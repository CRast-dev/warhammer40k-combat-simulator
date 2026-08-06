package result;
import model.Unit;

/**
 * Class representing the results of the entire battle
 */
public class BattleResult {
    private final Unit attacker;
    private final Unit defender;
    private ShootingPhaseResult shootingResult;

    public BattleResult(Unit attacker, Unit defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public Unit getAttacker() {
        return attacker;
    }

    public Unit getDefender() {
        return defender;
    }

    public ShootingPhaseResult getShootingResult() {
        return shootingResult;
    }

    public void setShootingResult(ShootingPhaseResult shootingResult) {
        this.shootingResult = shootingResult;
    }
}
