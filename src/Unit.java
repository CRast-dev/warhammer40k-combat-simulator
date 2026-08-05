import java.util.List;

public class Unit {
    private String name;
    private List<Model> models;

    public Unit(String name,  List<Model> models) {
        this.name = name;
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public List<Model> getModels() {
        return models;
    }
}
