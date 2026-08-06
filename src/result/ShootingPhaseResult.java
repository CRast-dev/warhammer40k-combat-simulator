package result;
import java.util.List;

/**
 * Class representing the results of an entire shooting phase
 */
public class ShootingPhaseResult extends PhaseResult{
    private final List<AttackResult> attacks;

    public ShootingPhaseResult(List<AttackResult> attacks) {
        super("Shooting");
        this.attacks = attacks;
    }
    public List<AttackResult> getAttacks() {
        return attacks;
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
    public int getTotalDamage(){
        int totalDamage = 0;
        for(AttackResult atk : attacks){
            totalDamage += atk.getDamage();
        }
        return totalDamage;
    }
}
