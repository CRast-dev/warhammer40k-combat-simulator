package combat;

import result.AllocationStrategy;

public class CombatOptions {
    private AllocationStrategy attackerAllocationStrategy;
    private AllocationStrategy defenderAllocationStrategy;
    private int startingDistance;
    private boolean attackerWantsToCharge;
    private boolean defenderWantsToCharge;

    public CombatOptions(AllocationStrategy attackerAllocationStrategy, AllocationStrategy defenderAllocationStrategy, int startingDistance, boolean attackerWantsToCharge, boolean defenderWantsToCharge) {
        this.attackerAllocationStrategy = attackerAllocationStrategy;
        this.defenderAllocationStrategy = defenderAllocationStrategy;
        this.startingDistance = startingDistance;
        this.attackerWantsToCharge = attackerWantsToCharge;
        this.defenderWantsToCharge = defenderWantsToCharge;
    }

    public AllocationStrategy getAttackerAllocationStrategy() {
        return attackerAllocationStrategy;
    }

    public AllocationStrategy getDefenderAllocationStrategy() {
        return defenderAllocationStrategy;
    }

    public int getStartingDistance() {
        return startingDistance;
    }

    public boolean attackerWantsToCharge() {
        return attackerWantsToCharge;
    }

    public boolean defenderWantsToCharge() {
        return defenderWantsToCharge;
    }
}
