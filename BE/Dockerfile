# FROM openjdk:8-jdk-alpine
FROM openjdk:11-jre-slim-buster
# 제외된 모듈 추가
# RUN apt-get update; apt-get install -y fontconfig libfreetype6
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]