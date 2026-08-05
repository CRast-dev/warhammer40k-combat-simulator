public class CombatSimulator {
    public AttackResult shooting(Unit attacker, Unit defender) {
        AttackResult atktotal = new AttackResult();
        for(Model model : attacker.getModels()){
            for(Weapon weapon : model.getWeapons()){
                AttackResult currentAttack = resolveWeaponAttack(weapon, (Model) defender.getModels());
                atktotal.add(currentAttack);
            }
        }
        return atktotal;
    }


    private AttackResult resolveWeaponAttack(Weapon weapon, Model defender) {
        AttackResult atkResult = new AttackResult();
        atkResult.setHits(CombatRules.rollHits(weapon));
        atkResult.setWounds(CombatRules.rollWounds(atkResult.getHits(), weapon, defender));
        atkResult.setDamage(CombatRules.calculateDamage(atkResult.getWounds(), weapon.getDamage()));
        return atkResult;
    }

}
