package com.wordpress.nettako.demo.elasticsearch.services;

import com.wordpress.nettako.demo.elasticsearch.repositories.DataRepository;
import com.wordpress.nettako.demo.elasticsearch.models.Data;
import com.wordpress.nettako.demo.elasticsearch.records.DateRangeReqMsg;
import com.wordpress.nettako.demo.elasticsearch.records.NewDataReqMsg;
import com.wordpress.nettako.demo.elasticsearch.records.UpdateDataReqMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ElasticCrudService {

    @Autowired
    DataRepository dataRepository;

    public List<Data> loadData(DateRangeReqMsg dateRangeReqMsg) throws ParseException {
        LocalDateTime startDate = dateRangeReqMsg.getStartDate();
        LocalDateTime endDate = dateRangeReqMsg.getEndDate();
        List<Data> dataList = dataRepository.findAllByCreateDateBetweenOrderByCreateDateDesc(startDate, endDate);
        return dataList;
    }

    public void addData(NewDataReqMsg reqMsg) throws Exception {
        Data data = new Data();
        data.setAmount(reqMsg.getAmount());
        data.setDataName(reqMsg.getDataName());
        Optional<LocalDateTime> createLocalDateTime = Optional.ofNullable(reqMsg.getCreateDate());
        if(createLocalDateTime.isEmpty()){
            data.setCreateDate(Timestamp.from(Instant.now()).toLocalDateTime());
        }else{
            data.setCreateDate(reqMsg.getCreateDate());
        }
        dataRepository.save(data);
    }

    public void updatedata(String dataName, UpdateDataReqMsg reqMsg) throws Exception {
       var existingData = dataRepository.findByDataName(dataName);
       if(existingData.isPresent()){
           var data = existingData.get();
           data.setAmount(reqMsg.getAmount());
           data.setCreateDate(reqMsg.getCreateDate());
           dataRepository.save(data);
       }else{
           throw new NullPointerException("Data not found");
       }
    }

    public void removedata(String dataName) throws Exception {
        var existingData = dataRepository.findByDataName(dataName);
        if(existingData.isPresent()){
            var data = existingData.get();
            dataRepository.delete(data);
        }else{
            throw new NullPointerException("Data not found");
        }
    }
}
