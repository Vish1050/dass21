# Stage 1: Build using a standard JDK 25 image
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy the dass21 subfolder content into the container
COPY dass21/ .

# Ensure the maven wrapper script is executable
RUN chmod +x mvnw

# FIX: Add -Dmaven.test.skip=true to skip compiling tests entirely
RUN ./mvnw clean package -DskipTests -Dmaven.test.skip=true

# Stage 2: Run the app
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/dass-app.jar app.jar

EXPOSE 8080

# Use Render's dynamic port
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]