import combat.*;
import model.*;
import result.*;
import view.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        
        CombatOptions options = new CombatOptions(AllocationStrategy.WORST_SAVE_FIRST, AllocationStrategy.WORST_SAVE_FIRST, 14, false, true);
        CombatSimulator combatSimulator = new CombatSimulator();
        List<Weapon> helverinWeapons = new ArrayList<>();
        helverinWeapons.add(new Weapon("Armiger Autocannon", 4, 3, 9,-1,
                new Damage(0,0,3),
                48, Weapon.WeaponType.RANGED));
        helverinWeapons.add(new Weapon("Armiger Autocannon", 4, 3, 9,-1,
                new Damage(0,0,3),
                48, Weapon.WeaponType.RANGED));
        helverinWeapons.add(new Weapon("Armoured feet", 4, 3, 6,0,
                new Damage(0,0,1),
                0, Weapon.WeaponType.MELEE));
        List<Model> helverinModels = new ArrayList<>();
        helverinModels.add(new Model(9,3,5,14, 12, helverinWeapons));
        List<Weapon> spaceMarineWeapons = new ArrayList<>();
        spaceMarineWeapons.add( new Weapon("Bolt rifle",2,3,4,-1,
                new Damage(0,0,1),
                24, Weapon.WeaponType.RANGED));
        spaceMarineWeapons.add(new Weapon("Bolt pistol", 1,3,4,0,
                new Damage(0,0,1),
                12, Weapon.WeaponType.RANGED));
        spaceMarineWeapons.add(new Weapon("Close combat weapon", 3,3,4,0,
                new Damage(0,0,1),
                0, Weapon.WeaponType.MELEE));
        List<Model> intercessorModels = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            intercessorModels.add(new Model(4,3,0,2, 6, spaceMarineWeapons));
        }
        Unit attacker = new Unit("Armiger Helverin", helverinModels);
        Unit defender = new Unit("Space Marine Intercessor", intercessorModels);
        BattleResult battle = combatSimulator.simulateBattle(attacker,defender, options);
        BattlePrinter.print(battle, PrintLevel.SUMMARY);
    }
}
