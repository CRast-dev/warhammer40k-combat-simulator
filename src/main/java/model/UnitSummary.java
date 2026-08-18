package model;

public class UnitSummary {

    private final int id;
    private final String name;

    public UnitSummary(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
