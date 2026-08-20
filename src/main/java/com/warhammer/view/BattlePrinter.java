package com.warhammer.view;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.warhammer.result.AttackResult;
import com.warhammer.result.BattleResult;
import com.warhammer.result.CombatPhaseResult;
import com.warhammer.result.PhaseResult;

/**
 * Class responsible for printing output to console
 */
public class BattlePrinter {


    /**
     * Stopgap measure to for the overloading of print so that java can look up which phase to print
     * @param phase the phase to print
     */
    public static void print(PhaseResult phase, PrintLevel level){
        phase.print(level);
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


    public static void printWeaponSummary(CombatPhaseResult shooting){

        Map<String, List<AttackResult>> attacksByWeapon = new LinkedHashMap<>();

        for (AttackResult attack : shooting.getAttacks()) {
            String weaponName = attack.getWeapon().getName();

            List<AttackResult> weaponAttacks = attacksByWeapon.get(weaponName);

            if (weaponAttacks == null) {
                weaponAttacks = new ArrayList<>();
                attacksByWeapon.put(weaponName, weaponAttacks);
            }

            weaponAttacks.add(attack);
        }
        for (Map.Entry<String, List<AttackResult>> entry : attacksByWeapon.entrySet()){
            AttackResult weaponResult = mergeWeaponAttacks(entry.getValue());
            System.out.println("Attacking with " + entry.getKey() + ":");
            System.out.println("Attacks: " + weaponResult.getAttacks());
            System.out.println("Hits: " + weaponResult.getHits());
            System.out.println("Wounds: " + weaponResult.getWounds());
            System.out.println("Unsaved Wounds: " + weaponResult.getUnsavedWounds());
            System.out.println("Damage: " + weaponResult.getDamage());
            System.out.println("Destroyed Models: " + weaponResult.getDestroyedModels());
            System.out.println();
        }


    }

    private static AttackResult mergeWeaponAttacks(List<AttackResult> attacks){
        AttackResult result = new AttackResult(attacks.get(0).getWeapon());
        for(AttackResult attack : attacks){
            result.add(attack);
        }
        return result;
    }


    public static void print(AttackResult attack){
        System.out.println(attack.getWeapon().getName());
        System.out.println("Attacks: " + attack.getAttacks());
        System.out.println("Hits: " + attack.getHits());
        System.out.println("Wounds: " + attack.getWounds());
        System.out.println("Unsaved Wounds: " + attack.getUnsavedWounds());
        System.out.println("Damage: " + attack.getDamage() + "\n");
    }
}
