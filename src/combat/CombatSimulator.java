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
                AttackResult currentAttack = resolveWeaponAttack(weapon, (Model) defender.getModels().get(0));
                atkList.add(currentAttack);
            }
        }
        return new ShootingPhaseResult(atkList);
    }


    /**
     * Method that resolves a single attack
     * @param weapon attacking weapon
     * @param defender defending model
     * @return result.AttackResult object with the number of hits, wounds and damage done in this attack
     */
    private AttackResult resolveWeaponAttack(Weapon weapon, Model defender) {
        AttackResult atkResult = new AttackResult(weapon);
        atkResult.setHits(CombatRules.rollHits(weapon));
        atkResult.setWounds(CombatRules.rollWounds(atkResult.getHits(), weapon, defender));
        atkResult.setDamage(CombatRules.calculateDamage(atkResult.getWounds(), weapon.getDamage()));
        return atkResult;
    }

}
