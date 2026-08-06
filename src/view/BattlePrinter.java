package view;

import result.AttackResult;
import result.BattleResult;
import result.PhaseResult;
import result.ShootingPhaseResult;

/**
 * Class responsible for printing output to console
 */
public class BattlePrinter {


    /**
     * Stopgap measure to for the overloading of print so that java can look up which phase to print
     * @param phase the phase to print
     */
    public static void print(PhaseResult phase){
        if(phase instanceof ShootingPhaseResult shooting){
            print(shooting);
        }
    }


    public static void print(BattleResult battle){
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println(battle.getAttacker().getName()
                + " against "
                + battle.getDefender().getName());
        System.out.println("+++++++++++++++++++++++++++++++");
        for(PhaseResult phase : battle.getPhases()){
            print(phase);
        }
    }
    public static void print(ShootingPhaseResult shooting){
        System.out.println("Shooting Phase:");
        for(AttackResult attack : shooting.getAttacks()){
            print(attack);
        }
        System.out.println("_______________________________");
        System.out.println("Total Hits: " + shooting.getTotalHits());
        System.out.println("Total Wounds: " + shooting.getTotalWounds());
        System.out.println("Total Damage: " + shooting.getTotalDamage());
    }
    public static void print(AttackResult attack){
        System.out.println(attack.getWeapon());
        System.out.println("Hits: " + attack.getHits());
        System.out.println("Wounds: " + attack.getWounds());
        System.out.println("Damage: " + attack.getDamage() + "\n");
    }


}
