package com.warhammer.combat;
import com.warhammer.model.Unit;
import com.warhammer.model.Model;

import java.util.ArrayList;
import java.util.List;


public class AllocationGroup {
    private List<Model> models;

    public AllocationGroup (){
        models = new ArrayList<>();
    }
    public void addModel(Model model){
        models.add(model);
    }

    public boolean belongsToSameGroup(Model model){
        if(models.isEmpty()){
            return false;
        }
        Model firstModel = models.get(0);
        return (model.isCharacter() == firstModel.isCharacter()
                && model.getMaximumWounds() == firstModel.getMaximumWounds()
                && model.getSave() == firstModel.getSave()
                && model.getInvulnSave() == firstModel.getInvulnSave());
    }
    public boolean hasDamagedModel(){
        return models.stream().anyMatch(model -> model.getCurrentWounds() < model.getMaximumWounds());
    };
    public static void removeDeadModel(AllocationGroup group, Unit unit){
        //filter all dead models into this new list
        List<Model> deadModels = group.getModels().stream().filter(model -> model.getCurrentWounds() <= 0).toList();
        for(Model model : deadModels){
            //remove the dead model from both the allocation group AND the unit
            group.getModels().remove(model);
            unit.removeModel(model);
        }

        group.getModels().removeIf(model -> model.getCurrentWounds() <= 0);
    }

    public static List<AllocationGroup> initializeAllocationGroups(Unit defender){
        List<AllocationGroup> groups = new ArrayList<>();
        for(Model model : defender.getModels()){
            AllocationGroup matchingGroup = null;
            for (AllocationGroup group : groups){
                if(group.belongsToSameGroup(model)){
                    matchingGroup = group;
                    break;
                }
            }
            if(matchingGroup == null){
                matchingGroup = new AllocationGroup();
                groups.add(matchingGroup);
            }
            matchingGroup.addModel(model);
        }
        return groups;
    }



    public List<Model> getModels(){
        return models;
    }
}
