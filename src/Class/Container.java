package Class;

import Enum.ContainerType;
import Interface.Updatable;

import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    private final String id;
    private static int nextId = 1;
    private double weight;
    private final ContainerType type;
    private Port currentPort;
    private Vehicle currentVehicle;
    private static ArrayList<Container> containers = new ArrayList<Container>();

    public static Container matchContainerId(String containerID) {
        for (Container c : containers) {
            if (c.getId().equalsIgnoreCase(containerID)) {
                return c;
            }
        }
        System.out.println("No matching container!");
        return null;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public static void removeContainer(String idToRemove) {
        try {
            Container c = Container.matchContainerId(idToRemove);
            containers.remove(c);

            if (c.currentPort != null) {
                c.currentPort.removeContainer(c);
            } else if (c.currentVehicle != null) {
                c.currentVehicle.removeContainer(c);
            }

            // Update to remove container in file
            System.out.println("Container " + idToRemove + " removed successfully!");
            c = null;

        } catch (Exception e) {
            System.out.println("Container not found with id: " + idToRemove);
        }
    }
    // constructor, getters, setters
    public Container(double weight, ContainerType type) {
        this.id = "container" +nextId++;
        this.weight = weight;
        this.type = type;
        this.currentPort = null;
        this.currentVehicle = null;
        containers.add(this);
    }
    public Container(double weight, ContainerType type, Port currentPort) {
        this.id = "container" +nextId++;
        this.weight = weight;
        this.type = type;
        this.currentPort = currentPort;
        this.currentVehicle = null;
        currentPort.addContainer(this);
        containers.add(this);
    }

    public Container(double weight, ContainerType type, Vehicle currentVehicle) {
        this.id = "container" +nextId++;
        this.weight = weight;
        this.type = type;
        this.currentPort = null;
        this.currentVehicle = currentVehicle;
        currentVehicle.addContainer(this);
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

    public static void addNewContainer(Port p) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t Enter Container information: ");
        System.out.print("\t\t Enter container's weight: ");
        double con_weight = scanner.nextDouble();
        System.out.print("\t\t Enter container's type: ");
        String con_type = scanner.next();

        Container newContainer = new Container(con_weight, Container.matchContainerType(con_type), p);
        System.out.println("New Container has been added: " + "\n" + newContainer.toString());
    }


}
