package result;
import model.Unit;

import java.util.List;

/**
 * Class representing the results of an entire shooting phase
 */
public class ShootingPhaseResult extends PhaseResult{
    private final List<AttackResult> attacks;
    private final Unit attacker;
    private final Unit defender;

    public ShootingPhaseResult(List<AttackResult> attacks, Unit attacker, Unit defender) {
        super(PhaseType.SHOOTING);
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
    public List<AttackResult> getAttacks() {
        return attacks;
    }

    public Unit getAttacker() {
        return attacker;
    }

    public Unit getDefender() {
        return defender;
    }
}
