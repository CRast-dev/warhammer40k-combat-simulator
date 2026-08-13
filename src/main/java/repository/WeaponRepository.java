package repository;

import database.DatabaseConnection;
import model.Damage;
import model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeaponRepository {
    public static List<Weapon> getWeaponByID(int id) throws SQLException {
        List<Weapon> weaponList = new ArrayList<>();
        String sql = "SELECT w.*, mw.quantity FROM weapons w JOIN model_weapons mw ON w.id = mw.weapon_id WHERE mw.model_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Weapon.WeaponType weaponType = Weapon.WeaponType.valueOf(resultSet.getString("weapon_type"));
                    Weapon weapon = new Weapon(
                            resultSet.getString("name"),
                            resultSet.getInt("flat_attacks"),
                            resultSet.getInt("attack_dice_side"),
                            resultSet.getInt("attack_dice_count"),
                            resultSet.getInt("skill"),
                            resultSet.getInt("strength"),
                            resultSet.getInt("ap"),
                            new Damage(
                                    resultSet.getInt("flat_damage"),
                                    resultSet.getInt("damage_dice_side"),
                                    resultSet.getInt("damage_dice_count")
                            ),
                            resultSet.getInt("range"),
                            weaponType);
                    int quantity = resultSet.getInt("quantity");
                    for (int i = 0; i < quantity; i++) {
                        weaponList.add(weapon);
                    }
                }
            }
        }
        return weaponList;
    }
}
