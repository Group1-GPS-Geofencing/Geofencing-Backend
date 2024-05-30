# Build the Application
FROM maven:3-openjdk-17 AS build
WORKDIR /app

# Copy only the pom.xml first and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests


FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/geofence-0.0.1-SNAPSHOT.jar geofence.jar


# Script to decode environment variables and create JSON files
COPY create_keys.sh /app/create_keys.sh
RUN chmod +x /app/create_keys.sh

EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "/app/create_keys.sh && java -jar geofence.jar"]