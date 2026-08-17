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


    public CombatStatistics getStatistic(Unit unit){
        CombatStatistics statistics = new CombatStatistics();
        for(PhaseResult phase : phases){
            phase.addStatisticFor(unit, statistics);
        }
        return statistics;
    }

}
