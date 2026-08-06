package result;
import model.Weapon;

/**
 * Represents the result of a single weapon attacking during a single phase.
 * Contains the Amount of hits, wounds and damage inflicted.
 */
public class AttackResult {
    private final Weapon weapon;
    private int hits;
    private int wounds;
    private int unsavedWounds;
    private int damage;

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

    public int getUnsavedWounds() {
        return unsavedWounds;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public void setUnsavedWounds(int unsavedWounds) {
        this.unsavedWounds = unsavedWounds;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
