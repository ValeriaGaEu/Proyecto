/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.mycompany.hydroponicgarden1.session;

import com.mycompany.hydroponicgarden1.model.User;
//import com.mycompany.hydroponicgarden1.model.HydroponicSystem;

public class session {

    // Usuario logueado
    private static User currentUser;

    // Sistema seleccionado
   // private static HydroponicSystem currentSystem;

    // ================= USER =================
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    // ================= SYSTEM =================
  /*  public static void setCurrentSystem(HydroponicSystem system) {
        currentSystem = system;
    }

    public static HydroponicSystem getCurrentSystem() {
        return currentSystem;
    } */

    // ================= LOGOUT =================
    public static void logout() {
        currentUser = null;
       // currentSystem = null;
    }
}
