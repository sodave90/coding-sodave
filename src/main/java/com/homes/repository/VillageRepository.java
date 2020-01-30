package com.homes.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.homes.service.ReadWriteData;
import com.homes.utilities.ResponseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is responsible for storing and retrieving
 * village data to/from data storage.
 * */
@Repository
public class VillageRepository implements ObjectRepository
{
    @Autowired
    private ReadWriteData readWritedata;


    public String addVillage(String village_name)
    {
        boolean villlageExists = readWritedata.validateVillageExist(village_name);
        if(villlageExists)
          return ResponseConstant.VILLAGE_EXISTS;
        boolean isValid = readWritedata.insert(village_name);
        if(isValid)
            return ResponseConstant.SUCCESS_MSG;
        else
            return ResponseConstant.FAILURE_MSG;
    }

    @Override
    public boolean store(int counter_id, double consumption) {
        boolean savedData = readWritedata.storeData(counter_id, consumption);
        return savedData;
    }

    @Override
    public String retrieve(int id) {

        String villageDet = readWritedata.retrieveData(id);
        return villageDet;
    }

    @Override
    public String retrieveReport(String duration) {

        JsonObject villJson = readWritedata.retrieveReport(duration);
        return new Gson().toJson(villJson);
    }

}
