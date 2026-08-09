package combat;

import model.Damage;
import model.Model;
import model.Unit;
import model.Weapon;
import org.junit.jupiter.api.Test;
import result.AllocationStrategy;
import result.ShootingPhaseResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombatSimulatorTest {
    @Test
    void excessDamageDoesNotCarryOver() {
        CombatSimulator combatSimulator = new CombatSimulator();
        List<Model> attackerModels = new ArrayList<>();
        List<Weapon> attackerModelsWeapons = new ArrayList<>();
        attackerModelsWeapons.add( new Weapon("Test Weapon",1,0,4,0,new Damage(0,0,100), 24));
        attackerModels.add(new Model(4,0,0,2,attackerModelsWeapons));
        List<Model> defenderModels = new ArrayList<>();
        defenderModels.add(new Model(4,7,7,2,null));
        Unit attacker = new Unit("TestAttacker", attackerModels);
        Unit defender = new Unit("TestDefender", defenderModels);
        AllocationStrategy strategy = AllocationStrategy.WORST_SAVE_FIRST;
        ShootingPhaseResult shootingPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        assertEquals(1, shootingPhaseResult.getDestroyedModels());
    }
    @Test
    void invulnSaveBlocksWoundsIfHighEnough() {
        CombatSimulator combatSimulator = new CombatSimulator();
        List<Model> attackerModels = new ArrayList<>();
        List<Weapon> attackerModelsWeapons = new ArrayList<>();
        attackerModelsWeapons.add( new Weapon("Test Weapon",5,0,4,-100,new Damage(0,0,100), 24));
        attackerModels.add(new Model(4,0,0,2,attackerModelsWeapons));
        List<Model> defenderModels = new ArrayList<>();
        defenderModels.add(new Model(4,0,-1,2,null));
        Unit attacker = new Unit("TestAttacker", attackerModels);
        Unit defender = new Unit("TestDefender", defenderModels);
        AllocationStrategy strategy = AllocationStrategy.WORST_SAVE_FIRST;
        ShootingPhaseResult shootingPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        assertEquals(0, shootingPhaseResult.getTotalUnsavedWounds());
    }
    @Test
    void diceDependantDamage() {
        CombatSimulator combatSimulator = new CombatSimulator();
        List<Model> attackerModels = new ArrayList<>();
        List<Weapon> attackerModelsWeapons = new ArrayList<>();
        attackerModelsWeapons.add( new Weapon("Test Weapon",5,0,4,-100,new Damage(1,6,0), 24));
        attackerModels.add(new Model(4,0,0,2,attackerModelsWeapons));
        List<Model> defenderModels = new ArrayList<>();
        defenderModels.add(new Model(4,7,7,2,null));
        Unit attacker = new Unit("TestAttacker", attackerModels);
        Unit defender = new Unit("TestDefender", defenderModels);
        AllocationStrategy strategy = AllocationStrategy.WORST_SAVE_FIRST;
        ShootingPhaseResult shootingPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        assertNotEquals(0, shootingPhaseResult.getTotalDamage());
    }



}