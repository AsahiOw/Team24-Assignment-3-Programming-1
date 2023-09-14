package Class;

import Enum.ContainerType;
public class Container {
    private String id;
    private double weight;
    private ContainerType type;

    // constructor, getters, setters

    public Container(String id, double weight, ContainerType type) {
        this.id = id;
        this.weight = weight;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }
}
