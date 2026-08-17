package combat;

import model.Model;
import model.Unit;
import model.Weapon;
import result.*;
import util.Dice;

import java.util.ArrayList;
import java.util.List;

/**
 *Class responsible for the different combat phases
 */
public class CombatSimulator {
    public BattleResult simulateBattle(Unit attacker, Unit defender, CombatOptions options) {
        BattleResult result = new BattleResult(attacker, defender);
        movementAttacker(attacker, options);
        result.addPhase(shooting(attacker, defender, options.getDefenderAllocationStrategy()));
        if (options.attackerWantsToCharge()) {
            ChargeResult chargeResultAttacker = charge(attacker, defender, options.getDistance());
            result.addPhase(chargeResultAttacker);
            if (chargeResultAttacker.isSuccessful()) {
                result.addPhase(melee(attacker, defender, options.getDefenderAllocationStrategy()));
                result.addPhase(melee(defender, attacker, options.getAttackerAllocationStrategy()));
                return result;
            }
        }
        result.addPhase(shooting(defender, attacker, options.getAttackerAllocationStrategy()));
        movementDefender(defender, options);
        if (options.defenderWantsToCharge()) {
            ChargeResult chargeResultDefender = charge(defender, attacker, options.getDistance());
            result.addPhase(chargeResultDefender);
            if (chargeResultDefender.isSuccessful()) {
                result.addPhase(melee(defender, attacker, options.getDefenderAllocationStrategy()));
                result.addPhase(melee(attacker, defender, options.getAttackerAllocationStrategy()));
            }
        }
        return result;
    }
    public void movementAttacker(Unit unit, CombatOptions options){
        if(options.attackerWantsToCharge()){
            options.setDistance(options.getDistance() - unit.getModels().get(0).getMovement());
        }
    }
    public void movementDefender(Unit unit, CombatOptions options){
        if(options.defenderWantsToCharge()){
            options.setDistance(options.getDistance() - unit.getModels().get(0).getMovement());
        }
    }

    /**
     * Method that represents an entire shooting phase of an attacking model vs. a defending model
     * @param attacker attacking unit
     * @param defender defending unit
     * @return a ShootingPhaseResult object containing the list of AttackResults
     */
    public CombatPhaseResult shooting(Unit attacker, Unit defender, AllocationStrategy strategy) {
        //TODO Implement Range statistic and distance between Units
        List<AttackResult> atkList = new ArrayList<>();
        for(Model model : attacker.getModels()){
            for(Weapon weapon : model.getRangedWeapons()){
                AttackResult currentAttack = resolveWeaponAttack(weapon, defender, strategy);
                atkList.add(currentAttack);
            }
        }
        return new CombatPhaseResult(PhaseType.SHOOTING, atkList,attacker,defender);
    }

    public CombatPhaseResult melee(Unit attacker, Unit defender, AllocationStrategy strategy){
        //ToDO melee combat
        List<AttackResult> atkList = new ArrayList<>();
        for(Model model : attacker.getModels()){
            for(Weapon weapon : model.getMeleeWeapons()){
                AttackResult currentAttack = resolveWeaponAttack(weapon, defender, strategy);
                atkList.add(currentAttack);
            }
        }
        return new CombatPhaseResult(PhaseType.MELEE, atkList,attacker,defender);
    }

    public ChargeResult charge(Unit charger, Unit defender, int distance){
        int chargeRoll = Dice.roll(6) + Dice.roll(6);
        return new ChargeResult(PhaseType.CHARGE, distance, chargeRoll, charger, defender);
    }


    /**
     * Method that resolves a single attack
     * @param weapon attacking weapon
     * @param defender defending model
     * @return result.AttackResult object with the number of hits, wounds and damage done in this attack
     */
    private AttackResult resolveWeaponAttack(Weapon weapon, Unit defender, AllocationStrategy strategy) {
        AttackResult atkResult = new AttackResult(weapon);
        atkResult.setAttacks(weapon.getAttacks());
        atkResult.setHits(CombatRules.rollHits(weapon));
        atkResult.setWounds(CombatRules.rollWounds(atkResult.getHits(), weapon, defender.getModels().get(0)));
        List<Integer> saveRolls = CombatRules.rollSaves(atkResult.getWounds());
        List<AllocationGroup> groups = AllocationGroup.initializeAllocationGroups(defender);
        AllocationHandler.orderAllocationGroups(groups, strategy);
        int currentGroupIndex = 0;
        for(int saveRoll : saveRolls){
            if(currentGroupIndex >= groups.size()){
                break;
            }
            Model targetModel = groups.get(currentGroupIndex).getModels().get(0);
            if(!(CombatRules.savingThrow(weapon.getAp(), targetModel, saveRoll))){
                atkResult.addUnsavedWounds();
                atkResult.addDamage(weapon.getDamage());
                atkResult.addDestroyedModel(resolveDamage(weapon.getDamage(), targetModel));
            }
            AllocationGroup.removeDeadModel(groups.get(currentGroupIndex), defender);



            while (currentGroupIndex < groups.size()
                    && groups.get(currentGroupIndex).getModels().isEmpty()) {
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
