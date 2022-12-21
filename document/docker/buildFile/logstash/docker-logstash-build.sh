#!/usr/bin/bash

bash -c "
  docker build -t hhoao/logstash:1.0 -f Dockerfile .
  docker push hhoao/logstash:1.0
"
