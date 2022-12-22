FROM openjdk:17
ADD ./build/libs/vblog-server-search-0.0.1-SNAPSHOT-exec.jar /
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/vblog-server-search-0.0.1-SNAPSHOT-exec.jar"]
