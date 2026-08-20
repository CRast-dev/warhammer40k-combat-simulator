package testutil;
import com.warhammer.model.Damage;
import com.warhammer.model.Model;
import com.warhammer.model.Unit;
import com.warhammer.model.Weapon;

import java.util.ArrayList;
import java.util.List;

public class TestFactory {

    public static Unit createInvulnerableTestDefender(){
        Model model = new Model(4,-1,-1,2,0,null);
        List<Model> models = new ArrayList<>();
        models.add(model);
        return new Unit("Testattacker",models);
    }
    public static Unit createNoSaveTestDefender(){
        Model model = new Model(4,7,7,2,0,null);
        List<Model> models = new ArrayList<>();
        models.add(model);
        return new Unit("Testattacker",models);
    }
    public static Unit createTwoModelNoSaveTestDefender(){
        List<Model> models = new ArrayList<>();
        Model model1 = new Model(4,7,7,2,0,null);
        Model model2 = new Model(4,7,7,2,0,null);
        models.add(model1);
        models.add(model2);
        return new Unit("Testattacker",models);
    }
    public static Unit createTestAttacker(Weapon weapon){
        Model model = new Model(4,0,0,2,0,List.of(weapon));
        List<Model> models = new ArrayList<>();
        models.add(model);
        return new Unit("Testattacker",models);
    }
    public static Unit createTestAttacker(List<Weapon> weapons){
        Model model = new Model(4,0,0,2,0,weapons);
        List<Model> models = new ArrayList<>();
        models.add(model);
        return new Unit("Testattacker",models);
    }

    public static Weapon createRangedTestWeapon(){
        return new Weapon("Test Ranged Weapon",1,0,0,-1, 100, -100, new Damage(1,0,0), 24, Weapon.WeaponType.RANGED);
    }
    public static Weapon createMeleeTestWeapon(){
        return new Weapon("Test Ranged Weapon",1,0,0,-1, 100, -100, new Damage(2,0,0), 24, Weapon.WeaponType.MELEE);
    }
    public static Weapon createTestWeaponDiceDamage(){
        return new Weapon("Test Ranged Weapon",1,0,0,-1, 100, -100, new Damage(1,6,1), 24, Weapon.WeaponType.RANGED);
    }

    public static Weapon createOneBigAttackTestWeapon(){
        return new Weapon("Test One Big Attack Weapon",1,0,0,-1, 100, -100, new Damage(100,0,0), 24, Weapon.WeaponType.RANGED);
    }



}
