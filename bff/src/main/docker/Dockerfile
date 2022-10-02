FROM openjdk:17-jdk-alpine

EXPOSE 8080

WORKDIR usr/bin/groupeh

ARG LOCAL_JAR_FILE=maven/*.jar

ADD ${LOCAL_JAR_FILE} app.jar

CMD ["java","-jar","app.jar"]