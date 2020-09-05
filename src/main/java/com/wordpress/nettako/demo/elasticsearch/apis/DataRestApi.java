package com.wordpress.nettako.demo.elasticsearch.apis;

import com.wordpress.nettako.demo.elasticsearch.services.ElasticCrudService;
import com.wordpress.nettako.demo.elasticsearch.records.DateRangeReqMsg;
import com.wordpress.nettako.demo.elasticsearch.records.NewDataReqMsg;
import com.wordpress.nettako.demo.elasticsearch.records.UpdateDataReqMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class DataRestApi {
    @Autowired
    ElasticCrudService elasticCrudService;

    @PostMapping(path = "/data/search")
    public ResponseEntity getInDateRange(@RequestBody DateRangeReqMsg requestMessage) throws ParseException {
        return ResponseEntity.ok(elasticCrudService.loadData(requestMessage));
    }

    @PutMapping(path = "/data")
    public ResponseEntity addData(@RequestBody NewDataReqMsg newRequest) {
        try {
            elasticCrudService.addData(newRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping(path = "/data/{dataName}")
    public ResponseEntity updateData(@PathVariable(name = "dataName") String dataName, @RequestBody UpdateDataReqMsg updateReq) {
        try {
            elasticCrudService.updatedata(dataName, updateReq);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/data/{dataName}")
    public ResponseEntity updateData(@PathVariable(name = "dataName") String dataName) {
        try {
            elasticCrudService.removedata(dataName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
