/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;

import java.sql.Timestamp;

public class Monitoring {

    private int idMonitoring;
    private double waterTemperature;
    private double ambientTemperature;
    private double ambientHumidity;
    private double ecMeasured;
    private double phMeasured;
    private Timestamp dateTime;

    public Monitoring() {
    }

    public int getIdMonitoring() {
        return idMonitoring;
    }

    public void setIdMonitoring(int idMonitoring) {
        this.idMonitoring = idMonitoring;
    }

    public double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public double getAmbientHumidity() {
        return ambientHumidity;
    }

    public void setAmbientHumidity(double ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }

    public double getEcMeasured() {
        return ecMeasured;
    }

    public void setEcMeasured(double ecMeasured) {
        this.ecMeasured = ecMeasured;
    }

    public double getPhMeasured() {
        return phMeasured;
    }

    public void setPhMeasured(double phMeasured) {
        this.phMeasured = phMeasured;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
