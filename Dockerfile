FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target/manufacturers-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8086

CMD ["java", "-jar", "manufacturers-api-1.0.0-SNAPSHOT.jar"]