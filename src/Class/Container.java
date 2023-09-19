package Class;

import Enum.ContainerType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    private String id;
    private static int nextId = 1;
    private double weight;
    private ContainerType type;
    private Port currentPort;
    private static ArrayList<Container> containers = new ArrayList<Container>();

    public static void removeContainer(String idToRemove) {
        for (Container c: containers) {
            if (c.getId().equalsIgnoreCase(idToRemove)  ) {
                containers.remove(c);
                c.currentPort.removeContainer(c);
                c = null;
            }
            else {
                System.out.println("Container not found with id: " + idToRemove);
            }
        }
    }
    // constructor, getters, setters
    public Container(double weight, ContainerType type) {
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
    public void setPort(Port p) {
        this.currentPort = p;
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
    public static ContainerType matchContainerType(String containerType) {
        ContainerType type;
        if ("DRY".equalsIgnoreCase(containerType)) {
            type = ContainerType.DRY;
        } else if ("OPEN_TOP".equalsIgnoreCase(containerType)) {
            type = ContainerType.OPEN_TOP;
        } else if ("OPEN_SIDE".equalsIgnoreCase(containerType)) {
            type = ContainerType.OPEN_SIDE;
        } else if ("REFRIGERATED".equalsIgnoreCase(containerType)) {
            type = ContainerType.REFRIGERATED;
        } else if ("LIQUID".equalsIgnoreCase(containerType)) {
            type = ContainerType.LIQUID;
        } else {
            System.out.println("No matching type found!");
            return null;
        }
        return type;
    }

    public static void addContainer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t Enter Container information: ");
        System.out.print("\t\t Enter container's weight: ");
        double con_weight = scanner.nextDouble();
        System.out.print("\t\t Enter container's type: ");
        String con_type = scanner.next();

        Container newContainer = new Container(con_weight, Container.matchContainerType(con_type));
        System.out.println("New Container has been added: " + "\n" + newContainer.toString());
        return;
    }
}
