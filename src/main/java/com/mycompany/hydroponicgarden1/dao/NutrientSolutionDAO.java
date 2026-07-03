/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.dao;

import com.mycompany.hydroponicgarden1.config.DatabaseConnection;
import com.mycompany.hydroponicgarden1.model.NutrientSolution;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class NutrientSolutionDAO {

    // Database connection
    private Connection connection;

    /**
     * Creates a new NutrientSolutionDAO object.
     */
    public NutrientSolutionDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
     * Registers a new nutrient solution.
     *
     * @param solution NutrientSolution object.
     * @return true if the solution was registered successfully.
     */
    public boolean registerSolution(NutrientSolution solution) {

        String sql = """
                INSERT INTO nutrient_solution
                (preparation_date, conductivity, temperature, level_ph, id_system)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, solution.getPreparationDate());
            statement.setDouble(2, solution.getConductivity());
            statement.setDouble(3, solution.getTemperature());
            statement.setDouble(4, solution.getLevelPh());
            statement.setInt(5, solution.getSystemId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Register Solution Error: " + e.getMessage());
            return false;

        }

    }

    /**
     * Returns a nutrient solution by its ID.
     *
     * @param solutionId Solution ID.
     * @return NutrientSolution object if found.
     */
    public NutrientSolution getSolution(int solutionId) {

        String sql = """
                SELECT *
                FROM nutrient_solution
                WHERE id_solution = ?
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, solutionId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                NutrientSolution solution = new NutrientSolution();

                solution.setSolutionId(result.getInt("id_solution"));
                solution.setPreparationDate(result.getDate("preparation_date"));
                solution.setConductivity(result.getDouble("conductivity"));
                solution.setTemperature(result.getDouble("temperature"));
                solution.setLevelPh(result.getDouble("level_ph"));
                solution.setSystemId(result.getInt("id_system"));

                return solution;
            }

        } catch (SQLException e) {

            System.out.println("Get Solution Error: " + e.getMessage());

        }

        return null;
    }

    /**
     * Updates a nutrient solution.
     *
     * @param solution NutrientSolution object.
     * @return true if the update was successful.
     */
    public boolean updateSolution(NutrientSolution solution) {

        String sql = """
                UPDATE nutrient_solution
                SET preparation_date = ?,
                    conductivity = ?,
                    temperature = ?,
                    level_ph = ?,
                    id_system = ?
                WHERE id_solution = ?
                """;

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setDate(1, solution.getPreparationDate());
            statement.setDouble(2, solution.getConductivity());
            statement.setDouble(3, solution.getTemperature());
            statement.setDouble(4, solution.getLevelPh());
            statement.setInt(5, solution.getSystemId());
            statement.setInt(6, solution.getSolutionId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Update Solution Error: " + e.getMessage());
            return false;

        }

    }

    /**
     * Deletes a nutrient solution.
     *
     * @param solutionId Solution ID.
     * @return true if the solution was deleted.
     */
    public boolean deleteSolution(int solutionId) {

        String sql = "DELETE FROM nutrient_solution WHERE id_solution = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, solutionId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Delete Solution Error: " + e.getMessage());
            return false;

        }

    }
    public java.util.ArrayList<NutrientSolution> getAllSolutions() {

    java.util.ArrayList<NutrientSolution> list = new java.util.ArrayList<>();

    String sql = """
            SELECT *
            FROM nutrient_solution
            ORDER BY id_solution DESC
            """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        while (result.next()) {

            NutrientSolution solution = new NutrientSolution();

            solution.setSolutionId(result.getInt("id_solution"));
            solution.setPreparationDate(result.getDate("preparation_date"));
            solution.setConductivity(result.getDouble("conductivity"));
            solution.setTemperature(result.getDouble("temperature"));
            solution.setLevelPh(result.getDouble("level_ph"));
            solution.setSystemId(result.getInt("id_system"));

            list.add(solution);

        }

    } catch (SQLException e) {

        System.out.println("Get All Solutions Error: " + e.getMessage());

    }

    return list;

}
    
}
