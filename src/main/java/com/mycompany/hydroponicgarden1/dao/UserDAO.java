/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hydroponicgarden1.dao;

import com.mycompany.hydroponicgarden1.config.DatabaseConnection;
import com.mycompany.hydroponicgarden1.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles all database operations related to users.
 *
 * @author Valeria
 */
public class UserDAO {

    // Database connection
    private Connection connection;

    /**
     * Creates a new UserDAO object.
     */
    public UserDAO() {
        connection = DatabaseConnection.getConnection();
    }

    /**
 * Registers a new user into the database.
 *
 * @param user User object.
 * @return true if the user was registered successfully.
 */
public boolean registerUser(User user) {

    String sql = """
        INSERT INTO record
        (name_user, last_name, birthdate, phone_mail, password_user)
        VALUES (?, ?, ?, ?, ?)
        """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setDate(3, user.getBirthDate());
        statement.setString(4, user.getEmailOrPhone());
        statement.setString(5, user.getPassword());

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Register Error: " + e.getMessage());
        return false;

    }

}
/**
 * Validates the user credentials.
 *
 * @param emailOrPhone User email or phone.
 * @param password User password.
 * @return true if the credentials are correct.
 */
public boolean login(String emailOrPhone, String password) {

    String sql = """
            SELECT *
            FROM record
            WHERE phone_mail = ?
            AND password_user = ?
            """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, emailOrPhone);
        statement.setString(2, password);

        return statement.executeQuery().next();

    } catch (SQLException e) {

        System.out.println("Login Error: " + e.getMessage());
        return false;

    }

}
/**
 * Returns a user by email or phone.
 *
 * @param emailOrPhone User email or phone.
 * @return User object if found; otherwise null.
 */
public User getUser(String emailOrPhone) {

    String sql = """
            SELECT *
            FROM record
            WHERE phone_mail = ?
            """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, emailOrPhone);

        ResultSet result = statement.executeQuery();

        if (result.next()) {

            User user = new User();

            user.setUserId(result.getInt("id_user"));
            user.setFirstName(result.getString("name_user"));
            user.setLastName(result.getString("last_name"));
            user.setBirthDate(result.getDate("birthdate"));
            user.setEmailOrPhone(result.getString("phone_mail"));
            user.setPassword(result.getString("password_user"));

            return user;
        }

    } catch (SQLException e) {

        System.out.println("Get User Error: " + e.getMessage());

    }

    return null;
}
/**
 * Updates the user information.
 *
 * @param user User object.
 * @return true if the update was successful.
 */
public boolean updateUser(User user) {

    String sql = """
            UPDATE record
            SET name_user = ?,
                last_name = ?,
                birthdate = ?,
                phone_mail = ?
            WHERE id_user = ?
            """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setDate(3, user.getBirthDate());
        statement.setString(4, user.getEmailOrPhone());
        statement.setInt(5, user.getUserId());

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Update Error: " + e.getMessage());
        return false;

    }
}
/**
 * Updates the user password.
 *
 * @param userId User ID.
 * @param password New password.
 * @return true if the password was updated.
 */
public boolean updatePassword(int userId, String password) {

    String sql = """
            UPDATE record
            SET password_user = ?
            WHERE id_user = ?
            """;

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, password);
        statement.setInt(2, userId);

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Password Update Error: " + e.getMessage());
        return false;

    }
}
/**
 * Deletes a user from the database.
 *
 * @param userId User ID.
 * @return true if the user was deleted.
 */
public boolean deleteUser(int userId) {

    String sql = "DELETE FROM record WHERE id_user = ?";

    try {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, userId);

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println("Delete Error: " + e.getMessage());
        return false;

    }
}
public User findByEmailOrPhone(String value) {

    User user = null;
    String sql = "SELECT * FROM record WHERE phone_mail = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, value);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            user = new User();

            user.setUserId(rs.getInt("id_user"));
            user.setFirstName(rs.getString("name_user"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthDate(rs.getDate("birthdate"));
            user.setEmailOrPhone(rs.getString("phone_mail"));
            user.setPassword(rs.getString("password_user"));
        }

    } catch (SQLException e) {
        System.out.println("Error find user: " + e.getMessage());
    }

    return user;
}
}

