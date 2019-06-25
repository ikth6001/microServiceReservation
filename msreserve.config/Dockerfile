FROM openjdk:8-jdk-alpine
ADD target/msreserve.config-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /app
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]