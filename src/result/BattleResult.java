package result;
import model.Unit;
import java.util.List;
import java.util.ArrayList;

/**
 * Class representing the results of the entire battle
 */
public class BattleResult extends PhaseResult{
    private final Unit attacker;
    private final Unit defender;
    private final List<PhaseResult> phases;

    public BattleResult(Unit attacker, Unit defender) {
        super("Battle");
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
}
