FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/application.yml application.yml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]