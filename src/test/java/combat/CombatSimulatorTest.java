package combat;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.*;
import testutil.TestFactory;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CombatSimulatorTest {
    private CombatSimulator combatSimulator;
    private AllocationStrategy strategy;
    @BeforeEach
    void setUp(){
        combatSimulator = new CombatSimulator();
        strategy = AllocationStrategy.WORST_SAVE_FIRST;
    }

    @Test
    void excessDamageDoesNotCarryOver() {
        // Arrange
        Weapon weapon = TestFactory.createOneBigAttackTestWeapon();
        Unit attacker = TestFactory.createTestAttacker(weapon);
        Unit defender = TestFactory.createTwoModelNoSaveTestDefender();
        // Act
        CombatPhaseResult combatPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        // Assert
        assertEquals(1, combatPhaseResult.getDestroyedModels());
    }

    @Test
    void invulnSaveBlocksWoundsIfHighEnough() {
        // Arrange
        Weapon weapon = TestFactory.createOneBigAttackTestWeapon();
        Unit attacker = TestFactory.createTestAttacker(weapon);
        Unit defender = TestFactory.createInvulnerableTestDefender();
        // Act
        CombatPhaseResult combatPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        // Assert
        assertEquals(0, combatPhaseResult.getTotalUnsavedWounds());
    }
    @Test
    void diceDependantDamage() {
        // Arrange
        Weapon weapon = TestFactory.createTestWeaponDiceDamage();
        Unit attacker = TestFactory.createTestAttacker(weapon);
        Unit defender = TestFactory.createNoSaveTestDefender();
        // Act
        CombatPhaseResult combatPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        // Assert
        assertNotEquals(1, combatPhaseResult.getTotalDamage());
    }

    @Test
    void noRangedWeaponsInMeleePhase() {
        // Arrange
        Weapon weapon1 = TestFactory.createRangedTestWeapon();
        Weapon weapon2 = TestFactory.createMeleeTestWeapon();
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(weapon1);
        weapons.add(weapon2);
        Unit attacker = TestFactory.createTestAttacker(weapons);
        Unit defender = TestFactory.createNoSaveTestDefender();
        // Act
        CombatPhaseResult combatPhaseResult = combatSimulator.melee(attacker,defender, strategy);
        // Assert
        assertEquals(2, combatPhaseResult.getTotalDamage());
    }

    @Test
    void noMeleeWeaponsInShootingPhase() {
        // Arrange
        Weapon weapon1 = TestFactory.createRangedTestWeapon();
        Weapon weapon2 = TestFactory.createMeleeTestWeapon();
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(weapon1);
        weapons.add(weapon2);
        Unit attacker = TestFactory.createTestAttacker(weapons);
        Unit defender = TestFactory.createNoSaveTestDefender();
        // Act
        CombatPhaseResult combatPhaseResult = combatSimulator.shooting(attacker,defender, strategy);
        // Assert
        assertEquals(1, combatPhaseResult.getTotalDamage());
    }


}