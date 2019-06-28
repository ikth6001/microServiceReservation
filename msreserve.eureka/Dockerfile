FROM openjdk:8
WORKDIR /app
ADD target/msreserve.eureka-0.0.1-SNAPSHOT.jar /app/app.jar
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /app/
ADD entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/wait-for-it.sh
RUN chmod +x /app/entrypoint.sh
ENTRYPOINT ["sh", "entrypoint.sh"]