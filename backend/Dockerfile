FROM openjdk:17.0-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]