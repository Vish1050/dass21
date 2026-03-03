# Stage 1: Build the app using JDK 25
FROM maven:3.9.9-eclipse-temurin-25 AS build
WORKDIR /app

# Copy the subfolder content into the build container
COPY dass21/ .

# Ensure the maven wrapper has execution permissions
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the built JAR from the build stage
# Based on your pom.xml, the name is dass21-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/dass21-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Use Render's dynamic PORT environment variable
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]