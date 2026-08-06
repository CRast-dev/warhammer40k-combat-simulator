package model;

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



    public Model(int toughness, int save, int invulnSave, int maximumWounds, List<Weapon> weapons) {
        this.toughness = toughness;
        this.save = save;
        this.invulnSave = invulnSave;
        this.maximumWounds = maximumWounds;
        this.currentWounds = maximumWounds;
        this.weapons = weapons;
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

    public void setCurrentWounds(int currentWounds) {
        this.currentWounds = currentWounds;
    }
}
