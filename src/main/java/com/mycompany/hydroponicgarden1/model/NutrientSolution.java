/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;

import java.sql.Date;
public class NutrientSolution {

    // Nutrient solution unique identifier
    private int solutionId;

    // Preparation date
    private Date preparationDate;

    // Electrical conductivity
    private double conductivity;

    // Solution temperature
    private double temperature;

    // pH level
    private double levelPh;

    // Hydroponic system identifier
    private int systemId;

    public NutrientSolution() {
    }

    public NutrientSolution(int solutionId, Date preparationDate,
            double conductivity, double temperature,
            double levelPh, int systemId) {

        this.solutionId = solutionId;
        this.preparationDate = preparationDate;
        this.conductivity = conductivity;
        this.temperature = temperature;
        this.levelPh = levelPh;
        this.systemId = systemId;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public Date getPreparationDate() {
        return preparationDate;
    }

    public void setPreparationDate(Date preparationDate) {
        this.preparationDate = preparationDate;
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLevelPh() {
        return levelPh;
    }

    public void setLevelPh(double levelPh) {
        this.levelPh = levelPh;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

}