package result;
import model.Unit;
import statistics.CombatStatistics;
import view.BattlePrinter;
import view.PrintLevel;

import java.util.List;

/**
 * Class representing the results of an entire shooting phase
 */
public class CombatPhaseResult extends PhaseResult{
    private final List<AttackResult> attacks;
    private final Unit attacker;
    private final Unit defender;

    public CombatPhaseResult(PhaseType type, List<AttackResult> attacks, Unit attacker, Unit defender) {
        super(type);
        this.attacks = attacks;
        this.attacker = attacker;
        this.defender = defender;
    }
    public int getTotalHits(){
        int totalHits = 0;
        for(AttackResult atk : attacks){
            totalHits += atk.getHits();
        }
        return totalHits;
    }
    public int getTotalAttacks(){
        int totalAttacks = 0;
        for(AttackResult atk : attacks){
            totalAttacks += atk.getAttacks();
        }
        return totalAttacks;
    }
    public int getTotalWounds(){
        int totalWounds = 0;
        for(AttackResult atk : attacks){
            totalWounds += atk.getWounds();
        }
        return totalWounds;
    }
    public int getTotalUnsavedWounds(){
        int totalUnsavedWounds = 0;
        for(AttackResult atk : attacks){
            totalUnsavedWounds += atk.getUnsavedWounds();
        }
        return totalUnsavedWounds;
    }

    public int getTotalDamage(){
        int totalDamage = 0;
        for(AttackResult atk : attacks){
            totalDamage += atk.getDamage();
        }
        return totalDamage;
    }
    public int getDestroyedModels(){
        int destroyedModels = 0;
        for(AttackResult atk : attacks){
            destroyedModels += atk.getDestroyedModels();
        }
        return destroyedModels;
    }
    public List<AttackResult> getAttacks() {
        return attacks;
    }

    public Unit getAttacker() {
        return attacker;
    }

    public Unit getDefender() {
        return defender;
    }

    @Override
    public void print(PrintLevel level) {
        System.out.println(this.getPhaseName() + " Phase:");
        System.out.println("Attacker: " + attacker.getName());
        System.out.println("Defender: " + defender.getName());
        if(level == PrintLevel.DETAILED){
            for(AttackResult attack : attacks){
                BattlePrinter.print(attack);
            }
        }else if (level == PrintLevel.SUMMARY){
            System.out.println("-------------------------------");
            System.out.println("Total Attacks: " + this.getTotalAttacks());
            System.out.println("Total Hits: " + this.getTotalHits());
            System.out.println("Total Wounds: " + this.getTotalWounds());
            System.out.println("Total unsaved Wounds: " + this.getTotalUnsavedWounds());
            System.out.println("Total Damage: " + this.getTotalDamage());
            System.out.println("Destroyed Models: " + this.getDestroyedModels());
            System.out.println("-------------------------------" + "\n");
        } else if (level == PrintLevel.WEAPON_SUMMARY) {
            BattlePrinter.printWeaponSummary(this);
        }
    }

    @Override
    public void addStatisticFor(Unit unit, CombatStatistics statistics) {
        if(attacker == unit){
            statistics.add(this);
        }
    }
}
