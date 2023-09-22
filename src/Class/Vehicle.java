package Class;
import Enum.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public abstract class Vehicle {
    private String id;
    private static int nextId = 1;
    private String name;
    private double fuel;
    private double maxFuel;
    private double capacity;
    private double maxLoad;
    private ArrayList<Container> onVehicleContainers;
    private Port currentPort;
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static Scanner scanner;

    public Vehicle(String name, double fuel, double maxFuel, double capacity, double maxLoad, Port currentPort) {
        this.id = "vehicle" + nextId++;
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.capacity = capacity;
        this.maxLoad = maxLoad;
        this.currentPort = currentPort;
        onVehicleContainers = new ArrayList<>();
        currentPort.addVehicle(this);
        vehicles.add(this);
    }
    // Get vehicle info
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Vehicle.nextId = nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }
    public void addFuel(double amountOfFuel) {
        if ((amountOfFuel + fuel) <= maxFuel) {
            this.fuel += amountOfFuel;
        } else {
            System.out.println("Can't fuel!");
        }
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort.removeVehicle(this);
        currentPort.addVehicle(this);
        this.currentPort = currentPort;
    }

    public static void removeVehicle(String idToRemove) {
        Vehicle vehicle = Vehicle.matchVehicleId(idToRemove);
        Objects.requireNonNull(vehicle).currentPort.removeVehicle(vehicle);
        vehicle.removeAllContainer();

        System.out.println("Vehicle " + idToRemove + " removed successfully!");
        vehicles.remove(vehicle);
        vehicle = null;
    }

    public void removeAllContainer() {
        while (!onVehicleContainers.isEmpty()) {
            Container.removeContainer(onVehicleContainers.get(onVehicleContainers.size()-1).getId());
        }
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public boolean isFullFuel() {
        return (fuel == maxFuel);
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public abstract double getTotalWeight() ;

    public void addContainer(Container container) {
        onVehicleContainers.add(container);
        container.setCurrentVehicle(this);
    }

    public void removeContainer(Container container) {
        onVehicleContainers.remove(container);
    }

    public ArrayList<Container> listOfContainers() {
        return onVehicleContainers;
    }
    public void printListOfContainers() {
        String onPortorVehicle;
        System.out.println("List of containers on " + this.name + ": ");
        System.out.println("╔══════════════╦══════════╦═══════════╦══════════╦══════════════╗");
        System.out.println("║  Container   ║ Weight   ║ Type      ║ State    ║ Port/Vehicle ║");
        System.out.println("╠══════════════╬══════════╬═══════════╬══════════╬══════════════╬");

        for (Container container: onVehicleContainers) {
            if (container.getCurrentState() == ContainerState.NEITHER) onPortorVehicle = "None";
            else onPortorVehicle = (container.getCurrentState() == ContainerState.ON_PORT) ? container.getCurrentPort().getId() : container.getCurrentVehicle().getId();
            System.out.printf("║ %-12s ║ %-8.1f ║ %-9s ║ %-8s ║ %-12s ║%n",
                    container.getId(),
                    container.getWeight(),
                    container.getType().name(),
                    container.getCurrentState().name(),
                    onPortorVehicle);
        }
        System.out.println("╚══════════════╩══════════╩═══════════╩══════════╩══════════════╝");
    }

    // Fuel vehicle
    public void refuel(double fuel) {
        if (this.getCurrentPort() == null) {
            System.out.println("Error! Ship must be docked to refuel.");
        }
        double neededFuel = this.maxFuel - this.getFuel();
        this.fuel = this.maxFuel;
        System.out.println("Refueled vehicle with " + neededFuel + " gallons.");
    }

    public static Vehicle matchVehicleId(String vehicleID) {
        for (Vehicle v: vehicles) {
            if (v.getId().equalsIgnoreCase(vehicleID)) {
                return v;
            }
        }
        System.out.println("Vehicle not found with id: " + vehicleID);
        return null;
    }

    public static void printListOfVehicles() {
        System.out.println("List of Vehicles available: ");
        System.out.println("╔══════════════╦══════════╦═══════════╦══════════╦══════════╦════════════╦═════════════╦═══════════╗");
        System.out.println("║  Vehicle ID  ║ Fuel     ║ Max Fuel  ║ Capacity ║ Max Load ║ Port       ║ Type(Truck) ║#Containers║");
        System.out.println("╠══════════════╬══════════╬═══════════╬══════════╬══════════╬════════════╬═════════════╬═══════════╣");
        for (Vehicle v : vehicles) {
            String truckType = (v instanceof Truck) ? ((Truck) v).getType().name() : "None" ;
            if (v instanceof Ship) {
                System.out.printf("║ %-12s ║ %-8.1f ║ %-9.1f ║ %-8.1f ║ %-8.1f ║ %-10s ║ %-11s ║ %-9d ║%n",
                        v.getName(),
                        v.getFuel(),
                        v.getMaxFuel(),
                        v.getCapacity(),
                        v.getMaxLoad(),
                        v.getCurrentPort().getName(),
                        truckType,
                        v.getNumContainers());
            }
        }
        System.out.println("╚══════════════╩══════════╩═══════════╩══════════╩══════════╩════════════╩═════════════╩═══════════╝");
        scanner.nextLine();
    }

    // Load / unload containers
    public abstract boolean loadContainer(Container c);
    public abstract boolean unloadContainer(Container c);

    // Get container info
    public int getNumContainers() {
        return onVehicleContainers.size();
    }
    // Move vehicle
    public abstract void moveToPort(Port port);
    public abstract boolean canMoveToPort(Port targetPort);

    public String getCurrentPortName(){
        return currentPort.getName();
    }
}

