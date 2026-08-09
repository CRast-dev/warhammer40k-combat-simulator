package combat;
import model.Unit;
import model.Model;

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
    public static void removeDeadModel(AllocationGroup group){
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



    public void removeDeadModel(){
        models.removeIf(model -> model.getCurrentWounds() <= 0);
    }


    public List<Model> getModels(){
        return models;
    }
}
