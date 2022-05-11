FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=build/libs/WorkSearch-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java","-jar","/application.jar"]