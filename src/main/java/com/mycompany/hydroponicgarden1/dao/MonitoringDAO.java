/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.dao;

import com.mycompany.hydroponicgarden1.config.DatabaseConnection;
import com.mycompany.hydroponicgarden1.model.Monitoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonitoringDAO {

    private Connection connection;

    public MonitoringDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
     * Registers a monitoring record.
     *
     * @param monitoring Monitoring object.
     * @return true if the record was saved successfully.
     */
    public boolean insertMonitoring(Monitoring monitoring) {

        String sql = """
                INSERT INTO monitoring
                (water_temperature,
                 ambient_temperature,
                 ambient_humidity,
                 ec_measured,
                 ph_measured)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDouble(1, monitoring.getWaterTemperature());
            statement.setDouble(2, monitoring.getAmbientTemperature());
            statement.setDouble(3, monitoring.getAmbientHumidity());
            statement.setDouble(4, monitoring.getEcMeasured());
            statement.setDouble(5, monitoring.getPhMeasured());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Insert Monitoring Error: " + e.getMessage());
            return false;

        }

    }

    /**
     * Returns all monitoring records.
     *
     * @return List of monitoring records.
     */
    public ArrayList<Monitoring> getAllMonitoring() {

        ArrayList<Monitoring> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM monitoring
                ORDER BY date_time DESC
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Monitoring monitoring = new Monitoring();

                monitoring.setIdMonitoring(rs.getInt("id_monitoring"));
                monitoring.setWaterTemperature(rs.getDouble("water_temperature"));
                monitoring.setAmbientTemperature(rs.getDouble("ambient_temperature"));
                monitoring.setAmbientHumidity(rs.getDouble("ambient_humidity"));
                monitoring.setEcMeasured(rs.getDouble("ec_measured"));
                monitoring.setPhMeasured(rs.getDouble("ph_measured"));
                monitoring.setDateTime(rs.getTimestamp("date_time"));

                list.add(monitoring);

            }

        } catch (SQLException e) {

            System.out.println("Get Monitoring Error: " + e.getMessage());

        }

        return list;

    }

    /**
     * Updates a monitoring record.
     *
     * @param monitoring Monitoring object.
     * @return true if updated successfully.
     */
    public boolean updateMonitoring(Monitoring monitoring) {

        String sql = """
                UPDATE monitoring
                SET water_temperature = ?,
                    ambient_temperature = ?,
                    ambient_humidity = ?,
                    ec_measured = ?,
                    ph_measured = ?
                WHERE id_monitoring = ?
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDouble(1, monitoring.getWaterTemperature());
            statement.setDouble(2, monitoring.getAmbientTemperature());
            statement.setDouble(3, monitoring.getAmbientHumidity());
            statement.setDouble(4, monitoring.getEcMeasured());
            statement.setDouble(5, monitoring.getPhMeasured());
            statement.setInt(6, monitoring.getIdMonitoring());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Update Monitoring Error: " + e.getMessage());
            return false;

        }

    }

    /**
     * Deletes a monitoring record.
     *
     * @param idMonitoring Monitoring ID.
     * @return true if deleted successfully.
     */
    public boolean deleteMonitoring(int idMonitoring) {

        String sql = """
                DELETE FROM monitoring
                WHERE id_monitoring = ?
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idMonitoring);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Delete Monitoring Error: " + e.getMessage());
            return false;

        }

    }

}