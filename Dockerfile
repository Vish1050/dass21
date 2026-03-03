# Ensure this matches your pom.xml (Java 25)
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY dass21/ .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests -Dmaven.test.skip=true

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/dass21-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}"]