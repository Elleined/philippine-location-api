FROM gradle:8.14.0-jdk21-alpine AS build
WORKDIR /app
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN ./gradlew clean build --no-daemon

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
VOLUME /tmp
COPY --from=build /app/build/libs/philippine-location-api.jar .
ENTRYPOINT ["java", "-jar", "philippine-location-api.jar"]