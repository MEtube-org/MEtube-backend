FROM gradle:8.7.0-jdk17
COPY . .
RUN ./gradlew clean build
FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]