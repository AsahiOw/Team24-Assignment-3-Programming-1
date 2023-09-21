package Class;
import java.util.ArrayList;
import java.util.Objects;

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
    private static final double MIN_REQUIRED_FUEL = 1000;
    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public Vehicle(String name, double fuel, double maxFuel, double capacity, double maxLoad, Port currentPort) {
        this.id = "vehicle" + nextId++;
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.capacity = capacity;
        this.maxLoad = maxLoad;
        this.currentPort = currentPort;
        onVehicleContainers = new ArrayList<Container>();
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

        vehicle.currentPort.removeVehicle(vehicle);

        vehicle.removeAllContainer();

        System.out.println("Vehicle " + idToRemove + " removed successfully!");
        vehicles.remove(vehicle);
        vehicle = null;
    }

    public void removeAllContainer() {
        while (onVehicleContainers.size() > 0) {
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
        for (Container c: onVehicleContainers) {
            System.out.println(c.toString());
        }
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
        for (Vehicle vehicle: vehicles) {
            System.out.println("\t " + vehicle.toString());
        }
    }

    public String isShipOrTruck() {
        if (this instanceof Ship) return "Ship";
        return "Truck";
    }


    public static Vehicle getVehicleByName(String name) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getName().equals(name)) {
                return vehicle;
            }
        }
        return null;
    }
    // Load / unload containers
    public abstract boolean loadContainer(Container c);
    public abstract boolean unloadContainer(Container c);

    // Get container info
    public int getNumContainers() {
        return onVehicleContainers.size();
    };
    // Move vehicle
    public abstract void moveToPort(Port port);
    public abstract boolean canMoveToPort(Port targetPort);

    public String getCurrentPortName(){
        return currentPort.getName();
    }
}

