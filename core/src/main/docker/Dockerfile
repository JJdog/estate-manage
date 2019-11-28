
FROM java:alpine

MAINTAINER Jaswine

VOLUME /tmp

WORKDIR /home/

EXPOSE 14000

RUN mkdir Micro-Apps

ARG JAR_FILE

COPY ${JAR_FILE} app.jar


ENTRYPOINT ["java","-jar","./app.jar"]
