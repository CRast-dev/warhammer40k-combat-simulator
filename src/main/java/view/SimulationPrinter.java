package view;

import model.Unit;
import statistics.SimulationStatistics;

public class SimulationPrinter {
    public static void print(SimulationStatistics statistics, Unit attacker, Unit defender) {
        System.out.println("================================");
        System.out.println("Simulation Results");
        System.out.println("================================");

        System.out.println("Simulations: " + statistics.getSimulations());

        System.out.println();
        System.out.println("ATTACKER: " + attacker.getName());
        System.out.println("-------------------------------");
        //System.out.println("Average Attacks: " + statistics.getAverageAttackerAttacks());
        System.out.println("Average Hits: " + statistics.getAverageAttackerHits());
        System.out.println("Average Wounds: " + statistics.getAverageAttackerWounds());
        System.out.println("Average unsaved Wounds: " + statistics.getAverageAttackerUnsavedWounds());
        System.out.println("Average Damage: " + statistics.getAverageAttackerDamage());
        System.out.println("Average Models Destroyed: " + statistics.getAverageAttackerDestroyedModels());
        System.out.println("Successful Charges: " + statistics.getTotalAttackerSuccessfulCharges());

        System.out.println();
        System.out.println("DEFENDER: " + defender.getName());
        System.out.println("-------------------------------");
        //System.out.println("Average Attacks: " + statistics.getAverageDefenderAttacks());
        System.out.println("Average Hits: " + statistics.getAverageDefenderHits());
        System.out.println("Average Wounds: " + statistics.getAverageDefenderWounds());
        System.out.println("Average unsaved Wounds: " + statistics.getAverageDefenderUnsavedWounds());
        System.out.println("Average Damage: " + statistics.getAverageDefenderDamage());
        System.out.println("Average Models Destroyed: " + statistics.getAverageDefenderDestroyedModels());
        System.out.println("Successful Charges: " + statistics.getTotalDefenderSuccessfulCharges());

    }

}
