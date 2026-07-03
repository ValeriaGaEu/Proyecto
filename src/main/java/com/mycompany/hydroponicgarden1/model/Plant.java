/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;


public class Plant {

    private int plantId;
    private String namePlant;
    private String variety;
    private int harvestTimeDays;
    private double phIdealMax;
    private double phIdealMin;
    private int systemId;

    public Plant() {
    }

    public Plant(int plantId,
                 String namePlant,
                 String variety,
                 int harvestTimeDays,
                 double phIdealMax,
                 double phIdealMin,
                 int systemId) {

        this.plantId = plantId;
        this.namePlant = namePlant;
        this.variety = variety;
        this.harvestTimeDays = harvestTimeDays;
        this.phIdealMax = phIdealMax;
        this.phIdealMin = phIdealMin;
        this.systemId = systemId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getNamePlant() {
        return namePlant;
    }

    public void setNamePlant(String namePlant) {
        this.namePlant = namePlant;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public int getHarvestTimeDays() {
        return harvestTimeDays;
    }

    public void setHarvestTimeDays(int harvestTimeDays) {
        this.harvestTimeDays = harvestTimeDays;
    }

    public double getPhIdealMax() {
        return phIdealMax;
    }

    public void setPhIdealMax(double phIdealMax) {
        this.phIdealMax = phIdealMax;
    }

    public double getPhIdealMin() {
        return phIdealMin;
    }

    public void setPhIdealMin(double phIdealMin) {
        this.phIdealMin = phIdealMin;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
   
