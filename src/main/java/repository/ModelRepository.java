package repository;

import database.DatabaseConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;
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