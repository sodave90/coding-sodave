package com.homes.controller;

import com.homes.repository.VillageRepository;
import com.homes.utilities.ResponseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * This controller has REST Api
 * which receives and stores energy consumption data of a village,
 * and generates on-demand energy consumption report.
 * */
@RestController
public class ApplicationController {

    @Autowired
    private VillageRepository repository;

    private ResponseEntity<Object> responseEntity;

    @PostMapping("/village")
    public ResponseEntity<Object> addVillage(@RequestParam(name = "village_name", defaultValue = "ABC") String village_name){
        String response = repository.addVillage(village_name);

        if(ResponseConstant.FAILURE_MSG.equals(response))
        {
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        return responseEntity;

    }

    @PostMapping("/counter_callback")
    public ResponseEntity<Object> storeCounterDetails(@RequestBody Map<String,Object> request){
        boolean recordAdded = repository.store((Integer) request.get("counter_id"), (Double) request.get("consumption"));
        if(recordAdded)
        {
            responseEntity = new ResponseEntity<>(ResponseConstant.SUCCESS_MSG,HttpStatus.OK);
        }
        else
            responseEntity = new ResponseEntity<>(ResponseConstant.FAILURE_MSG,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @GetMapping("/counter")
    public ResponseEntity<Object> retrieveVillageDet(@RequestParam(name = "id", defaultValue = "0") int id){
        String village = repository.retrieve(id);
        if(null == village)
        {
            responseEntity = new ResponseEntity<>(ResponseConstant.NO_RECORD_MSG,HttpStatus.OK);
        }
        else
            responseEntity = new ResponseEntity<>(village,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/consumption_report")
    public ResponseEntity<Object> generateReport(@RequestParam(name = "duration", defaultValue = "24h") String duration){
        String villageStr =  repository.retrieveReport(duration);
        if(null== villageStr)
        {
            responseEntity = new ResponseEntity<>(ResponseConstant.NO_RECORD_MSG,HttpStatus.OK);
        }
        else
            responseEntity = new ResponseEntity<>(villageStr,HttpStatus.OK);
        return responseEntity;
    }
}
