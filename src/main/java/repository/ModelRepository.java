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

    public static Model getModelByID(int id) throws SQLException {
        String sql = "SELECT * FROM models WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    List<Weapon> modelWeapons = WeaponRepository.getWeaponByID(id);
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