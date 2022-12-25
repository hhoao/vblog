#!/bin/bash
if curl -sf --cacert ca.crt --user elastic:"${ELASTIC_PASSWORD}" https://localhost:9200/articles|grep -q articles;
then
  # 删除articles索引
  # curl -XDELETE --cacert /data/elastic/ca/ca.crt --user elastic:"${ELASTIC_PASSWORD}" "https://${SERVER_HOST}:9200/articles";
  # 创建articles索引
  curl -XPUT --cacert /data/elastic/ca/ca.crt --user elastic:"${ELASTIC_PASSWORD}" \
    "https://${SERVER_HOST}:9200/articles" -H 'Content-Type: application/json' -d'
  {
      "aliases" : { },
      "mappings" : {
        "properties" : {
          "@timestamp" : {
            "type" : "date"
          },
          "@version" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "id" : {
            "type" : "long"
          },
          "title" : {
            "type" : "text",
            "analyzer" : "ik_max_word",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "digest" : {
            "type" : "text",
            "analyzer" : "ik_max_word"
          },
          "content" : {
            "type" : "text",
            "analyzer" : "ik_max_word"
          }
        }
      }
  }'
else
  echo "Index of articles already exits;";
fi
