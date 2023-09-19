package Class;

import Interface.Vehicle;

import java.lang.reflect.Array;
import java.util.*;

import Enum.ContainerType;

public class Ship implements Vehicle {
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
    private double totalWeight = 0;
    private Map<ContainerType, Double> fuelConsumptionRates;
    private List<TripLogEntry> tripLog;
    private static ArrayList<Ship> ships = new ArrayList<Ship>();

//    constructor, getter, setter


    public Ship(String name, double fuel, double maxFuel, double capacity, double maxLoad, ArrayList<Container> containers, Port currentPort) {
        this.id = "ship"+nextId++;
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.capacity = capacity;
        this.maxLoad = maxLoad;
        this.containers = containers;
        this.currentPort = currentPort;
        fuelConsumptionRates = new HashMap<>();
        fuelConsumptionRates.put(ContainerType.DRY, 3.5);
        fuelConsumptionRates.put(ContainerType.OPEN_TOP, 2.8);
        fuelConsumptionRates.put(ContainerType.OPEN_SIDE, 2.7);
        fuelConsumptionRates.put(ContainerType.REFRIGERATED, 4.5);
        fuelConsumptionRates.put(ContainerType.LIQUID, 4.8);
        ships.add(this);
    }


    public static void getShips() {
        System.out.println("List of Ship: ");
        for (Ship s: ships) {
            System.out.println("\t" + s.toString());
        }
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fuel=" + fuel +
                ", maxFuel=" + maxFuel +
                ", capacity=" + capacity +
                ", maxLoad=" + maxLoad +
                ", currentPort=" + currentPort +
                ", totalWeight=" + totalWeight +
                '}';
    }

    public String getId() {
        return id;
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

    public double getCapacity() {
        return capacity;
    }


    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    @Override
    public Port getCurrentPort() {
        return currentPort;
    }

    @Override
    public void setCurrentPort(Port port) {
        this.currentPort = port;
    }

    //    method
    @Override
    public void loadContainer(Container c) {
        // Check capacity and load
        if (this.getNumContainers() == this.getCapacity()) {
            System.out.println("Ship is at full capacity!");
            return;
        }

        // Check container weight
        double totalWeight = this.getTotalContainerWeight();
        if (totalWeight + c.getWeight() > this.getMaxLoad()) {
            System.out.println("Container will exceed max load limit!");
            return;
        }
        // Update total weight
        this.totalWeight += c.getWeight();

        // Load container
        this.containers.add(c); // Store in some list
        currentPort.removeContainer(c);

        System.out.println("Loaded container " + c.getId() + " on ship " + this.getName());
    }

    @Override
    public void unloadContainer(Container c) {
        // Unload container
        // Check if container is loaded
        if (!this.containers.contains(c)) {
            System.out.println("This container is not on the ship!");
            return;
        }

        // Unload container
        this.containers.remove(c);
        currentPort.addContainerToPort(c);

        // Update total weight
        this.totalWeight -= c.getWeight();

        System.out.println("Unloaded container " + c.getId() + " from ship " + this.getName());
    }

    @Override
    public int getNumContainers() {
        // Return number of loaded containers
        return this.containers.size();
    }

    @Override
    public double getTotalContainerWeight() {
        // Calculate and return total weight
        return totalWeight;
    }

    private double calculateFuelNeeded(double distance) {

        double totalConsumption = 0;

        for (Container c : this.containers) {
            double rate = fuelConsumptionRates.get(c.getType());
            totalConsumption += rate * c.getWeight();
        }

        return totalConsumption * distance;

    }

    @Override
    public void moveToPort(Port destinationPort) {
        // Set current port
        // Consume fuel based on distance
        // Check if valid move
        if (!canMoveToPort(destinationPort)) {
            System.out.println("Cannot move ship to port!");
            return;
        }

        // Calculate fuel needed
        double fuelNeeded = calculateFuelNeeded(this.getCurrentPort().distanceTo(destinationPort));

        // Check fuel available
        if (fuelNeeded > this.getFuel()) {
            System.out.println("Not enough fuel!");
            return;
        }

        // Update current port
        this.setCurrentPort(destinationPort);

        // Reduce fuel
        this.fuel -= fuelNeeded;

        // Update log
        TripLogEntry entry = new TripLogEntry(destinationPort, new Date());
        tripLog.add(entry);

    }

    @Override
    public boolean canMoveToPort(Port targetPort) {

        // Check fuel level
        if (this.getFuel() < MIN_REQUIRED_FUEL) {
            return false;
        }

        // Check capacity
        if (this.getNumContainers() == this.getCapacity()) {
            return false;
        }
        // Check weather conditions
        // Any other checks
        return true;
    }

    @Override
    public void refuel(double fuel) {
        if (this.getCurrentPort() == null) {
            System.out.println("Error! Ship must be docked to refuel.");
        }

        double neededFuel = this.maxFuel - this.getFuel();
        this.fuel = this.maxFuel;
        System.out.println("Refueled ship with " + neededFuel + " gallons.");
    }
}






