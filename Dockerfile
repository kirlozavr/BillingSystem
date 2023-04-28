FROM openjdk:21-slim
ARG JAR_FILE=/target/*.jar
WORKDIR /opt/app
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]