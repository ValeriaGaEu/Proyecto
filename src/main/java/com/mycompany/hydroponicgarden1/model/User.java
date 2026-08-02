/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.model;

import java.sql.Date;

/**
 * Represents a user of the Hydroponic Garden system.
 *
 * @author Valeria
 */
public class User extends Person {

    // User unique identifier
    private int userId;
    // User password
    private String password;
    public User() {
        super();
}

    public User(int userId,
        String firstName,
        String lastName,
        Date birthDate,
        String emailOrPhone,
        String password) {

    super(firstName, lastName, birthDate, emailOrPhone);

    this.userId = userId;
    this.password = password;
}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
@Override
public String mostrarInformacion() {

    return "Usuario: "
            + getFirstName()
            + " "
            + getLastName()
            + " | Contacto: "
            + getEmailOrPhone();

}
}