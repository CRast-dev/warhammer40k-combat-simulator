package combat;
import java.util.List;
import result.AllocationStrategy;

public class AllocationHandler {

    public static List<AllocationGroup> orderAllocationGroups(List<AllocationGroup> groups, AllocationStrategy strategy){
        //Characters must always be before non-character allocationgroups
        //Characters always have their own allocation group with just them as the only Model in it
        if(strategy == AllocationStrategy.WORST_SAVE_FIRST){
            groups = orderWorstSaveFirst(groups);
        } else if (strategy == AllocationStrategy.BEST_SAVE_FIRST) {
            groups = orderBestSaveFirst(groups);
        }
        return groups;
    }

    private static List<AllocationGroup> orderWorstSaveFirst(List<AllocationGroup> groups){

    }



    private static List<AllocationGroup> orderBestSaveFirst(List<AllocationGroup> groups){

    }

}
