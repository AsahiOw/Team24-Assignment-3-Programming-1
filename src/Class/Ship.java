package Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Enum.ContainerType;

public class Ship extends Vehicle {
    private static final double MIN_REQUIRED_FUEL = 1000;
    private double totalWeight = 0;
    private Map<ContainerType, Double> fuelConsumptionRates;

//    constructor, getter, setter


    public Ship(String name, double fuel, double maxFuel, double capacity, double maxLoad, Port currentPort) {
        super(name, fuel,maxFuel, capacity,maxLoad, currentPort);
        fuelConsumptionRates = new HashMap<>();
        fuelConsumptionRates.put(ContainerType.DRY, 3.5);
        fuelConsumptionRates.put(ContainerType.OPEN_TOP, 2.8);
        fuelConsumptionRates.put(ContainerType.OPEN_SIDE, 2.7);
        fuelConsumptionRates.put(ContainerType.REFRIGERATED, 4.5);
        fuelConsumptionRates.put(ContainerType.LIQUID, 4.8);
    }

    @Override
    public double getTotalWeight() {
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", fuel=" + super.getFuel() +
                ", maxFuel=" + super.getMaxFuel() +
                ", capacity=" + super.getCapacity() +
                ", maxLoad=" + super.getMaxLoad() +
                ", currentPort=" + super.getCurrentPort().getName() +
                ", totalWeight=" + totalWeight +
                '}';
    }

    //    method
    @Override
    public boolean loadContainer(Container c) {
        // Check capacity and load
        if (this.getNumContainers() == this.getCapacity()) {
            System.out.println("Ship is at full capacity!");
            return false;
        }

        // Check container weight
        double totalWeight = this.totalWeight;
        if (totalWeight + c.getWeight() > this.getMaxLoad()) {
            System.out.println("Container will exceed max load limit!");
            return false;
        }
        // Update total weight
        this.totalWeight += c.getWeight();

        // Load container
        super.addContainer(c); // Store in some list
        if (super.getCurrentPort() != null) {super.getCurrentPort().removeContainer(c);};
        return true;
    }

    @Override
    public boolean unloadContainer(Container c) {
        // Unload container
        // Check if container is loaded
        if (!super.listOfContainers().contains(c)) {
            System.out.println("This container is not on the ship!");
            return false;
        }

        // Unload container
        super.removeContainer(c);
        super.getCurrentPort().addContainerToPort(c);

        // Update total weight
        this.totalWeight -= c.getWeight();

        System.out.println("Unloaded container " + c.getId() + " from ship " + super.getName());
        return false;
    }

    @Override
    public int getNumContainers() {
        // Return number of loaded containers
        return super.listOfContainers().size();
    }

    private double calculateFuelNeeded(double distance) {

        double totalConsumption = 0;

        for (Container c : super.listOfContainers()) {
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
        if (fuelNeeded > super.getFuel()) {
            System.out.println("Not enough fuel!");
            return;
        }

        // Update current port
        this.setCurrentPort(destinationPort);

        // Reduce fuel
        super.setFuel(super.getFuel() - fuelNeeded);
        System.out.println("Vehicle " + super.getId() + " moved to " + destinationPort.getId() + " successfully!");
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

        double neededFuel = super.getMaxFuel() - this.getFuel();
        super.setFuel(super.getMaxFuel());
        System.out.println("Refueled ship with " + neededFuel + " gallons.");
    }
}






