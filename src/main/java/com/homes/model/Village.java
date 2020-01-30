package com.homes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Village{


    private int counter_id;
    private String village_name;
    private List<EnergyConsumption> energyConsumptions;

    public List<EnergyConsumption> getEnergyConsumptions() {
        return energyConsumptions;
    }

    public void setEnergyConsumptions(List<EnergyConsumption> energyConsumptions) {
        this.energyConsumptions = energyConsumptions;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    private Date created_at;

    public Village()
    {
    }

    public Village(int counter_id, String village_name)
    {
        this.counter_id = counter_id;
        this.village_name = village_name;
    }

    public Village(int counter_id, String village_name, List<EnergyConsumption> consumption, Date created_at)
    {
        this.counter_id = counter_id;
        this.village_name = village_name;
        this.energyConsumptions = consumption;
        this.created_at = created_at;
    }


    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public int getCounter_id() {
        return counter_id;
    }

    public void setCounter_id(int counter_id) {
        this.counter_id = counter_id;
    }

    public String toString()
    {
        return "{\"counter_id\":" + counter_id + ",\"village_name\":" +
                village_name + ",\"consumption\":" + energyConsumptions + "}";
    }

    public void addRecord(EnergyConsumption encon)
    {
        List<EnergyConsumption> enList = getEnergyConsumptions();
        int size = enList.size();
        if(size == 0)
        {
            enList = new ArrayList<>();
        }
        enList.add(encon);
        setEnergyConsumptions(enList);
    }

}
