package combat;

import model.Model;
import model.Unit;
import model.Weapon;
import result.AttackResult;
import result.BattleResult;
import result.ShootingPhaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 *Class responsible for the different combat phases
 */
public class CombatSimulator {


    public BattleResult simulateBattle(Unit attacker, Unit defender){
        BattleResult result = new BattleResult(attacker, defender);
        result.addPhase(shooting(attacker, defender));
        result.addPhase(shooting(defender, attacker));
        return result;
    }

    /**
     * Method that represents an entire shooting phase of an attacking model vs. a defending model
     * @param attacker attacking unit
     * @param defender defending unit
     * @return a ShootingPhaseResult object containing the list of AttackResults
     */
    public ShootingPhaseResult shooting(Unit attacker, Unit defender) {
        //TODO Implement Range statistic and distance between Units
        List<AttackResult> atkList = new ArrayList<>();
        for(Model model : attacker.getModels()){
            for(Weapon weapon : model.getWeapons()){
                //TODO Decide which Model gets attacked or make a units model list be ordered
                AttackResult currentAttack = resolveWeaponAttack(weapon, defender);
                atkList.add(currentAttack);
            }
        }
        return new ShootingPhaseResult(atkList,attacker,defender);
    }


    /**
     * Method that resolves a single attack
     * @param weapon attacking weapon
     * @param defender defending model
     * @return result.AttackResult object with the number of hits, wounds and damage done in this attack
     */
    private AttackResult resolveWeaponAttack(Weapon weapon, Unit defender) {
        AttackResult atkResult = new AttackResult(weapon);
        atkResult.setHits(CombatRules.rollHits(weapon));
        atkResult.setWounds(CombatRules.rollWounds(atkResult.getHits(), weapon, defender.getModels().get(0)));
        List<Integer> saveRolls = CombatRules.rollSaves(atkResult.getWounds());
        List<AllocationGroup> groups = AllocationGroup.initializeAllocationGroups(defender);
        //TODO: orderAllocationGroups() method
        int currentGroupIndex = 0;
        for(int saveRoll : saveRolls){
            if(!(CombatRules.savingThrow(weapon.getAp(), groups.get(currentGroupIndex).getModels().get(0), saveRoll))){
                //Save failed
                atkResult.addUnsavedWounds();
                atkResult.addDamage(weapon.getDamage());
                atkResult.addDestroyedModel(resolveDamage(weapon.getDamage(), groups.get(currentGroupIndex).getModels().get(0)));
            }
            AllocationGroup.removeDeadModel(groups.get(currentGroupIndex));
            if(groups.get(currentGroupIndex).getModels().isEmpty()){
                currentGroupIndex++;
            }
            if(currentGroupIndex >= groups.size()){
                break;
            }
        }

        return atkResult;
    }


    private int resolveDamage(int damage, Model defender){
        int currentWounds = defender.getCurrentWounds();
        defender.setCurrentWounds(currentWounds - damage);
        if (defender.getCurrentWounds() <= 0){
            return 1;
        }else{
            return 0;
        }
    }

}
