import model.*;
import combat.CombatSimulator;
import result.BattleResult;
import view.BattlePrinter;
import view.PrintLevel;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        CombatSimulator combatSimulator = new CombatSimulator();
        List<Weapon> helverinWeapons = new ArrayList<>();
        helverinWeapons.add(new Weapon("Armiger Autocannon", 4, 3, 9,-1,new Damage(0,0,3), 48));
        List<Model> helverinModels = new ArrayList<>();
        helverinModels.add(new Model(9,3,5,14,helverinWeapons));

        List<Weapon> spaceMarineWeapons = new ArrayList<>();
        spaceMarineWeapons.add( new Weapon("Bolt rifle",2,3,4,-1,new Damage(0,0,1), 24));
        spaceMarineWeapons.add(new Weapon("Bolt pistol", 1,3,4,0,new Damage(0,0,1),12));
        List<Model> intercessorModels = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            intercessorModels.add(new Model(4,3,0,2,spaceMarineWeapons));
        }
        Unit attacker = new Unit("Armiger Helverin", helverinModels);
        Unit defender = new Unit("Space Marine Intercessor", intercessorModels);

        BattleResult battle = combatSimulator.simulateBattle(attacker,defender);
        BattlePrinter.print(battle, PrintLevel.SUMMARY);
    }
}
