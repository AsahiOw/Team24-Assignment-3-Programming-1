package Class;

import Enum.*;
import Interface.Updatable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Container {
    private String id;
    private static int nextId = 1;
    private double weight;
    private final ContainerType type;
    private Port currentPort;
    private Vehicle currentVehicle;
    private ContainerState currentState;
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
        this.currentState = ContainerState.ON_VEHICLE;
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
//     constructor, getters, setters
    public Container(double weight, ContainerType type) {
        this.id = "container"+nextId++;
        this.weight = weight;
        this.type = type;
        this.currentPort = null;
        this.currentVehicle = null;
        containers.add(this);
        this.currentState = ContainerState.NEITHER;
    }

    public Container(double weight, ContainerType type, Port currentPort) {
        this.id = "container"+nextId++;
        this.weight = weight;
        this.type = type;
        this.currentPort = currentPort;
        this.currentVehicle = null;
        containers.add(this);
        this.currentState = ContainerState.ON_PORT;
    }

    public Container(double weight, ContainerType type, Vehicle currentVehicle) {
        this.id = "container"+nextId++;
        this.weight = weight;
        this.type = type;
        this.currentPort = null;
        this.currentVehicle = currentVehicle;
        containers.add(this);
        this.currentState = ContainerState.ON_VEHICLE;
    }


    public static void getContainers() {
        System.out.println("List of Container: ");
        for (Container c: containers) {
            System.out.println("\t" + c.toString());
        }
    }

    public static ArrayList<Container> getListOfContainers() {
        return containers;
    }
    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", weight=" + weight +
                ", type=" + type +
                ", containerState=" + currentState +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setPort(Port p) {
        this.currentPort = p;
        this.currentState = ContainerState.ON_PORT;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static double calculateTotalWeightByType(ContainerType type) {
        double totalWeight = 0;
        for (Container container: containers) {
            if (container.type == type) {
                totalWeight += container.getWeight();
            }
        }
        return totalWeight;
    }

    public static double calculateTotalWeightByTypeInPort(ContainerType type, Port port) {
        double totalWeight = 0;
        for (Container container: containers) {
            if (container.type == type && container.currentPort == port) {
                totalWeight += container.getWeight();
            }
        }
        return totalWeight;
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

    public static void addNewContainer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t Enter Container information ");
        System.out.print("\t\t Enter container's weight: ");
        double con_weight = scanner.nextDouble();
        System.out.print("\t\t Enter container's type: ");
        String con_type = scanner.next();
        System.out.print("Add this container on vehicle/port: ");
        String addOn = scanner.next();

        if (addOn.equalsIgnoreCase("vehicle")) {
            System.out.print("\t\t Enter vehicle ID: ");
            String vehicle_Id = scanner.next();

            while (Vehicle.getVehicleByName(vehicle_Id) != null) {
                System.out.print("\t\t Enter vehicle's vehicle ID again: ");
                vehicle_Id = scanner.next();
            }

            Container newContainer = new Container(con_weight, Container.matchContainerType(con_type), Vehicle.getVehicleByName(vehicle_Id));
            FileWriter writer = new FileWriter("src/Data/Container.txt", true);
            writer.write(newContainer.getId() + "," + newContainer.getWeight() + "," + newContainer.getType() + "," + newContainer.currentState + "," + vehicle_Id + "\n");
            writer.close();
            System.out.println("New Container has been added: " + "\n" + newContainer);

        } else if (addOn.equalsIgnoreCase("port")) {
            System.out.print("\t\t Enter port ID: ");
            String port_Id = scanner.next();
            while (Port.matchPortID(port_Id) == null) {
                System.out.print("\t\t Enter vehicle's port ID again: ");
                port_Id = scanner.next();
            }
            Container newContainer = new Container(con_weight, Container.matchContainerType(con_type), Port.matchPortID(port_Id));
            FileWriter writer = new FileWriter("src/Data/Container.txt", true);
            writer.write(newContainer.getId() + "," + newContainer.getWeight() + "," + newContainer.getType() + "," + newContainer.currentState + "," + port_Id + "\n");
            writer.close();
            System.out.println("New Container has been added: " + "\n" + newContainer);

        } else if (addOn.equalsIgnoreCase("neither")) {
            Container newContainer = new Container(con_weight, Container.matchContainerType(con_type));
            FileWriter writer = new FileWriter("src/Data/Container.txt", true);
            writer.write(newContainer.getId() + "," + newContainer.getWeight() + "," + newContainer.getType() + newContainer.currentState + "\n");
            writer.close();
            System.out.println("New Container has been added: " + "\n" + newContainer);

        } else {
            System.out.println("Not exists container type!");
        }

    }



}
