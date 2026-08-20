package com.warhammer.result;
import com.warhammer.model.Weapon;

/**
 * Represents the result of a single weapon attacking during a single phase.
 * Contains the Amount of hits, wounds and damage inflicted.
 */
public class AttackResult {
    private final Weapon weapon;
    private int attacks;
    private int hits;
    private int wounds;
    private int unsavedWounds;
    private int damage;
    private int destroyedModels;

    public AttackResult(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHits() {
        return hits;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getWounds() {
        return wounds;
    }

    public int getDamage() {
        return damage;
    }

    public int getDestroyedModels() {
        return destroyedModels;
    }

    public int getUnsavedWounds() {
        return unsavedWounds;
    }
    public void addDestroyedModel(int models){
        this.destroyedModels += models;
    }
    public void addDamage(int damage){
        this.damage += damage;
    }
    public void addUnsavedWounds(){
        this.unsavedWounds++;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }
    public void add(AttackResult result){
        this.attacks += result.getAttacks();
        this.hits += result.getHits();
        this.wounds += result.getWounds();
        this.unsavedWounds += result.getUnsavedWounds();
        this.damage += result.getDamage();
        this.destroyedModels += result.getDestroyedModels();
    }

    public void setAttacks(int attacks) {
        this.attacks = attacks;
    }

    public int getAttacks() {
        return attacks;
    }

    public void setUnsavedWounds(int unsavedWounds) {
        this.unsavedWounds = unsavedWounds;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDestroyedModels(int destroyedModels) {
        this.destroyedModels = destroyedModels;
    }
}
