package model;

import java.util.ArrayList;
import java.util.List;
/**
 * A single model within a unit.
 * A model contains its woundcount along with the defensive parameters and the weapons
 * it is currently equipped with.
 */
public class Model {
    private int maximumWounds;
    private int currentWounds;
    private List<Weapon> weapons;
    private int toughness;
    private int save;
    private int invulnSave;
    private final boolean character;



    public Model(int toughness, int save, int invulnSave, int maximumWounds, List<Weapon> weapons) {
        this.toughness = toughness;
        this.save = save;
        this.invulnSave = invulnSave;
        this.maximumWounds = maximumWounds;
        this.currentWounds = maximumWounds;
        this.weapons = weapons;
        this.character = false;
    }
    public Model(int toughness, int save, int invulnSave, int maximumWounds, List<Weapon> weapons, boolean character) {
        this.toughness = toughness;
        this.save = save;
        this.invulnSave = invulnSave;
        this.maximumWounds = maximumWounds;
        this.currentWounds = maximumWounds;
        this.weapons = weapons;
        this.character = character;
    }

    public boolean isCharacter() {
        return character;
    }

    public int getToughness() {
        return toughness;
    }

    public int getSave() {
        return save;
    }

    public int getInvulnSave() {
        return invulnSave;
    }
    public int getMaximumWounds() {
        return maximumWounds;
    }

    public int getCurrentWounds() {
        return currentWounds;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }
    public List<Weapon> getRangedWeapons(){
        List<Weapon> rangedWeapons = new ArrayList<>();
        for(Weapon weapon : weapons){
            if(weapon.getType() == Weapon.WeaponType.RANGED){
                rangedWeapons.add(weapon);
            }
        }
        return rangedWeapons;
    }
    public List<Weapon> getMeleeWeapons(){
        List<Weapon> meleeWeapons = new ArrayList<>();
        for(Weapon weapon : weapons){
            if(weapon.getType() == Weapon.WeaponType.MELEE){
                meleeWeapons.add(weapon);
            }
        }
        return meleeWeapons;
    }

    public void setCurrentWounds(int currentWounds) {
        this.currentWounds = currentWounds;
    }
}
