package com.warhammer.repository;

import com.warhammer.database.DatabaseConnection;
import com.warhammer.model.Model;
import com.warhammer.model.Unit;
import com.warhammer.dto.UnitSummary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitRepository {

    public static Unit getUnitByID(int id) throws SQLException {
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

    public static List<UnitSummary> findAll() throws SQLException{
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
