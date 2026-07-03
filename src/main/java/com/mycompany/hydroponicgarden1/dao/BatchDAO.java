/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.dao;

import com.mycompany.hydroponicgarden1.config.DatabaseConnection;
import com.mycompany.hydroponicgarden1.model.Batch;
import java.sql.*;
import java.util.ArrayList;


public class BatchDAO {

    private final Connection connection;

    public BatchDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // ==========================
    // INSERTAR LOTE
    // ==========================
    public boolean insertBatch(Batch batch) {

        String sql = """
            INSERT INTO batch
            (state, quantity, planting_date, id_system, id_plant)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, batch.getState());
            ps.setInt(2, batch.getQuantity());
            ps.setDate(3, batch.getPlantingDate());
            ps.setInt(4, batch.getSystemId());
            ps.setInt(5, batch.getPlantId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // OBTENER LOTES POR SISTEMA
    // ==========================
    public ArrayList<Batch> getBatchBySystem(int systemId) {

        ArrayList<Batch> list = new ArrayList<>();

        String sql = "SELECT * FROM batch WHERE id_system = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, systemId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Batch batch = new Batch();

                batch.setBatchId(rs.getInt("id_batch"));
                batch.setState(rs.getString("state"));
                batch.setQuantity(rs.getInt("quantity"));
                batch.setPlantingDate(rs.getDate("planting_date"));
                batch.setSystemId(rs.getInt("id_system"));
                batch.setPlantId(rs.getInt("id_plant"));

                list.add(batch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ==========================
    // MODIFICAR
    // ==========================
    public boolean updateBatch(Batch batch) {

        String sql = """
            UPDATE batch
            SET state=?,
                quantity=?,
                planting_date=?,
                id_system=?,
                id_plant=?
            WHERE id_batch=?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, batch.getState());
            ps.setInt(2, batch.getQuantity());
            ps.setDate(3, batch.getPlantingDate());
            ps.setInt(4, batch.getSystemId());
            ps.setInt(5, batch.getPlantId());
            ps.setInt(6, batch.getBatchId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean deleteBatch(int idBatch) {

        String sql = "DELETE FROM batch WHERE id_batch=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idBatch);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // BUSCAR
    // ==========================
    public ArrayList<Batch> searchBatch(int systemId, String texto) {

        ArrayList<Batch> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM batch
            WHERE id_system=?
            AND (
                CAST(id_batch AS CHAR) LIKE ?
                OR state LIKE ?
            )
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, systemId);

            String filtro = "%" + texto + "%";

            ps.setString(2, filtro);
            ps.setString(3, filtro);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Batch batch = new Batch();

                batch.setBatchId(rs.getInt("id_batch"));
                batch.setState(rs.getString("state"));
                batch.setQuantity(rs.getInt("quantity"));
                batch.setPlantingDate(rs.getDate("planting_date"));
                batch.setSystemId(rs.getInt("id_system"));
                batch.setPlantId(rs.getInt("id_plant"));

                list.add(batch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
public Batch getBatchById(int idBatch) {

    String sql = "SELECT * FROM batch WHERE id_batch=?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setInt(1, idBatch);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Batch batch = new Batch();

            batch.setBatchId(rs.getInt("id_batch"));
            batch.setState(rs.getString("state"));
            batch.setQuantity(rs.getInt("quantity"));
            batch.setPlantingDate(rs.getDate("planting_date"));
            batch.setSystemId(rs.getInt("id_system"));
            batch.setPlantId(rs.getInt("id_plant"));

            return batch;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}
