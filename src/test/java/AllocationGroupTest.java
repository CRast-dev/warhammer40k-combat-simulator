import java.util.ArrayList;
import java.util.List;
import combat.*;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AllocationGroupTest {
    @Test
    void modelsWithSameCharacteristicsAreInSameGroup() {
        List<Model> testModels = new ArrayList<>();
        testModels.add(new Model(4,3,0,2,0,null));
        testModels.add(new Model(4,3,0,2,0,null));
        testModels.add(new Model(4,3,0,2,0,null));
        Unit unit = new Unit("Test Model", testModels);
        List<AllocationGroup> groups = AllocationGroup.initializeAllocationGroups(unit);

        assertEquals(1, groups.size());
        assertEquals(3, groups.get(0).getModels().size());
    }
    @Test
    void modelsWithDifferentCharacteristicsAreInDifferentGroup() {
        List<Model> testModels = new ArrayList<>();
        testModels.add(new Model(4,3,0,2,0,null));
        testModels.add(new Model(4,5,0,2,0,null));
        testModels.add(new Model(4,0,0,2,0,null));
        Unit unit = new Unit("Test Model", testModels);
        List<AllocationGroup> groups = AllocationGroup.initializeAllocationGroups(unit);

        assertEquals(3, groups.size());
    }

}