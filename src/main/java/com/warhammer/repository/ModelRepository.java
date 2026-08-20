package com.warhammer.repository;

import java.sql.ResultSet;
import java.util.List;

import com.warhammer.model.Model;
import com.warhammer.model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModelRepository {

    public static Model getModelByID(int id, Connection connection) throws SQLException {
        String sql = "SELECT toughness, save, invuln_save, max_wounds, movement, is_character FROM models WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    List<Weapon> modelWeapons = WeaponRepository.getWeaponByID(id, connection);
                    return new Model(
                            resultSet.getInt("toughness"),
                            resultSet.getInt("save"),
                            resultSet.getInt("invuln_save"),
                            resultSet.getInt("max_wounds"),
                            resultSet.getInt("movement"),
                            modelWeapons,
                            resultSet.getBoolean("is_character")
                    );
                }
            }
        }

        return null;
    }
}