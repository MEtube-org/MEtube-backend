FROM gradle:8.7.0-jdk17 AS builder

COPY . .

RUN ./gradlew clean build --no-daemon


FROM openjdk:17

COPY --from=builder /home/gradle/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]