/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.dao;
import com.mycompany.hydroponicgarden1.config.DatabaseConnection;
import com.mycompany.hydroponicgarden1.model.HydroponicSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author valer
 */

public class HydroponicSystemDAO {
     private Connection connection;

public HydroponicSystemDAO() {

    connection = DatabaseConnection.getConnection();

}
/**
 * Inserts a new hydroponic system.
 *
 * @param system Hydroponic system.
 * @return true if inserted successfully.
 */
public boolean insertSystem(HydroponicSystem system) {
    

    String sql = """
            INSERT INTO garden_system
            (
                capacity_liters,
                type_system,
                ubication,
                id_user
            )
            VALUES (?, ?, ?, ?)
            """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDouble(1, system.getCapacityLiters());
        statement.setString(2, system.getTypeSystem());
        statement.setString(3, system.getUbication());
        statement.setInt(4, system.getUserId());

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Insert System Error: " + e.getMessage());

    }

    return false;

}
// ==========================
    // OBTENER SISTEMAS DEL USUARIO
    // ==========================
    public ArrayList<HydroponicSystem> getSystemsByUser(int userId) {

        ArrayList<HydroponicSystem> systems = new ArrayList<>();

        String sql = """
                SELECT *
                FROM garden_system
                WHERE id_user = ?
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                HydroponicSystem system = new HydroponicSystem();

                system.setSystemId(result.getInt("id_system"));
                system.setCapacityLiters(result.getDouble("capacity_liters"));
                system.setTypeSystem(result.getString("type_system"));
                system.setUbication(result.getString("ubication"));
                system.setUserId(result.getInt("id_user"));

                systems.add(system);
            }

        } catch (SQLException e) {

            System.out.println("Get Systems Error: " + e.getMessage());

        }

        return systems;
    }
    public boolean updateSystem(HydroponicSystem system) {

    String sql = """
        UPDATE garden_system
        SET
            capacity_liters = ?,
            type_system = ?,
            ubication = ?
        WHERE id_system = ?
        """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDouble(1, system.getCapacityLiters());
        statement.setString(2, system.getTypeSystem());
        statement.setString(3, system.getUbication());
        statement.setInt(4, system.getSystemId());

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Update Error: " + e.getMessage());

    }

    return false;
}
    public boolean deleteSystem(int systemId) {

    String sql = """
        DELETE FROM garden_system
        WHERE id_system = ?
        """;

    try {

        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.setInt(1, systemId);

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Delete Error: " + e.getMessage());

    }

    return false;
}
    public ArrayList<HydroponicSystem> searchSystems(int userId, String texto) {

    ArrayList<HydroponicSystem> systems = new ArrayList<>();

    String sql = """
        SELECT *
        FROM garden_system
        WHERE id_user = ?
        AND (
            type_system LIKE ?
            OR ubication LIKE ?
            OR CAST(id_system AS CHAR) LIKE ?
        )
        """;

    try {

        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.setInt(1, userId);

        String filtro = "%" + texto + "%";

        statement.setString(2, filtro);
        statement.setString(3, filtro);
        statement.setString(4, filtro);

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            HydroponicSystem system = new HydroponicSystem();

            system.setSystemId(result.getInt("id_system"));
            system.setCapacityLiters(result.getDouble("capacity_liters"));
            system.setTypeSystem(result.getString("type_system"));
            system.setUbication(result.getString("ubication"));
            system.setUserId(result.getInt("id_user"));

            systems.add(system);
        }

    } catch (SQLException e) {

        System.out.println("Search Error: " + e.getMessage());

    }

    return systems;
}
}


