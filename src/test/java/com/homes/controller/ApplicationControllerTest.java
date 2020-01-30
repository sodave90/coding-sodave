package com.homes.controller;

import com.homes.model.EnergyConsumption;
import com.homes.model.Village;
import com.homes.repository.VillageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

    @Mock
    VillageRepository repository;

    @InjectMocks
    ApplicationController applicationController;

    @Test
    public void testStoreCounterDetails()
    {
        Village village = new Village();
        village.setCounter_id(1);
        village.setVillage_name("TestVillage");

        boolean recordSaved = true;

        when(repository.store(1,1234)).thenReturn(recordSaved);

        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("counter_id",1);
        reqMap.put("consumption",1234.0);

        ResponseEntity<Object> response = applicationController.storeCounterDetails(reqMap);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGenerateReport()
    {
        String duration ="24h";

        List<EnergyConsumption> encom = new ArrayList<>();
        encom.add(new EnergyConsumption(123.0,new Date()));

        List<Village> villageList = new ArrayList<>();
        villageList.add(new Village(1,"TestVillage1",encom, new Date()));
        villageList.add(new Village(2,"TestVillage2",encom, new Date()));
        villageList.add(new Village(3,"TestVillage3",encom, new Date()));

        String result = "{\"villages\":[{\"village_name\":\"TestVillage1\"," +
                "\"consumption\":123.0},{\"village_name\":\"Village2\",\"consumption\":123.0}," +
                "{\"village_name\":\"Village3\",\"consumption\":123.0}]}";

        when(repository.retrieveReport(duration)).thenReturn(result);

        ResponseEntity<Object> response = applicationController.generateReport(duration);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(true,response.hasBody());

    }

    @Test
    public void testRetrieveVillageDet()
    {

        repository.addVillage("TestVillage");
        repository.store(1, 1234.0);

        String vilStr = "{\"counter_id\": 1, \"village_name\": \"TestVillage\"}";

        when(repository.retrieve(1)).thenReturn(vilStr);

        ResponseEntity<Object> response = applicationController.retrieveVillageDet(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.hasBody());
    }

}
