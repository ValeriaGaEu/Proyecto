/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;

/**
 *
 * @author valer
 */
import java.sql.Date;

public class Person {

    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected String emailOrPhone;

    public Person() {
    }

    public Person(String firstName,
                  String lastName,
                  Date birthDate,
                  String emailOrPhone) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailOrPhone = emailOrPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    public String mostrarInformacion() {
        return firstName + " " + lastName;
    }
}