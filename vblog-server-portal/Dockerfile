FROM openjdk:17-jdk-alpine
COPY ./build/libs/vblog-server-portal-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/vblog-server-portal-0.0.1-SNAPSHOT.jar"]
