package com.warhammer.repository;

import com.warhammer.database.DatabaseConnection;
import com.warhammer.dto.CreateModelRequestDTO;
import com.warhammer.dto.CreateWeaponRequestDTO;
import com.warhammer.model.Model;
import com.warhammer.model.Unit;
import com.warhammer.dto.UnitSummary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UnitRepository {
    private final Connection connection;

    public UnitRepository(Connection connection) {
        this.connection = connection;
    }

    public int createUnit(String name) throws SQLException {
        String sql = "INSERT INTO units (name) VALUES (?) RETURNING id";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("id");
                }
                throw new SQLException("FAILED createUNIT METHOD IN UNITREPOSITORY");
            }
        }
    }


    public void addModel(int unitId, int modelId, int quantity) throws SQLException {
        String sql = """
                INSERT INTO unit_models (unit_id, model_id, quantity) VALUES (?, ?, ?)
                """;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, unitId);
            statement.setInt(2, modelId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        }

    }

    public Unit getUnitByID(int id) throws SQLException {
        String unitName = "";
        int unitID = 0;
        List<Model> models = new ArrayList<>();
        String sql = "SELECT * FROM units WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                unitID = resultSet.getInt("id");
                unitName = resultSet.getString("name");
                String modelSql = "SELECT model_id, model_count FROM unit_models WHERE unit_id = ?";
                try (PreparedStatement modelStatement = connection.prepareStatement(modelSql)) {
                    modelStatement.setInt(1,id);
                    try(ResultSet modelResult = modelStatement.executeQuery()){
                        while(modelResult.next()){
                            int modelID = modelResult.getInt("model_id");
                            int modelCount = modelResult.getInt("model_count");
                            Model model = ModelRepository.getModelByID(modelID,connection);
                            for(int i = 0; i < modelCount; i++){
                                models.add(new Model(model));
                            }
                        }
                    }
                }
            }
            return new Unit(unitID, unitName, models);
        }
    }

    public List<UnitSummary> findAll() throws SQLException{
        List<UnitSummary> units = new ArrayList<>();

        String sql = "SELECT id, name FROM units";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                units.add(new UnitSummary(id,name));
            }
        }
        return units;
    }



}
