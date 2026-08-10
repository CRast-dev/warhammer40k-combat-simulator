package result;

import model.Unit;

public class ChargeResult extends PhaseResult{

    private final int chargeRoll;
    private final int distance;
    private final boolean successful;
    private final Unit attacker;
    private final Unit defender;

    public ChargeResult(PhaseType type, int distance, int chargeRoll, Unit attacker, Unit defender) {
        super(type);
        this.distance = distance;
        this.chargeRoll = chargeRoll;
        this.attacker = attacker;
        this.defender = defender;
        if(chargeRoll >= distance){
            this.successful = true;
        }else{
            this.successful = false;
        }
    }

    public int getChargeRoll() {
        return chargeRoll;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Unit getAttacker() {
        return attacker;
    }

    public Unit getDefender() {
        return defender;
    }

    public int getDistance() {
        return distance;
    }
}
