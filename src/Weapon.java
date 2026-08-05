public class Weapon {
    private String name;
    private int attacks;
    private int strength;
    private int ballisticSkill;
    //ap is used in the GW way of being negative, like "-1"
    private int ap;
    private int damage;
    private int range;

    public Weapon(String name, int attacks, int strength, int ballisticSkill, int ap, int damage, int range){
        this.name = name;
        this.attacks = attacks;
        this.strength = strength;
        this.ballisticSkill = ballisticSkill;
        this.ap = ap;
        this.damage = damage;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getStrength() {
        return strength;
    }

    public int getBallisticSkill() {
        return ballisticSkill;
    }

    public int getAp() {
        return ap;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
