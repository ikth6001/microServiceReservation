FROM openjdk:8
WORKDIR /app
ADD target/msreserve.config-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/app.jar"]