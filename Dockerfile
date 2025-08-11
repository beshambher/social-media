FROM openjdk:17-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests
RUN ls -l /app/target/

COPY target/*.war app.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
