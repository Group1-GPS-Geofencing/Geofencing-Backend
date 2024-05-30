# Build the Application
LABEL authors="James"

FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /target/geofence-0.0.1-SNAPSHOT.jar geofence.jar


# Script to decode environment variables and create JSON files
COPY create_keys.sh /app/create_keys.sh
RUN chmod +x /app/create_keys.sh

EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "/app/create_keys.sh && java -jar geofence.jar"]