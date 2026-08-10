package model;

import java.util.List;
/**
 * A model.Unit of one or more Models.
 * A unit has a name along with a list of models in the unit
 */
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
