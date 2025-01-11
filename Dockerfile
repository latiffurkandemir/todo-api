FROM openjdk:21-jdk-slim

LABEL maintainer="Latif Furkan Demir <latiffurkan.demir@gmail.com>"

COPY target/todo-api-0.0.1-SNAPSHOT.jar todo-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "todo-api-0.0.1-SNAPSHOT.jar"]
