# Stage 1: Build using JDK 25
FROM maven:3.9.9-eclipse-temurin-25 AS build
WORKDIR /app

# Copy the dass21 subfolder content into the container
COPY dass21/ .

# Fix permissions and build
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the JAR (Maven puts it in the target folder)
COPY --from=build /app/target/dass21-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Map to Render's dynamic port
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]