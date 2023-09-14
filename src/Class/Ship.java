package Class;

import Interface.Vehicle;

import java.util.*;

import Enum.ContainerType;

public class Ship implements Vehicle {
    private String name;
    private double fuel;
    private int capacity;
    private double maxLoad;
    private ArrayList<Container> containers;
    private Port currentPort;
    private static final double MIN_REQUIRED_FUEL = 1000;
    private double totalWeight = 0;
    private Map<ContainerType, Double> fuelConsumptionRates;

    private List<TripLogEntry> tripLog;

//    constructor, getter, setter


    public Ship(String name, double fuel, int capacity, double maxLoad, ArrayList<Container> containers, Port currentPort) {
        this.name = name;
        this.fuel = fuel;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }


    //    method
    @Override
    public void loadContainer(Container c) {
        // Check capacity and load
        if(this.getNumContainers() == this.getCapacity()) {
            System.out.println("Ship is at full capacity!");
            return;
        }

        // Check container weight
        double totalWeight = this.getTotalContainerWeight();
        if(totalWeight + c.getWeight() > this.getMaxLoad()) {
            System.out.println("Container will exceed max load limit!");
            return;
        }
        // Update total weight
        this.totalWeight += c.getWeight();

        // Load container
        this.containers.add(c); // Store in some list

        System.out.println("Loaded container " + c.getId() + " on ship " + this.getName());
    }

    @Override
    public void unloadContainer(Container c) {
        // Unload container
        // Check if container is loaded
        if(!this.containers.contains(c)) {
            System.out.println("This container is not on the ship!");
            return;
        }

        // Unload container
        this.containers.remove(c);

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
    public double getTotalContainerWeight(){
        // Calculate and return total weight
        return totalWeight;
    }
    private double calculateFuelNeeded(double distance) {

        double totalConsumption = 0;

        for(Container c : this.containers) {
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
        if(!canMoveToPort(destinationPort)) {
            System.out.println("Cannot move ship to port!");
            return;
        }

        // Calculate fuel needed
        double fuelNeeded = calculateFuelNeeded(this.getCurrentPort().distanceTo(destinationPort));

        // Check fuel available
        if(fuelNeeded > this.getFuelLevel()) {
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
    public Port getCurrentPort() {
        return currentPort;
    }

    @Override
    public void setCurrentPort(Port port) {
        this.currentPort = port;
    }

    @Override
    public boolean canMoveToPort(Port targetPort) {

        // Check fuel level
        if(this.getFuelLevel() < MIN_REQUIRED_FUEL) {
            return false;
        }

        // Check capacity
        if(this.getNumContainers() == this.getCapacity()) {
            return false;
        }

        // Check weather conditions

        // Any other checks

        return true;
}
