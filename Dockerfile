ARG MAVEN_VERSION=3.9.6-eclipse-temurin-21
FROM maven:${MAVEN_VERSION} AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV APP_PORT=8080

EXPOSE ${APP_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]
