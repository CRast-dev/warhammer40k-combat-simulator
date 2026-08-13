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

    public static List<Model> getModelByID(int id) throws SQLException {
        List<Model> modelList = new ArrayList<>();
        String sql = "SELECT * FROM models WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()){
                    String sqlWeap = "SELECT weapon_id FROM model_weapons WHERE model_id = ?";
                    PreparedStatement statementWeap = connection.prepareStatement(sqlWeap);
                    List<Weapon> modelWeapons = new ArrayList<>();
                    statementWeap.setInt(1, id);
                    try (ResultSet resultSetWeap = statementWeap.executeQuery()) {
                        modelWeapons = WeaponRepository.getWeaponByID(resultSetWeap.getInt("weapon_id"));
                    }
                    //TODO make a for loop to reduce amount of queries
                    modelList.add(new Model(resultSet.getInt("toughness"),
                            resultSet.getInt("save"),
                            resultSet.getInt("invuln_save"),
                            resultSet.getInt("max_wounds"),
                            resultSet.getInt("movement"),
                            modelWeapons,
                            resultSet.getBoolean("is_character")));
                }
            }
        }
        return modelList;
    }
}