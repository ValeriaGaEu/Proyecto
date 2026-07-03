/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;

import java.sql.Date;

public class Batch {

    private int batchId;
    private String state;
    private int quantity;
    private Date plantingDate;
    private int systemId;
    private int plantId;

    public Batch() {
    }

    public Batch(int batchId, String state, int quantity,
                 Date plantingDate, int systemId, int plantId) {

        this.batchId = batchId;
        this.state = state;
        this.quantity = quantity;
        this.plantingDate = plantingDate;
        this.systemId = systemId;
        this.plantId = plantId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(Date plantingDate) {
        this.plantingDate = plantingDate;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

}
