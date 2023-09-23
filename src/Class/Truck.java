package Class;
import Enum.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static Enum.ContainerType.LIQUID;
import static Enum.ContainerType.REFRIGERATED;
import static Enum.TruckType.REEFER;
import static Enum.TruckType.TANKER;

public class Truck extends Vehicle{
    private double totalWeight = 0;
    private final Map<ContainerType, Double> fuelConsumptionRates;
    private final TruckType type;

    public Truck(String name, double fuel, double maxFuel, double capacity, double maxLoad, Port currentPort, TruckType type) {
        super(name, fuel,maxFuel, capacity,maxLoad,currentPort);
        fuelConsumptionRates = new HashMap<>();
        fuelConsumptionRates.put(ContainerType.DRY, 4.6);
        fuelConsumptionRates.put(ContainerType.OPEN_TOP, 3.2);
        fuelConsumptionRates.put(ContainerType.OPEN_SIDE, 3.2);
        fuelConsumptionRates.put(ContainerType.REFRIGERATED, 5.4);
        fuelConsumptionRates.put(ContainerType.LIQUID, 5.3);
        this.type = type;
    }
    @Override
    public String toString() {
        return "Truck{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", fuel=" + super.getFuel() +
                ", maxFuel=" + super.getMaxFuel() +
                ", capacity=" + super.getCapacity() +
                ", maxLoad=" + super.getMaxLoad() +
                ", currentPort=" + super.getCurrentPort().getName() +
                ", totalWeight=" + totalWeight +
                ", type=" + type +
                '}';
    }

    public TruckType getType() {
        return type;
    }

    //    method
    @Override
    public boolean loadContainer(Container c) {
        // Check if truck type can carry container type
        if(c.getType() == REFRIGERATED && type != REEFER) {
            System.out.println("Cannot load refrigerated container on this truck");
            return false;
        }
        if(c.getType() == LIQUID && type != TANKER) {
            System.out.println("Cannot load liquid container on this truck");
            return false;
        }

        // Check capacity and load
        if (this.getNumContainers() == this.getCapacity()) {
            System.out.println("Truck is at full capacity!");
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
        if (super.getCurrentPort() != null) {super.getCurrentPort().removeContainer(c);}

        return true;
    }

    @Override
    public boolean unloadContainer(Container c) {
        // Unload container
        // Check if container is loaded
        if (!super.listOfContainers().contains(c)) {
            return false;
        }

        // Unload container
        super.removeContainer(c);
        c.setCurrentVehicle(null);
        c.setCurrentState(ContainerState.NEITHER);
        super.getCurrentPort().addContainerToPort(c);

        // Update total weight
        this.totalWeight -= c.getWeight();
        return true;
    }

    @Override
    public int getNumContainers() {
        // Return number of loaded containers
        return super.listOfContainers().size();
    }

    @Override
    public double getTotalWeight() {
        // Calculate and return total weight
        return totalWeight;
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
        Scanner scanner = new Scanner(System.in);
        if (!canMoveToPort(destinationPort)) {
            System.out.println("Cannot move Truck to port!");
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
        super.setFuel(super.getFuel() - fuelNeeded);

        System.out.print("When do you want to move this vehicle (dd/mm/yyyy): ");
        String startDateString = scanner.next();
        System.out.print("When do you want this vehicle to arrive (dd/mm/yyyy): ");
        String arrivalDateString = scanner.next();
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString);
            Date arrivalDate = new SimpleDateFormat("dd/MM/yyyy").parse(arrivalDateString);
            while (arrivalDate.compareTo(startDate) < 0) {
                System.out.print("\tEnter departure date again (dd/mm/yyyy): ");
                startDateString = scanner.next();
                System.out.print("\tEnter arrival date again (dd/mm/yyyy): ");
                arrivalDateString = scanner.next();
            }
        } catch (ParseException e) {
            System.out.println("Invalid date!");
        }

        new Trip(this, startDateString, this.getCurrentPort(), arrivalDateString, destinationPort, TripStatus.IN_PROGRESS);
        System.out.println("Vehicle " + super.getId() + " plan to be moved to " + destinationPort.getId() + " successfully!");
    }

    @Override
    public boolean canMoveToPort(Port targetPort) {

        // Check fuel level
        if (this.getFuel() < getCurrentPort().distanceTo(targetPort)) {
            return false;
        }
        // Check landingAbility
        if (!targetPort.getLandingAbility()){
            return false;
        }
        // Check capacity
        return this.getNumContainers() != this.getCapacity();
        // Check weather conditions
        // Any other checks
    }

    public static TruckType matchTruckType(String truckType) {
        TruckType type;
        if ("BASIC".equalsIgnoreCase(truckType)) {
            type = TruckType.BASIC;
        } else if ("REEFER".equalsIgnoreCase(truckType)) {
            type = TruckType.REEFER;
        } else if ("TANKER".equalsIgnoreCase(truckType)) {
            type = TruckType.TANKER;
        } else {
            System.out.println("No matching type found!");
            return null;
        }
        return type;
    }
}

