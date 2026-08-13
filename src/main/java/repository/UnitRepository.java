package repository;

import database.DatabaseConnection;
import model.Model;
import model.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitRepository {

    public static Unit getUnitByID(int id) throws SQLException {
        String sql = "SELECT * FROM units WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return new Unit(resultSet.getString("name"),
                        ModelRepository.getModelByID(resultSet.getInt(id)));
            }
        }
    }

}
