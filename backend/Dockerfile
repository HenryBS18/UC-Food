FROM maven:3-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/backend*.jar ./backend.jar

EXPOSE 8080

CMD [ "java", "-jar", "backend.jar" ]