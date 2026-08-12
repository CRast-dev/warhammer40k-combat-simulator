package repository;

import database.DatabaseConnection;
import model.Damage;
import model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeaponRepository {
    public Weapon findWeaponByID(int id) throws SQLException{
        //use sql statement with "?" to indicate parameter
        String sql = "SELECT * FROM weapons WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        //prepare against sql injection?
            PreparedStatement statement = connection.prepareStatement(sql)){
            //replace parameter with the actual id we are looking for
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                //TODO some kind of toUpper() for the string so that "melee" gets correctly matched
                Weapon.WeaponType weaponType = Weapon.WeaponType.valueOf(resultSet.getString("weapon_type"));
                if(resultSet.next()){
                    return new Weapon(resultSet.getString("name"),
                            resultSet.getInt("flat_attacks"),
                            resultSet.getInt("attack_dice_side"),
                            resultSet.getInt("attack_dice_count"),
                            resultSet.getInt("skill"),
                            resultSet.getInt("strength"),
                            resultSet.getInt("ap"),
                            new Damage(resultSet.getInt("flat_damage"),
                                    resultSet.getInt("damage_dice_side"),
                                    resultSet.getInt("damage_dice_count")),
                            resultSet.getInt("range"),
                            weaponType);

                }
            }
        }
    return null;
    }
}
