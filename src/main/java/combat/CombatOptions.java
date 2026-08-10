package combat;

import combat.AllocationStrategy;

public class CombatOptions {
    private AllocationStrategy attackerAllocationStrategy;
    private AllocationStrategy defenderAllocationStrategy;
    private int distance;
    private boolean attackerWantsToCharge;
    private boolean defenderWantsToCharge;

    public CombatOptions(AllocationStrategy attackerAllocationStrategy, AllocationStrategy defenderAllocationStrategy, int distance, boolean attackerWantsToCharge, boolean defenderWantsToCharge) {
        this.attackerAllocationStrategy = attackerAllocationStrategy;
        this.defenderAllocationStrategy = defenderAllocationStrategy;
        this.distance = distance;
        this.attackerWantsToCharge = attackerWantsToCharge;
        this.defenderWantsToCharge = defenderWantsToCharge;
    }

    public AllocationStrategy getAttackerAllocationStrategy() {
        return attackerAllocationStrategy;
    }

    public AllocationStrategy getDefenderAllocationStrategy() {
        return defenderAllocationStrategy;
    }

    public int getDistance() {
        return distance;
    }

    public boolean attackerWantsToCharge() {
        return attackerWantsToCharge;
    }

    public boolean defenderWantsToCharge() {
        return defenderWantsToCharge;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
