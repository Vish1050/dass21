# Stage 1: Build using a standard JDK 25 image
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy the dass21 subfolder content into the container
COPY dass21/ .

# Ensure the maven wrapper script is executable
RUN chmod +x mvnw

# Run the build using the wrapper (this downloads Maven automatically)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the JAR from the build stage
# (The name matches your pom.xml artifactId and version)
COPY --from=build /app/target/dass21-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Use Render's dynamic port
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]