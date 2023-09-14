package Class;

import Enum.ContainerType;
public class Container {
    private int id;
    private double weight;
    private ContainerType type;

    // constructor, getters, setters

    public Container(int id, double weight, ContainerType type) {
        this.id = id;
        this.weight = weight;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
