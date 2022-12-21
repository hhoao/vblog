FROM elasticsearch:7.17.3

USER "0"
RUN ["./bin/elasticsearch-plugin", "install", "--batch", "https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.17.3/elasticsearch-analysis-ik-7.17.3.zip"]
