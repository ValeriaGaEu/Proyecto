/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.dao;

import com.mycompany.hydroponicgarden1.config.DatabaseConnection;
import com.mycompany.hydroponicgarden1.model.Plant;
import java.sql.*;
import java.util.ArrayList;

public class PlantDAO {

    private final Connection connection;

    public PlantDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // ==========================
    // INSERTAR PLANTA
    // ==========================
    public boolean insertPlant(Plant p) {

        String sql = """
            INSERT INTO plant
            (
                name_planat,
                variety,
                harvest_time_days,
                ph_ideal_max,
                ph_ideal_min,
                id_system
            )
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, p.getNamePlant());
            ps.setString(2, p.getVariety());
            ps.setInt(3, p.getHarvestTimeDays());
            ps.setDouble(4, p.getPhIdealMax());
            ps.setDouble(5, p.getPhIdealMin());
            ps.setInt(6, p.getSystemId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // OBTENER PLANTAS POR SISTEMA
    // ==========================
    public ArrayList<Plant> getPlantsBySystem(int systemId) {

        ArrayList<Plant> list = new ArrayList<>();

        String sql = "SELECT * FROM plant WHERE id_system = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, systemId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Plant p = new Plant();

                    p.setPlantId(rs.getInt("id_plant"));
                    p.setNamePlant(rs.getString("name_planat"));
                    p.setVariety(rs.getString("variety"));
                    p.setHarvestTimeDays(rs.getInt("harvest_time_days"));
                    p.setPhIdealMax(rs.getDouble("ph_ideal_max"));
                    p.setPhIdealMin(rs.getDouble("ph_ideal_min"));
                    p.setSystemId(rs.getInt("id_system"));

                    list.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ==========================
    // ELIMINAR PLANTA
    // ==========================
    public boolean deletePlant(int plantId) {

        String sql = "DELETE FROM plant WHERE id_plant = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, plantId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // BUSCAR PLANTAS
    // ==========================
    public ArrayList<Plant> searchPlants(int systemId, String text) {

        ArrayList<Plant> list = new ArrayList<>();

        String sql = """
            SELECT * 
            FROM plant
            WHERE id_system = ?
            AND (
                name_planat LIKE ?
                OR variety LIKE ?
                OR CAST(id_plant AS CHAR) LIKE ?
            )
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, systemId);

            String filter = "%" + text + "%";

            ps.setString(2, filter);
            ps.setString(3, filter);
            ps.setString(4, filter);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Plant p = new Plant();

                    p.setPlantId(rs.getInt("id_plant"));
                    p.setNamePlant(rs.getString("name_planat"));
                    p.setVariety(rs.getString("variety"));
                    p.setHarvestTimeDays(rs.getInt("harvest_time_days"));
                    p.setPhIdealMax(rs.getDouble("ph_ideal_max"));
                    p.setPhIdealMin(rs.getDouble("ph_ideal_min"));
                    p.setSystemId(rs.getInt("id_system"));

                    list.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
