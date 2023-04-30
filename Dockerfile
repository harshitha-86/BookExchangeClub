# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN apt-get update && apt-get install -y dos2unix && \
    dos2unix mvnw && \
    chmod +x mvnw && \
    rm -rf /var/lib/apt/lists/*

ENV PATH="/app:${PATH}"

COPY src ./src

CMD ["/app/mvnw", "spring-boot:run"]
