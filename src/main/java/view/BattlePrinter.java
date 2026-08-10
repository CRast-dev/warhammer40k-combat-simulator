package view;

import result.*;

/**
 * Class responsible for printing output to console
 */
public class BattlePrinter {


    /**
     * Stopgap measure to for the overloading of print so that java can look up which phase to print
     * @param phase the phase to print
     */
    public static void print(PhaseResult phase, PrintLevel level){
        if(phase instanceof CombatPhaseResult combat){
            print(combat, level);
        } else if (phase instanceof ChargeResult charge) {
            print(charge, level);
        }
    }
    public static void print(BattleResult battle, PrintLevel level){
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(battle.getAttacker().getName()
                + " against "
                + battle.getDefender().getName());
        System.out.println("+++++++++++++++++++++++++++++++" + "\n");
        for(PhaseResult phase : battle.getPhases()){
            print(phase, level);
        }
    }
    public static void print(CombatPhaseResult shooting, PrintLevel level){
        System.out.println(shooting.getPhaseName() + " Phase:");
        System.out.println("Attacker: " + shooting.getAttacker().getName());
        System.out.println("Defender: " + shooting.getDefender().getName());
        if(level == PrintLevel.DETAILED){
            for(AttackResult attack : shooting.getAttacks()){
                print(attack);
            }
        }
        System.out.println("-------------------------------");
        System.out.println("Total Hits: " + shooting.getTotalHits());
        System.out.println("Total Wounds: " + shooting.getTotalWounds());
        System.out.println("Total unsaved Wounds: " + shooting.getTotalUnsavedWounds());
        System.out.println("Total Damage: " + shooting.getTotalDamage());
        System.out.println("Destroyed Models: " + shooting.getDestroyedModels());
        System.out.println("-------------------------------" +"\n");
    }
    public static void print(ChargeResult charge, PrintLevel level){
        System.out.println("Charge Phase:");
        System.out.println(charge.getAttacker().getName() + " attempts to charge at a distance of " + charge.getDistance() + "!");
        System.out.println("The Charge Roll is a " + charge.getChargeRoll() + "!");
        String outcome = "";
        if (charge.isSuccessful()){
            outcome = "successful";
        }else{
            outcome = "not successful";
        }
        System.out.println("The Charge Roll was " + outcome + "!" + "\n");
    }


    public static void print(AttackResult attack){
        System.out.println(attack.getWeapon().getName());
        System.out.println("Hits: " + attack.getHits());
        System.out.println("Wounds: " + attack.getWounds());
        System.out.println("Unsaved Wounds: " + attack.getUnsavedWounds());
        System.out.println("Damage: " + attack.getDamage() + "\n");
    }
}
