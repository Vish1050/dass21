# Stage 1: Build using JDK 25
FROM maven:3.9.9-eclipse-temurin-25 AS build
WORKDIR /app

# Copy the subfolder contents into the build container
# This tells Docker: "Go into the dass21 folder and take everything"
COPY dass21/ .

# Give permission to the maven wrapper and build
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the JAR from the build stage (Maven puts it in /target)
COPY --from=build /app/target/dass21-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Use Render's dynamic port
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]