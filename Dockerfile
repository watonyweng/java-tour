FROM openjdk:11.0.4-jre

ADD target/lib /usr/share/java/lib

ARG JAR_FILE

ADD target/${JAR_FILE} /usr/share/java/app.jar

ENTRYPOINT ["java", "-jar", "/usr/share/java/app.jar"]
