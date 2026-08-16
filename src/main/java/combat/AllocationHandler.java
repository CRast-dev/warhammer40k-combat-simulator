package combat;
import java.util.List;

public class AllocationHandler {

    public static List<AllocationGroup> orderAllocationGroups(List<AllocationGroup> groups, AllocationStrategy strategy){
        //Characters must always be before non-character allocationgroups
        //Characters always have their own allocation group with just them as the only Model in it
        groups.sort((group1, group2) -> {
            boolean group1Damaged = group1.hasDamagedModel();
            boolean group2Damaged = group2.hasDamagedModel();

            if (group1Damaged && !group2Damaged) {
                return -1;
            }
            if (!group1Damaged && group2Damaged) {
                return 1;
            }
            int save1 = group1.getModels().get(0).getSave();
            int save2 = group2.getModels().get(0).getSave();

            if(strategy == AllocationStrategy.WORST_SAVE_FIRST){
                return Integer.compare(save2,save1);
            }
            //BEST_SAVE_FIRST case
            return Integer.compare(save1,save2);


        });
        return groups;
    }


}
