#!/bin/bash    

function install-cypress {
    echo "Running: Install Cypress"
    npm install cypress cypress-cucumber-preprocessor --save-dev
}

function install-build-react {
    echo "Running: Install and Build React"
    cd /home/roland/IdeaProjects/PersonalDetails/src/main/webapp
    npm install
    npm run build:dev
    cd /home/roland/IdeaProjects/PersonalDetails
}

function copy-build-files {
    echo "Running: Copy Build Files"
    cp -r ./src/main/webapp/build/* ./src/main/resources/static/
}

function start-spring-server {
    echo "Running: Start Spring Server"
    START_MSG="Started PersonalDetailsApp"
    LOG_FILE="spring-boot.log"

    function waitForStartMsg {
      while read LOGLINE
      do
        echo ${LOGLINE}
        if [[ "${LOGLINE}" == *"${START_MSG}"* ]]
        then
            echo "Spring Boot has started."
            break
        fi
      done
    }

    nohup mvn spring-boot:run > "${LOG_FILE}" 2>&1 &
    SPRING_BOOT_PID="$!"
    echo "Spring Boot started with PID: ${SPRING_BOOT_PID}"
    
    trap "kill ${SPRING_BOOT_PID}" EXIT
    waitForStartMsg < <(tail -F "${LOG_FILE}")
}

function run-integration-tests {
    echo "Running: Integration tests"
    mvn verify
}

function run-cypress-tests {
    echo "Running: Cypress Tests"
    npm test
}

function all_cmds_in_order {
    install-cypress
    install-build-react
    copy-build-files
    start-spring-server
    run-integration-tests
    run-cypress-tests
}

# Run all commands by default
all_cmds_in_order