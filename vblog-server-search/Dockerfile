FROM openjdk:17
COPY ./build/libs/vblog-server-search-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/vblog-server-search-0.0.1-SNAPSHOT.jar"]
