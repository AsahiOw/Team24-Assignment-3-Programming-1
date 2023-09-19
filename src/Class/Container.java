package Class;

import Enum.ContainerType;

import java.sql.Array;
import java.util.ArrayList;

public class Container {
    private String id;
    private static int nextId = 1;
    private double weight;
    private ContainerType type;
    private static ArrayList<Container> containers = new ArrayList<Container>();

    // constructor, getters, setters

    public Container(String id, double weight, ContainerType type) {
        this.id = "container" +nextId++;
        this.weight = weight;
        this.type = type;
        containers.add(this);
    }

    public static void getContainers() {
        System.out.println("List of Ship: ");
        for (Container c: containers) {
            System.out.println("\t" + c.toString());
        }
    }
    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", weight=" + weight +
                ", type=" + type +
                '}';
    }

    public String getId() {
        return id;
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
