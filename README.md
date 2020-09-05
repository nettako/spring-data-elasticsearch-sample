# Integrating Spring-data-elasticsearch With Elasticsearch 7

What need todo prepare
* Version Spring Data Elasticsearch: 4.0.3.RELEASE
* Version Elasticsearch Client in build: 7.6.2
* Version Elasticsearch Client used: 7.6.2
* Version Elasticsearch cluster: 7.6.2 (elasticsearch installed must bee the same)
* Data type in elasticsearch 7 that you must handle

###Known Issue Spring-data elasticsearch for Elasticsearch 7
Disable auto create index (with createIndex = false) due to bugs on auto create index date field mapping pattern. 
when parsing date this will be error if you create index with spring data.

create index manually on elasticsearch with PUT method /[index-name]

example json index settings

> {
     "settings": {
       "index": {
         "number_of_shards": 3,
         "number_of_replicas": 2
       }
     },
     "mappings":{
           "properties":{
               "amount":{
                   "type":"float"
               },
               "createDate":{
                   "type":"date"
               },
               "dataName":{
                   "type":"text",
                   "fields":{
                       "keyword":{
                       "type":"keyword",
                       "ignore_above":256
                       }
                   }
               }
           }
       }
### Reference Documentation
For further reference, please consider the following sections:
* [Elasticsearch 7.6.2](https://www.elastic.co/downloads/past-releases#elasticsearch)
* [Spring Data Elasticsearch](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/)

