package com.homes.model;

import java.util.Date;

public class EnergyConsumption {

    public EnergyConsumption()
    {
    }

    public EnergyConsumption(double consumption, Date recordedAt)
    {
        this.consumption = consumption;
        this.recordedAt = recordedAt;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }

    private double consumption;
    private Date recordedAt;
}
