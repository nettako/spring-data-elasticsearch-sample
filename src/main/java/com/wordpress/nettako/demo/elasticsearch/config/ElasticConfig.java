package com.wordpress.nettako.demo.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "com.wordpress.nettako.demo.elasticsearch.repositories"
)
public class ElasticConfig extends AbstractElasticsearchConfiguration {

    @Value("${server.elastic.address}")
    String elasticServerAddress;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticServerAddress)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
