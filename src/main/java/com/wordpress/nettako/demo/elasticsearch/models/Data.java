package com.wordpress.nettako.demo.elasticsearch.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//disable auto create index due with createIndex = false to bugs on auto create index date field mapping pattern parse
//create index manually on elasticsearch with PUT method /[index-name]
/*example json index settings
* {
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
}*/
@Setter
@Getter
@Document(indexName = "data", createIndex = false)
public class Data {
    @Id
    String id;
    @Field(name = "dataName", type = FieldType.Text)
    String dataName;
    @Field(name = "amount", type = FieldType.Double)
    BigDecimal amount;
    @Field(name = "createDate", type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'kk:mm:ss")
    LocalDateTime createDate;
}
