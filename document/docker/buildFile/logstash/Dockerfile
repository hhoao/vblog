FROM logstash:7.17.3

USER "0"
RUN bash -c ' \
    rm -f /usr/share/logstash/pipeline/logstash.conf /usr/share/logstash/config/logstash-sample.conf; \
    echo > /usr/share/logstash/config/logstash.yml; \
    if [ ! -d /data/mysql/driver ]; \
    then \
        mkdir -p /data/mysql/driver; \
        curl -O https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.25/mysql-connector-java-8.0.25.jar; \
    fi; \
    '
