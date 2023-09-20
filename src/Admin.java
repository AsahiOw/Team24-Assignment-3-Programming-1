import Class.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }
//  Open txt file
    File file1 = new File("src/Data/Port.txt");
    File file2 = new File("src/Data/Container.txt");
    File file3 = new File("src/Data/Trip.txt");
    File file4 = new File("src/Data/Port manager.txt");
    File file5 = new File("src/Data/Admin.txt");
    File file6 = new File("src/Data/Ship.txt");
    File file7 = new File("src/Data/Truck.txt");
    // Get input choice and execute relevant method
    static Scanner scanner = new Scanner(System.in);
    @Override
    public void showMenuOptions() throws IOException {
        int sub_option = 0;

        System.out.println("Welcome Admin! Select an option: \n");
        System.out.println("1. Listing/Viewing options");
        System.out.println("2. Adding entities");
        System.out.println("3. Removing entities");
        System.out.println("4. Vehicle Options");
        System.out.println("5. User Management");
        System.out.println("6. Statistic Operation");
        System.out.println("7. Exit");

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        switch (option) {

            case 1: // Listing/Viewing options
//              List of Options
                System.out.println("\n----------------------");
                System.out.println("1. List all ports");
                System.out.println("2. List all vehicles");
                System.out.println("3. List all containers");
                System.out.println("4. List all managers");

//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1:
                        Port.printListOfPorts();
                        continueToOption();
                        break;
                    case 2:
                        Vehicle.printListOfVehicles();
                        continueToOption();
                        break;
                    case 3:
                        Container.getContainers();
                        continueToOption();
                        break;
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;


            case 2: //Adding entities
//              List of Options
                System.out.println("\n----------------------");
                System.out.println("1. Add new port");
                System.out.println("2. Add new vehicle");
                System.out.println("3. Add new container");

//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Add new port
                        addNewPort();
                        continueToOption();
                        break;
                    case 2: // Add new vehicle
                        addNewVehicle();
                        continueToOption();
                        break;
                    case 3: // Add new container
                        addNewContainer();
                        continueToOption();
                        break;
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;


            case 3: // Removing entities
                System.out.println("1. Remove port");
                System.out.println("2. Remove vehicle");
                System.out.println("3. Remove container");

//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Remove Port
                        removeSelectedPort();
                        continueToOption();
                        break;
                    case 2: // Remove vehicle
                        removeSelectedVehicle();
                        continueToOption();
                        break;
                    case 3: // Remove container
                        removeSelectedContainer();
                        continueToOption();
                        break;
                }
                break;

            case 4:
                System.out.println("1. Vehicle load container");
                System.out.println("2. Vehicle unload container");
                System.out.println("3. Vehicle move to port");
                System.out.println("4. Fuel up vehicle");

//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Vehicle load container
                        loadContainerOnVehicle();
                        continueToOption();
                        break;
                    case 2: // Vehicle unload container
                        unloadContainerOnVehicle();
                        continueToOption();
                        break;
                    case 3: // Vehicle move to port
                        moveVehicleToPort();
                        continueToOption();
                        break;
                    case 4: // Fuel up vehicle
                        fuelUpVehicle();
                        continueToOption();
                        break;
                }
                break;


            case 5:
                System.out.println("1. Add user");
                System.out.println("2. Remove user");
//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Add user
                        continueToOption();
                        break;
                    case 2: // Remove user
                        continueToOption();
                        break;
                }
                break;


            case 6:
                System.out.println("1. Total fuel used today");
                System.out.println("2. Total weight of each type of container ");
                System.out.println("3. List all ships in a port ");
                System.out.println("4. List all trips in a day ");
                System.out.println("5. List all trips from day A to day B");
//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Total fuel used today
                    case 2:
                    case 3:
                }
                break;


            default:
                System.out.println("Unexpected value: " + option + ", please select again!");
                showMenuOptions();
                break;
        }
    }

    public void addNewPort() throws IOException {

        System.out.println("\n----------------------");
        System.out.println("\t Enter Port information: ");
        System.out.print("\t\t Enter port's name: ");
        String port_name = scanner.next();

        System.out.print("\t\t Enter port's latitude and longtitude: ");
        double port_latitude = scanner.nextDouble();
        double port_longtitude = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("\t\t Enter port's capacity: ");
        double port_capacity = scanner.nextDouble();

        System.out.print("\t\t Enter port's landing Ability: ");
        boolean port_landingAbility = scanner.nextBoolean();


        Port port = new Port(port_name, port_latitude, port_longtitude, port_capacity, port_landingAbility);
//      add to data folder
        PrintWriter writer = new PrintWriter(file1);
        writer.print(port.getId() + ",");
        writer.print(port.getName() + ",");
        writer.print(port.getLatitude() + ",");
        writer.print(port.getLongitude() + ",");
        writer.print(port.getCapacity() + ",");
        writer.println(port.isLandingAbility());
        writer.close();
        System.out.println("New port has been added: " + "\n" + port);
    }

    public void addNewVehicle() throws FileNotFoundException {
        System.out.println("\n----------------------");
        System.out.print("\t Which vehicle you want to add (Enter Truck/Ship): ");
        String vehicle_type = scanner.next();

        System.out.println("\n\t Enter vehicle information: ");
        System.out.print("\t\t Enter vehicle's name: ");
        String veh_name = scanner.next();
        System.out.print("\t\t Enter vehicle's fuel: ");
        double veh_fuel = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's max fuel: ");
        double veh_maxFuel = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's capacity: ");
        double veh_capacity = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's maxLoad: ");
        double veh_maxLoad = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's port ID: ");
        String port_id = scanner.next();

        if (vehicle_type.equalsIgnoreCase("Truck")) {
            System.out.print("\t\t Enter vehicle's type: ");
            String veh_type = scanner.next();
            Vehicle truck = new Truck(veh_name, veh_fuel, veh_maxFuel, veh_capacity, veh_maxLoad, Port.matchPortID(port_id), Truck.matchTruckType(veh_type));
            //      add to data folder
            PrintWriter writer = new PrintWriter(file7);
            writer.print(truck.getId() + ",");
            writer.print(truck.getName() + ",");
            writer.print(truck.getFuel() + ",");
            writer.print(truck.getMaxFuel() + ",");
            writer.print(truck.getCapacity() + ",");
            writer.print(truck.getMaxLoad() + ",");
            writer.print(truck.getCurrentPort() + ",");
            writer.println(Truck.matchTruckType(veh_type));
            writer.close();
            System.out.println("New Truck has been added: " + "\n" + truck);
        } else if (vehicle_type.equalsIgnoreCase("Ship")) {
            Vehicle ship = new Ship(veh_name, veh_fuel, veh_maxFuel, veh_capacity, veh_maxLoad, Port.matchPortID(port_id));
            //      add to data folder
            PrintWriter writer = new PrintWriter(file7);
            writer.print(ship.getId() + ",");
            writer.print(ship.getName() + ",");
            writer.print(ship.getFuel() + ",");
            writer.print(ship.getMaxFuel() + ",");
            writer.print(ship.getCapacity() + ",");
            writer.print(ship.getMaxLoad() + ",");
            writer.print(ship.getCurrentPort());
            writer.close();
            System.out.println("New Ship has been added: " + "\n" + ship);
        }
    }

    public void addNewContainer() throws FileNotFoundException {
        System.out.println("\n\t Enter port you want to load this container on: ");
        String con_portid = scanner.next();
        Container.addNewContainer(Port.matchPortID(con_portid));
    }

    public static void loadContainerOnVehicle() {
        ArrayList<Container> listOfContainers = new ArrayList<Container>();
        System.out.print("Select vehicles by ID: ");
        String vehId = scanner.next();

        System.out.print("Enter container(s) name (type 'stop' at the end): ");
        String con_Id = scanner.next();
        while (con_Id.equalsIgnoreCase("stop")) {
            listOfContainers.add(Container.matchContainerId(con_Id));
            con_Id = scanner.next();
        }

        int countContainers = 0;

        for (Container c: listOfContainers) {
            if (!Objects.requireNonNull(Vehicle.matchVehicleId(vehId)).loadContainer(c)) {
                break;
            } else {
                countContainers ++;
            }
        }

        if (countContainers == listOfContainers.size()) {
            System.out.println("All container loaded successfully!");
        } else {
            System.out.println("Only " + countContainers + " has been loaded");
            System.out.println("Containers ID that haven't been loaded: ");
            for (int i = countContainers; i<= listOfContainers.size(); i++) {
                System.out.print("\t " + listOfContainers.get(i));
            }
        }
        scanner.nextLine();
    }
    public static void removeSelectedPort() {
        Port.printListOfPorts();
        System.out.println("----------------------");
        System.out.print("Enter ID of the port you want to remove: ");
        String portIdToRemove = scanner.next();
        Port.removePort(portIdToRemove);
    }
    public static void removeSelectedVehicle() {
        Vehicle.printListOfVehicles();
        System.out.println("----------------------");
        System.out.print("Enter ID of the vehicle you want to remove: ");
        String vehicleIdToRemove = scanner.next();
        Vehicle.removeVehicle(vehicleIdToRemove);
    }

    public static void removeSelectedContainer() {
        Container.getContainers();
        System.out.println("----------------------");
        System.out.print("Enter id of the container you want to remove: ");
        String containerIdToRemove = scanner.next();
        Container.removeContainer(containerIdToRemove);
        scanner.nextLine();
    }

    public static void unloadContainerOnVehicle(){
        ArrayList<Container> listOfContainers = new ArrayList<Container>();
        System.out.print("Select vehicle by ID: ");
        String vehId = scanner.next();
        Vehicle.matchVehicleId(vehId).printListOfContainers();

        System.out.print("Enter container(s) ID you want to remove (type 'stop' at the end): ");
        String con_Id = scanner.next();
        while (con_Id.equalsIgnoreCase("stop")) {
            listOfContainers.add(Container.matchContainerId(con_Id));
            con_Id = scanner.next();
        }

        ArrayList<Container> rejectedContainer = new ArrayList<Container>();
        ArrayList<Container> acceptedContainer = new ArrayList<Container>();
        for (Container c: listOfContainers) {
            if (!Objects.requireNonNull(Vehicle.matchVehicleId(vehId)).loadContainer(c)) {
                rejectedContainer.add(c);
            } else {
                acceptedContainer.add(c);
            }
        }

        if (rejectedContainer.isEmpty()) {
            System.out.println("All container are unloaded!");
        } else if (acceptedContainer.isEmpty()) {
            System.out.println("All container can not be unloaded!");
        } else {
            System.out.println("List of unloaded containers: ");
            for (Container c1: acceptedContainer) {
                System.out.println("\t " + c1.getId());
            }
            System.out.println("List of containers can not be unloaded: ");
            for (Container c1: rejectedContainer) {
                System.out.println("\t " + c1.getId());
            }
        }
    }

    public void moveVehicleToPort() {
        System.out.print("Select vehicle by ID: ");
        String vehId = scanner.next();
        System.out.println("Select port you want to move to by ID: ");
        String port_ID = scanner.next();
        Objects.requireNonNull(Vehicle.matchVehicleId(vehId)).moveToPort(Port.matchPortID(port_ID));

    }

    public void fuelUpVehicle() {
        System.out.print("Select vehicle by ID: ");
        String vehId = scanner.next();
        Vehicle vehicle = Vehicle.matchVehicleId(vehId);
        if (vehicle.isFullFuel()) {
            System.out.println("This vehicle is full fuel.");
        } else {
            System.out.print("Input amount of fuel (type 'full' to fuel full): ");
            if (scanner.hasNextDouble()) {
                double fuel = scanner.nextDouble();
                vehicle.addFuel(fuel);
            } else if (scanner.hasNext()) {
                String str = scanner.next();
                if (str.equalsIgnoreCase("full")) {
                    double amountOfFuelAdded = vehicle.getMaxFuel() - vehicle.getFuel();
                    vehicle.addFuel(amountOfFuelAdded);
                    System.out.println("This vehicle has been added full fuel.\nAmount of fuel added: " + amountOfFuelAdded);
                } else {
                    System.out.println("Unexpected value!");
                }
            }
        }
        scanner.nextLine();
    }

    public void continueToOption() throws IOException {
        System.out.print("\nContinue? (Y/N) ");
        String continueToOption = scanner.next();

        if(continueToOption.equalsIgnoreCase("Y")) {
            this.showMenuOptions();
        } else if(continueToOption.equalsIgnoreCase("N")) {
            System.out.println("Session end.");
            return;
        }
    }
}
