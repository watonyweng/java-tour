FROM openjdk:8-jre

MAINTAINER Watony Weng <softweitao@126.com>

ENTRYPOINT ["java", "-jar", "/usr/share/java/app.jar"]

ADD target/lib /usr/share/java/lib

ARG JAR_FILE

ADD target/${JAR_FILE} /usr/share/java/app.jar