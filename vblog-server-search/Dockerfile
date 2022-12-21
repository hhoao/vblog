FROM openjdk:17
ADD ./target/vblog-server-search-0.0.1-SNAPSHOT-exec.jar /
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/vblog-server-search-0.0.1-SNAPSHOT-exec.jar"]
