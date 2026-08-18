package result;

import model.Unit;
import statistics.CombatStatistics;
import view.PrintLevel;

public class ChargeResult extends PhaseResult{

    private final int chargeRoll;
    private final int distance;
    private final boolean successful;

    private final Unit defender;
    private final Unit attacker;

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

    @Override
    public void print(PrintLevel level) {
        System.out.println("Charge Phase:");
        System.out.println(this.getAttacker().getName() + " attempts to charge at a distance of " + this.getDistance() + "!");
        System.out.println("The Charge Roll is a " + this.getChargeRoll() + "!");
        String outcome = "";
        if (this.isSuccessful()){
            outcome = "successful";
        }else{
            outcome = "not successful";
        }
        System.out.println("The Charge Roll was " + outcome + "!" + "\n");
    }

    @Override
    public void addStatisticFor(Unit unit, CombatStatistics statistics) {
        if(attacker == unit){
            statistics.addCharge(this);
        }
    }
}
