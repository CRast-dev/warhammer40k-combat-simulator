package result;
import statistics.CombatStatistics;
import model.Unit;
import java.util.List;
import java.util.ArrayList;

/**
 * Class representing the results of the entire battle
 */
public class BattleResult{
    private final Unit attacker;
    private final Unit defender;
    private final List<PhaseResult> phases;

    public BattleResult(Unit attacker, Unit defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.phases = new ArrayList<>();
    }

    public void addPhase(PhaseResult phase){
        phases.add(phase);
    }

    public List<PhaseResult> getPhases() {
        return phases;
    }

    public Unit getAttacker() {
        return attacker;
    }
    public Unit getDefender() {
        return defender;
    }
    public CombatStatistics getAttackerStatistic(){
        CombatStatistics statistics = new CombatStatistics();
        for(PhaseResult phase : phases){
            if(phase instanceof CombatPhaseResult combatPhase){
                if(combatPhase.getAttacker() == attacker){
                    statistics.add(combatPhase);
                }
            }
        }
        return statistics;
    }
    public CombatStatistics getDefenderStatistic(){
        CombatStatistics statistics = new CombatStatistics();
        for(PhaseResult phase : phases){
            if(phase instanceof CombatPhaseResult combatPhase){
                if(combatPhase.getAttacker() == defender){
                    statistics.add(combatPhase);
                }
            }
        }
        return statistics;
    }

    public int getSuccessfulCharges(Unit unit){
        int successfulCharges = 0;
        for(PhaseResult phase : phases){
            if(phase instanceof ChargeResult charge){
                if(charge.isSuccessful()){
                    successfulCharges++;
                }
            }
        }
        return successfulCharges;
    }

}
