package com.warhammer.repository;

import java.sql.ResultSet;
import java.util.List;

import com.warhammer.dto.CreateModelRequestDTO;
import com.warhammer.model.Model;
import com.warhammer.model.Weapon;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
public class ModelRepository {
    private final DataSource dataSource;

    public ModelRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
    public int createModel(CreateModelRequestDTO model) throws SQLException {
        String sql = """
            INSERT INTO models (name, max_wounds, toughness, save, invuln_save, is_character, movement)
            VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id
            """;
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, model.name());
            statement.setInt(2, model.maxWounds());
            statement.setDouble(3, model.toughness());
            statement.setInt(4, model.save());
            statement.setInt(5, model.invulnSave());
            statement.setBoolean(6, model.isCharacter());
            statement.setInt(7, model.movement());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("id");
                }
            }
            throw new SQLException("FAILED createModel METHOD IN UNITREPOSITORY");
        }
    }


    public void addWeapon(int modelId, int weaponId, int quantity) throws SQLException {
        String sql = """
                INSERT INTO model_weapons (model_id, weapon_id, quantity) VALUES (?, ?, ?)
                """;
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, modelId);
            statement.setInt(2,weaponId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        }
    }
}