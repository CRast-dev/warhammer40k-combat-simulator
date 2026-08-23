package com.warhammer.repository;

import com.warhammer.dto.CreateWeaponRequestDTO;
import com.warhammer.model.Damage;
import com.warhammer.model.Weapon;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class WeaponRepository {
    private final Connection connection;

    public WeaponRepository(Connection connection) {
        this.connection = connection;
    }

    public static List<Weapon> getWeaponByID(int id, Connection connection) throws SQLException {
        List<Weapon> weaponList = new ArrayList<>();
        String sql = "SELECT w.*, mw.quantity FROM weapons w JOIN model_weapons mw ON w.id = mw.weapon_id WHERE mw.model_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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


    public int createWeapon(CreateWeaponRequestDTO weapon) throws SQLException {
        String sql = """
                INSERT INTO weapons (name, flat_attacks, skill, strength, ap, flat_damage, damage_dice_side, damage_dice_count,
                range, weapon_type, atttack_dice_side, attack_dice_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING id
                """;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, weapon.name());
            statement.setInt(2, weapon.flatAttacks());
            statement.setInt(3,weapon.skill());
            statement.setInt(4, weapon.strength());
            statement.setInt(5, weapon.ap());
            statement.setInt(6, weapon.flatDamage());
            statement.setInt(7, weapon.damageDiceSide());
            statement.setInt(8, weapon.damageDiceCount());
            statement.setInt(9, weapon.range());
            statement.setString(10, weapon.weaponType().name());
            statement.setInt(11, weapon.attackDiceSide());
            statement.setInt(12, weapon.attackDiceCount());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("id");
                }
            }
            throw new SQLException("FAILED createWeapon METHOD IN UNITREPOSITORY");
        }
    }
}
