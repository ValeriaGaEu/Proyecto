/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;

/**
 *
 * @author valer
 */
public class HydroponicSystem {

    // System ID
    private int systemId;

    // Capacity in liters
    private double capacityLiters;

    // Hydroponic system type
    private String typeSystem;

    // System location
    private String ubication;

    // Owner user ID
    private int userId;

    public HydroponicSystem() {
    }

    public HydroponicSystem(int systemId,
            double capacityLiters,
            String typeSystem,
            String ubication,
            int userId) {

        this.systemId = systemId;
        this.capacityLiters = capacityLiters;
        this.typeSystem = typeSystem;
        this.ubication = ubication;
        this.userId = userId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public double getCapacityLiters() {
        return capacityLiters;
    }

    public void setCapacityLiters(double capacityLiters) {
        this.capacityLiters = capacityLiters;
    }

    public String getTypeSystem() {
        return typeSystem;
    }

    public void setTypeSystem(String typeSystem) {
        this.typeSystem = typeSystem;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
     
