package com.warhammer.model;

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
    private int movement;



    public Model(int toughness, int save, int invulnSave, int maximumWounds, int movement, List<Weapon> weapons) {
        this.toughness = toughness;
        this.save = save;
        this.invulnSave = invulnSave;
        this.maximumWounds = maximumWounds;
        this.currentWounds = maximumWounds;
        this.weapons = weapons;
        this.movement = movement;
        this.character = false;
    }
    public Model(int toughness, int save, int invulnSave, int maximumWounds, int movement, List<Weapon> weapons, boolean character) {
        this.toughness = toughness;
        this.save = save;
        this.invulnSave = invulnSave;
        this.maximumWounds = maximumWounds;
        this.currentWounds = maximumWounds;
        this.weapons = weapons;
        this.movement = movement;
        this.character = character;
    }

    public Model(Model other) {
        //copy constrcutor. because same class i guess we dont need getter methods
        this.toughness = other.toughness;
        this.save = other.save;
        this.invulnSave = other.invulnSave;
        this.maximumWounds = other.maximumWounds;
        this.currentWounds = other.maximumWounds;
        this.weapons = new ArrayList<>(other.weapons);
        this.movement = other.movement;
        this.character = other.character;
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

    public int getMovement() {
        return movement;
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
