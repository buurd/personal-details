# start with OpenJDK 21 on Alpine, a lightweight Linux distribution
FROM eclipse-temurin:21

# port the Spring app listens to inside Docker image
EXPOSE 8080

# a directory within the container where the app will be located
WORKDIR /app

# copy the JAR file into the image
COPY ../target/personal-details-1.0-SNAPSHOT.jar /app/myapp.jar

# command to run the app using this shell form
CMD ["java", "-jar", "/app/myapp.jar"]

# allow creating a database-file by giving permission to any dir you may store the db
RUN mkdir /app/db
RUN chmod -R 770 /app/db