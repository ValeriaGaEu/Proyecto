/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the connection to the MySQL database.
 *
 * @author Valeria
 */
public class DatabaseConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/hydroponic_garden";

    // Database username
    private static final String USER = "root";

    // Database password
    private static final String PASSWORD = "12345";

    /**
     * Returns a connection to the database.
     *
     * @return Connection object
     */
    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database connection established.");

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL Driver not found.");

        } catch (SQLException e) {

            System.out.println("Database connection failed.");
            System.out.println(e.getMessage());

        }

        return connection;
    }

}
/**
 *
 * @author valer
 */


