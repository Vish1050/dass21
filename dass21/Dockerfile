# Step 1: Build the app using Maven and Java 25
FROM maven:3-openjdk-21 AS build
# Note: As of early 2026, Maven images often use 21 as the stable base,
# but we will compile for 25 inside it.
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the app using Java 25
FROM openjdk:25-slim
COPY --from=build /target/dass-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]