FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/philippine-location-api.jar ./philippine-location-api.jar
ENTRYPOINT ["java", "-jar", "philippine-location-api.jar"]