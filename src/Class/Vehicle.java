package Class;
import java.util.ArrayList;

public abstract class Vehicle {
    private String id;
    private static int nextId = 1;
    private String name;
    private double fuel;
    private double maxFuel;
    private double capacity;
    private double maxLoad;
    private ArrayList<Container> containers;
    private Port currentPort;
    private static final double MIN_REQUIRED_FUEL = 1000;
    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public Vehicle(String name, double fuel, double maxFuel, double capacity, double maxLoad, ArrayList<Container> containers, Port currentPort) {
        this.id = "vehicle" + nextId++;
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.capacity = capacity;
        this.maxLoad = maxLoad;
        this.containers = containers;
        this.currentPort = currentPort;
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

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
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

    public void addContainer(Container c) {
        containers.add(c);
    }

    public void removeContainer(Container c) {
        containers.remove(c);
    }


    public ArrayList<Container> listOfContainers() {
        return containers;
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

    // Load / unload containers
    public abstract boolean loadContainer(Container c);
    public abstract void unloadContainer(Container c);

    // Get container info
    public int getNumContainers() {
        return this.containers.size();
    };
    public abstract double getTotalContainerWeight();

    // Move vehicle
    public abstract void moveToPort(Port port);
    public abstract boolean canMoveToPort(Port targetPort);
}

