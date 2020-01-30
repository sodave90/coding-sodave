package com.homes.model;

import java.io.Serializable;
import java.util.List;

public class VillageObject implements Serializable
{
    public VillageObject(){}

    public VillageObject(List<Village> villages)
    {
       this.villages = villages;
    }

    private List<Village> villages;


    public List<Village> getVillages() {
        return villages;
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }



}
