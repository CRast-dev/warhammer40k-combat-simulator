public class AttackResult {
    private int hits;
    private int wounds;
    private int damage;

    public AttackResult() {
    }

    public void add(AttackResult other){
        this.hits += other.hits;
        this.wounds += other.wounds;
        this.damage += other.damage;
    }

    public int getHits() {
        return hits;
    }

    public int getWounds() {
        return wounds;
    }

    public int getDamage() {
        return damage;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
