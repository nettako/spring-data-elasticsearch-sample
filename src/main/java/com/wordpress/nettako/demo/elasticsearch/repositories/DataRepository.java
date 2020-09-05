package com.wordpress.nettako.demo.elasticsearch.repositories;

import com.wordpress.nettako.demo.elasticsearch.models.Data;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends ElasticsearchRepository<Data, Long> {
    List<Data> findAllByCreateDateBetweenOrderByCreateDateDesc(LocalDateTime startDate, LocalDateTime endDate);
    Optional<Data> findByDataName(String dataName);
}
