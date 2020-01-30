package com.homes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.homes.model.EnergyConsumption;
import com.homes.model.Village;
import com.homes.model.VillageObject;
import com.homes.utilities.DateUtil;
import com.homes.utilities.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReadWriteData implements ReadWriteFile
{
    private File file;
    private ObjectMapper om;

    @Autowired
    private StringUtil strUtil;


    public ReadWriteData()
    {
        om = new ObjectMapper(new YAMLFactory());
        file = new File("src/main/resources/config/village_data.yaml");
    }

    @Override
    public boolean storeData(int counter_id, double consumption) {
        try {
            VillageObject vilObj = readData();
            if(validateCounter(counter_id)) {
                EnergyConsumption enco = new EnergyConsumption();
                enco.setConsumption(consumption);
                enco.setRecordedAt(new Date());
                vilObj.getVillages().forEach(n ->
                    {if (n.getCounter_id() == counter_id)
                    n.addRecord(enco);});
                om.writeValue(file, vilObj);
            }
            else
                return false;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public String retrieveData(int id) {
             List<Village> villageData = readData().getVillages();
            Comparator<Village> comparator = Comparator.comparing(Village::getCreated_at);
            Village vil =villageData.stream()
                            .filter(n -> n.getCounter_id() == id)
                            .max(comparator)
                            .orElse(null);
            return "{\"counter_id\":" + vil.getCounter_id() + ", \"village_name\": \"" + vil.getVillage_name() + "\"}";
    }

    public VillageObject readData(){
        VillageObject village_details = null;
        try {
            if (!file.exists())
            {
                file.createNewFile();
            }
            village_details = om.readValue(file,VillageObject.class);
        }
        catch(EOFException e) {
            //System.out.println("empty file");
        }
            catch (IOException e) {
            e.printStackTrace();
        }

        return village_details;
    }

    @Override
    public JsonObject retrieveReport(String duration) {
        VillageObject village_details = null;
        JsonObject json = null;
        JsonArray result = new JsonArray();
        List<Village> villageData = readData().getVillages();
        int maxDuration = strUtil.retrieve_duration(duration);

        for(Village village: villageData)
        {
            Comparator<EnergyConsumption> comparator = Comparator.comparing(EnergyConsumption::getRecordedAt);
            List<EnergyConsumption> ecomList = village.getEnergyConsumptions();
            if(ecomList.size() > 0) {
                EnergyConsumption encom = ecomList.stream().filter(n -> DateUtil.getTimeDifference(n.getRecordedAt(),
                        new Date()) < maxDuration)
                        .collect(Collectors.maxBy(comparator))
                        .orElse(null);
                if(null != encom) {
                    json = new JsonObject();
                    json.addProperty("village_name", village.getVillage_name());
                    json.addProperty("consumption", encom.getConsumption());
                    result.add(json);
                }
            }
        }
        JsonObject rJson = new JsonObject();
        rJson.add("villages", result);
        return rJson;
    }

    @Override
    public boolean insert(String village_name) {
        List<Village> villages = null;

        if (!validateVillageExist(village_name))
        {
            VillageObject vilObj = readData();
            try {
                if(null == vilObj) {
                    villages = new ArrayList<>();
                    vilObj = new VillageObject(villages);
                }
                villages = vilObj.getVillages();
                int counter = villages.size();
                Village village = (new Village(++counter,village_name, new ArrayList<>(), new Date()));
                villages.add(village);
                vilObj.setVillages(villages);
                    om.writeValue(file, vilObj);
                }
                catch (IOException e) {
                    e.printStackTrace();
                return false;
            }
        }
            return true;
    }

    public boolean validateVillageExist(String village_name) {
        boolean villageExists = false;
        //validation logic
        return villageExists;
    }

    public boolean validateCounter(int counter) {
        boolean counterExists = true;
        //validation logic
        return counterExists;
    }
}
