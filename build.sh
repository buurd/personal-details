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
     PID_FILE="spring-boot.pid"

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
     echo ${SPRING_BOOT_PID} > ${PID_FILE}
     echo "Spring Boot started with PID: ${SPRING_BOOT_PID}"
     waitForStartMsg < <(tail -F "${LOG_FILE}")
 }

 function stop-spring-server {
     PID_FILE="spring-boot.pid"
     if [ -f "${PID_FILE}" ]
     then
         SPRING_BOOT_PID=$(cat ${PID_FILE})
         echo "Stopping Spring Boot PID: ${SPRING_BOOT_PID}"
         kill "${SPRING_BOOT_PID}"
         rm ${PID_FILE}
     else
         echo "No spring boot process to stop"
     fi
 }

function run-integration-tests {
    echo "Running: Integration tests"
    rm -f ./target/database.mv.db
    mvn verify
}

function run-cypress-tests {
    echo "Running: Cypress Tests"
    npm test
}

for cmd in "$@"
do
case $cmd in
    install-cypress) install-cypress ;;
    install-build-react) install-build-react ;;
    copy-build-files) copy-build-files ;;
    start-spring-server) start-spring-server ;;
    stop-spring-server) stop-spring-server ;;
    run-integration-tests) run-integration-tests ;;
    run-cypress-tests) run-cypress-tests ;;
    *)
    echo "Invalid command: $cmd"
    echo $"Usage: $0 {install-cypress|install-build-react|copy-build-files|start-spring-server|stop-spring-server|run-integration-tests|run-cypress-tests}"
    exit 1
esac
done