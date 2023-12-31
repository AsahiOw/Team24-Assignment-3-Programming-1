package Application;

import Class.*;
import Enum.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }
    static Scanner scanner = new Scanner(System.in);

    /*------Main Program: Options menu for Admin------*/

    @Override
    public void showMenuOptions() throws IOException {
        int sub_option = 0;

        // Main menu

        System.out.println("Welcome Admin! Select an option: \n");
        System.out.println("╔════════════List of Option════════════╗");
        System.out.println("║ 1. Listing/Viewing options           ║");
        System.out.println("║ 2. Adding entities                   ║");
        System.out.println("║ 3. Removing entities                 ║");
        System.out.println("║ 4. Vehicle Options                   ║");
        System.out.println("║ 5. User Management                   ║");
        System.out.println("║ 6. Statistic Operation               ║");
        System.out.println("║ 7. Exit                              ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();

        switch (option) {
            // Listing/Viewing options
            case 1:

//              List of Sub-Options for Listing/Viewing
                System.out.println("\n");
                System.out.println("╔════════Listing/Viewing options═══════╗");
                System.out.println("║ 1. List all ports                    ║");
                System.out.println("║ 2. List all vehicles                 ║");
                System.out.println("║ 3. List all containers               ║");
                System.out.println("║ 4. List all managers                 ║");
                System.out.println("╚══════════════════════════════════════╝");

                System.out.print("\nSelect Sub-Option: ");
                sub_option = scanner.nextInt();

                switch (sub_option) {
                    case 1: // List all ports
                        Port.printListOfPorts();
                        continueToOption();
                        break;
                    case 2: // List all vehicles
                        Vehicle.printListOfVehicles();
                        continueToOption();
                        break;
                    case 3: // List all containers
                        Container.getContainers();
                        continueToOption();
                        break;
                    case 4: // List all managers
                        User.listAllManagers();
                        continueToOption();
                        break;
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;

            //Adding entities
            case 2:

//              List of Sub-Options for Adding entities
                System.out.println("\n");
                System.out.println("╔════════════Adding entities═══════════╗");
                System.out.println("║ 1. Add new port                      ║");
                System.out.println("║ 2. Add new vehicle                   ║");
                System.out.println("║ 3. Add new container                 ║");
                System.out.println("╚══════════════════════════════════════╝");

                System.out.print("\nSelect Sub-Option: ");
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

            // Removing entities
            case 3:

//              List of Sub-Options for Removing entities
                System.out.println("\n");
                System.out.println("╔═══════════Removing entities══════════╗");
                System.out.println("║ 1. Remove port                       ║");
                System.out.println("║ 2. Remove vehicle                    ║");
                System.out.println("║ 3. Remove container                  ║");
                System.out.println("╚══════════════════════════════════════╝");

                System.out.print("\nSelect Sub-Option: ");
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
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;


            case 4:

//              List of Sub-Options for Vehicle
                System.out.println("\n");
                System.out.println("╔════════════Vehicle Options═══════════╗");
                System.out.println("║ 1. Vehicle load container            ║");
                System.out.println("║ 2. Vehicle unload container          ║");
                System.out.println("║ 3. Vehicle move to port              ║");
                System.out.println("║ 4. Fuel up vehicle                   ║");
                System.out.println("╚══════════════════════════════════════╝");

                System.out.print("\nSelect Sub-Option: ");
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
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;

            case 5:

//              List of Sub-Options for User Management
                System.out.println("\n");
                System.out.println("╔════════════User Management═══════════╗");
                System.out.println("║ 1. Add user                          ║");
                System.out.println("║ 2. Remove user                       ║");
                System.out.println("╚══════════════════════════════════════╝");

                System.out.print("\nSelect Sub-Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Add user
                        User.addUser();
                        continueToOption();
                        break;
                    case 2: // Remove user
                        User.promptRemoveUser();
                        continueToOption();
                        break;
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;

            case 6:

//              List of Sub-Options for Statistic Operation
                System.out.println("\n");
                System.out.println("╔════════════Statistic Operation════════════╗");
                System.out.println("║ 1. Total fuel used today                  ║");
                System.out.println("║ 2. Total weight of each type of container ║");
                System.out.println("║ 3. List all ships in a port               ║");
                System.out.println("║ 4. List all trips in a day                ║");
                System.out.println("║ 5. List all trips from day A to day B     ║");
                System.out.println("╚═══════════════════════════════════════════╝");

                System.out.print("\nSelect Sub-Option: ");
                sub_option = scanner.nextInt();

                switch (sub_option) {
                    case 1: // Total fuel used today
                        System.out.println(Trip.totalFuelUsedToday());
                        continueToOption();
                        break;
                    case 2: // Total weight of each type of container
                        printTotalWeightByContainerType();
                        continueToOption();
                        break;
                    case 3: // List all ships in a port
                        printListOfShipInPort();
                        continueToOption();
                        break;
                    case 4: // List all trips in a days
                        printListOfTripByDate();
                        continueToOption();
                        break;
                    case 5: // List all trips from day A to day B
                        printListOfTripFromDateToDate();
                        continueToOption();
                        break;
                    default:
                        System.out.println("Unexpected value: " + option + ", please select again!");
                        showMenuOptions();
                        break;
                }
                break;

            // Exit
            case 7:
                User.endSession();
                break;

            // Invalid option
            default:
                System.out.println("Unexpected value: " + option + ", please select again!");
                showMenuOptions();
                break;
        }
    }

    /*------Methods for Admin operations------*/

    // Case 2.1 : Add new Port
    public void addNewPort() throws IOException {
        System.out.println("\n══════════════════════════════════════");

        // Get Port information based on user input and validation method
        System.out.println("\t Enter Port information: ");
        System.out.print("\t\t Enter port's name: ");
        String port_name = scanner.next();
        System.out.print("\t\t Enter port's latitude and longitude: ");
        double port_latitude = scanner.nextDouble();
        double port_longitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("\t\t Enter port's capacity: ");
        double port_capacity = scanner.nextDouble();
        System.out.print("\t\t Enter port's landing ability (true/false): ");
        String port_landingAbility = scanner.next();

        // Validate port_landingAbility to be "true" or "false" only
        while ((!port_landingAbility.equalsIgnoreCase("true")) && (!port_landingAbility.equalsIgnoreCase("false"))) {
            System.out.print("Enter a true or false for port's landing ability: ");
            port_landingAbility = scanner.next();
        }

        boolean landingAbility = "true".equalsIgnoreCase(port_landingAbility);
        Port port = new Port(port_name, port_latitude, port_longitude, port_capacity, landingAbility); // Create new port based on input

        // Add to data folder
        FileWriter writer = new FileWriter("src/Data/Port.txt", true);
        writer.write(port.getId() + ",");
        writer.write(port.getName() + ",");
        writer.write(port.getLatitude() + ",");
        writer.write(port.getLongitude() + ",");
        writer.write(port.getCapacity() + ",");
        writer.write(port.getLandingAbility() + "\n");
        writer.close();

        System.out.println("New port has been added: " + "\n" + port);
    }

    // Case 2.2 : Add new Vehicle
    public void addNewVehicle() throws IOException {
        System.out.println("\n══════════════════════════════════════");

        // Get Vehicle information based on user input and validation method
        System.out.println("\n\t Enter vehicle information: ");
        System.out.print("\t\t Enter vehicle's name: ");
        String vehicleName = scanner.next();
        System.out.print("\t\t Enter vehicle's fuel: ");
        double vehicleFuel = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's max fuel: ");
        double vehicleMaxFuel = scanner.nextDouble();

        // Validate maxFuel needs to be larger than current fuel
        while (vehicleMaxFuel < vehicleFuel) {
            System.out.println("Max fuel can't be lower than capacity. Enter max fuel again: ");
            vehicleMaxFuel = scanner.nextDouble();
        }

        System.out.print("\t\t Enter vehicle's capacity: ");
        double vehicleCapacity = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's maxLoad: ");
        double vehicleMaxLoad = scanner.nextDouble();

        // Validate maxLoad needs to be larger than capacity
        while (vehicleMaxLoad < vehicleCapacity) {
            System.out.println("Max load can't be lower than capacity. Enter max load again: ");
            vehicleMaxLoad = scanner.nextDouble();
        }


        System.out.print("\t\t Enter vehicle's port ID: ");
        String portId = scanner.next();
        System.out.print("\t Which vehicle you want to add (Enter Truck/Ship): ");
        String vehicleType = scanner.next();

        // Validate port need to be existing
        while (Port.matchPortID(portId) == null) {
            System.out.print("\t\t Enter vehicle's port ID again: ");
            portId = scanner.next();
        }

        if (vehicleType.equalsIgnoreCase("Truck")) { // If it is truck, user need to enter Truck type
            System.out.print("\t\t Enter vehicle's type: ");
            String veh_type = scanner.next();

            if (Objects.requireNonNull(Port.matchPortID(portId)).getLandingAbility()) {

                // Add to data folder
                Vehicle truck = new Truck(vehicleName, vehicleFuel, vehicleMaxFuel, vehicleCapacity, vehicleMaxLoad, Port.matchPortID(portId), Truck.matchTruckType(vehicleType));
                FileWriter writer = new FileWriter("src/Data/Truck.txt", true);

                writer.write(truck.getId() + ",");
                writer.write(truck.getName() + ",");
                writer.write(truck.getFuel() + ",");
                writer.write(truck.getMaxFuel() + ",");
                writer.write(truck.getCapacity() + ",");
                writer.write(truck.getMaxLoad() + ",");
                writer.write(truck.getCurrentPortName() + ",");
                writer.write(Truck.matchTruckType(veh_type) + "\n");
                writer.close();

                System.out.println("New Truck has been added: " + "\n" + truck);
            } else {
                System.out.println("This port does not allow trucks!");
            }
        } else if (vehicleType.equalsIgnoreCase("Ship")) {

            // Add to data folder
            Vehicle ship = new Ship(vehicleName, vehicleFuel, vehicleMaxFuel, vehicleCapacity, vehicleMaxLoad, Port.matchPortID(portId));
            FileWriter writer = new FileWriter("src/Data/Ship.txt", true);

            writer.write(ship.getId() + ",");
            writer.write(ship.getName() + ",");
            writer.write(ship.getFuel() + ",");
            writer.write(ship.getMaxFuel() + ",");
            writer.write(ship.getCapacity() + ",");
            writer.write(ship.getMaxLoad() + ",");
            writer.write(ship.getCurrentPortName() + "\n");
            writer.close();

            System.out.println("New Ship has been added: " + "\n" + ship);
        } else {
            System.out.println("Not existing vehicle type!");
        }
    }

    // Case 2.3 : Add new Container
    public void addNewContainer() throws IOException {
        Container.addNewContainer();
        scanner.nextLine();
    }

    // Case 3.1 : Remove port
    public static void removeSelectedPort() throws IOException {
        Port.printListOfPorts(); // Show user list of ports available

        // Get user input
        System.out.println("\n══════════════════════════════════════");
        System.out.print("Enter ID of the port you want to remove: ");
        String portIdToRemove = scanner.next();

        // Validate port need to be existing
        while (Port.matchPortID(portIdToRemove) == null) {
            System.out.println("Please enter existing port ID: ");
            portIdToRemove = scanner.next();
        }

        // Add to data folder
        File inputFile;
        inputFile = new File("src/Data/Port.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<String> lines = new ArrayList<>();
        String lineFromFile;
        while ((lineFromFile = reader.readLine()) != null) {
            lines.add(lineFromFile);
        }
        reader.close();

        // Remove matching line from array
        for (int i=0; i<lines.size(); i++) {
            if (lines.get(i).startsWith(portIdToRemove)) {
                lines.remove(i);
                break;
            }
        }

        // Write array back to file
        PrintWriter writer = new PrintWriter(inputFile);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
        Port.removePort(portIdToRemove);

        // Get trips from Trip class
        List<Trip> trips = Trip.getTrips();

        // Add trips to list
        List<Trip> allTrips = new ArrayList<>(trips);
        try {
            // Write trips to Trip.txt
            BufferedWriter tripWriter = new BufferedWriter(new FileWriter("src/Data/Trip.txt", false));
            for (Trip trip : allTrips) {
                tripWriter.write(trip.getId() + ",");
                tripWriter.write(trip.getVehicleName() + ",");
                tripWriter.write(trip.getDepartureDateString() + ",");
                tripWriter.write(trip.getdeparturePortName() + ",");
                tripWriter.write(trip.getArrivalDateString() + ",");
                tripWriter.write(trip.getarrivalPortName() + ",");
                tripWriter.write(trip.getStatus() + "\n");
            }
            tripWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Case 3.2 : Remove vehicle
    public static void removeSelectedVehicle() throws IOException {
        Vehicle.printListOfVehicles(); // Show user list of vehicles available

        // Get user input
        System.out.println("\n══════════════════════════════════════");
        System.out.print("Enter ID of the vehicle you want to remove: ");
        String vehicleIdToRemove = scanner.next();

        // Validate vehicle need to be existing
        while (Vehicle.matchVehicleId(vehicleIdToRemove) == null) {
            System.out.println("Please enter existing port ID: ");
            vehicleIdToRemove = scanner.next();
        }

        // Add to data folder
        File inputFile;
        if (Vehicle.matchVehicleId(vehicleIdToRemove) instanceof Ship) {
            inputFile = new File("src/Data/Ship.txt");
        } else {
            inputFile = new File("src/Data/Truck.txt");
        }
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<String> lines = new ArrayList<>();
        String lineFromFile;
        while ((lineFromFile = reader.readLine()) != null) {
            lines.add(lineFromFile);
        }
        reader.close();

        // Remove matching line from array
        for (int i=0; i<lines.size(); i++) {
            if (lines.get(i).startsWith(vehicleIdToRemove)) {
                lines.remove(i);
                break;
            }
        }

        // Write array back to file
        PrintWriter writer = new PrintWriter(inputFile);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
        Vehicle.removeVehicle(vehicleIdToRemove);

        // Get trips from Trip class
        List<Trip> trips = Trip.getTrips();

        // Add trips to list
        List<Trip> allTrips = new ArrayList<>(trips);
        try {
            // Write trips to Trip.txt
            BufferedWriter tripWriter = new BufferedWriter(new FileWriter("src/Data/Trip.txt", false));
            for (Trip trip : allTrips) {
                tripWriter.write(trip.getId() + ",");
                tripWriter.write(trip.getVehicleName() + ",");
                tripWriter.write(trip.getDepartureDateString() + ",");
                tripWriter.write(trip.getdeparturePortName() + ",");
                tripWriter.write(trip.getArrivalDateString() + ",");
                tripWriter.write(trip.getarrivalPortName() + ",");
                tripWriter.write(trip.getStatus() + "\n");
            }
            tripWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Case 3.3 : Remove container
    public static void removeSelectedContainer() throws IOException {
        Container.getContainers(); // Show user list of containers available

        // Get user input
        System.out.println("\n══════════════════════════════════════");
        Container.getContainers();
        System.out.print("Enter ID of the container you want to remove: ");
        String containerIdToRemove = scanner.next();

        // Validate container need to be existing
        while (Container.matchContainerId(containerIdToRemove) == null) {
            System.out.println("Please enter existing container ID: ");
            containerIdToRemove = scanner.next();
        }

        // Add data to folder
        File inputFile;
        inputFile = new File("src/Data/Container.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<String> lines = new ArrayList<>();
        String lineFromFile;
        while ((lineFromFile = reader.readLine()) != null) {
            lines.add(lineFromFile);
        }
        reader.close();

        // Remove matching line from array
        for (int i=0; i<lines.size(); i++) {
            if (lines.get(i).startsWith(containerIdToRemove)) {
                lines.remove(i);
                break;
            }
        }

        // Write array back to file
        PrintWriter writer = new PrintWriter(inputFile);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
        Container.removeContainer(containerIdToRemove);
        scanner.nextLine();
    }

    // Case 4.1: Vehicle load container
    public static void loadContainerOnVehicle() {

        // Get user input
        System.out.println("\n══════════════════════════════════════");
        ArrayList<Container> listOfContainers = new ArrayList<>();
        Vehicle.printListOfVehicles(); // Show vehicle list of containers available
        System.out.print("Select vehicles by ID: ");
        String vehicle_Id = scanner.next();

        // Validate vehicle need to be existing
        while (Vehicle.matchVehicleId(vehicle_Id) == null) {
            Vehicle.printListOfVehicles();
            System.out.print("Enter vehicle ID again: ");
            vehicle_Id = scanner.next();
        }

        Container.getContainers();
        System.out.print("Enter container(s) id (type 'stop' at the end): ");
        String con_Id = scanner.next();

        // Validate container need to be existing
        while (Container.matchContainerId(con_Id) == null) {
            System.out.print("Enter container ID again: ");
            con_Id = scanner.next();
        }

        while (!con_Id.equalsIgnoreCase("stop")) {
            listOfContainers.add(Container.matchContainerId(con_Id));
            con_Id = scanner.next();
        }

        int countContainers = 0;
        int beforeSize = listOfContainers.size();

        ArrayList<Container> newListOfContainers = new ArrayList<>(Container.getListOfContainers());
        ArrayList<Container> failedToLoadContainers = new ArrayList<>(listOfContainers);


        for (Container c : listOfContainers) {
            int index = newListOfContainers.indexOf(c);
            newListOfContainers.remove(c);

            if (Objects.requireNonNull(Vehicle.matchVehicleId(vehicle_Id)).loadContainer(c)) {
                countContainers++;
                newListOfContainers.add(index, c);
                failedToLoadContainers.remove(c);
            } else {
                newListOfContainers.add(index, c);
            }

        }

        System.out.println("\n══════════════Result══════════════════");
        if (countContainers == beforeSize) {
            System.out.println("All container loaded successfully!");
        } else {
            System.out.println("Only " + countContainers + " has been loaded");
            System.out.println("Containers ID that haven't been loaded: ");
            System.out.println("╔══════════════╦══════════════╦═══════════════════╦══════════════╦══════════════╗");
            System.out.println("║  Container   ║    Weight    ║       Type        ║    State     ║ Port/Vehicle ║");
            System.out.println("╠══════════════╬══════════════╬═══════════════════╬══════════════╬══════════════╣");

            for (int i = 0; i <= beforeSize; i++) {
                String onPortorVehicle;
                Container container = failedToLoadContainers.get(i);
                if (container.getCurrentState() == ContainerState.NEITHER) onPortorVehicle = "None";
                else
                    onPortorVehicle = (container.getCurrentState() == ContainerState.ON_PORT) ? container.getCurrentPort().getId() : container.getCurrentVehicle().getId();
                System.out.printf("║ %-12s ║ %-12.1f ║ %-17s ║ %-12s ║ %-12s ║%n",
                        container.getId(),
                        container.getWeight(),
                        container.getType().name(),
                        container.getCurrentState().name(),
                        onPortorVehicle);
            }
            System.out.println("╚══════════════╩══════════════╩═══════════════════╩══════════════╩══════════════╝");
        }
        try {
            FileWriter deleteWriter = new FileWriter("src/Data/Container.txt", false);
            FileWriter writer = new FileWriter("src/Data/Container.txt", true);

            for (Container container : newListOfContainers) {
                if (container.getCurrentState() == ContainerState.ON_VEHICLE) {
                    writer.write(container.getId() + "," + container.getWeight() + "," + container.getType() + "," + container.getCurrentState() + "," + container.getCurrentVehicle().getName() + "\n");
                } else if (container.getCurrentState() == ContainerState.ON_PORT) {
                    writer.write(container.getId() + "," + container.getWeight() + "," + container.getType() + "," + container.getCurrentState() + "," + container.getCurrentPort().getName() + "\n");
                } else {
                    writer.write(container.getId() + "," + container.getWeight() + "," + container.getType() + "," + container.getCurrentState() + "\n");
                }
            }
            writer.close();
            scanner.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Case 4.2: Vehicle unload container
    public static void unloadContainerOnVehicle(){
        System.out.println("\n══════════════════════════════════════");
        ArrayList<Container> listOfContainers = new ArrayList<>();
        System.out.print("Select vehicle by ID: ");
        String vehicle_Id = scanner.next();
        while (Vehicle.matchVehicleId(vehicle_Id) == null) {
            Vehicle.printListOfVehicles();
            System.out.print("Enter vehicle ID again: ");
            vehicle_Id = scanner.next();
        }

        Objects.requireNonNull(Vehicle.matchVehicleId(vehicle_Id)).printListOfContainers();

        System.out.print("Enter container(s) ID you want to remove (type 'stop' at the end): ");
        String con_Id = scanner.next();

        while (!con_Id.equalsIgnoreCase("stop")) {
            listOfContainers.add(Container.matchContainerId(con_Id));
            con_Id = scanner.next();
        }

        ArrayList<Container> newListOfContainers = new ArrayList<>(Container.getListOfContainers());

        ArrayList<Container> rejectedContainer = new ArrayList<>();
        ArrayList<Container> acceptedContainer = new ArrayList<>();

        for (Container c: listOfContainers) {
            int index = newListOfContainers.indexOf(c);
            newListOfContainers.remove(c);

            if (!Objects.requireNonNull(Vehicle.matchVehicleId(vehicle_Id)).unloadContainer(c)) {
                rejectedContainer.add(c);
            } else {
                acceptedContainer.add(c);
            }

            System.out.println(c.toString());
            newListOfContainers.add(index, c);
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

        try {
            FileWriter deleteWriter = new FileWriter("src/Data/Container.txt", false);
            FileWriter writer = new FileWriter("src/Data/Container.txt", true);

            for (Container container : newListOfContainers) {
                if (container.getCurrentState() == ContainerState.ON_VEHICLE) {
                    writer.write(container.getId() + "," + container.getWeight() + "," + container.getType() + "," + container.getCurrentState() + "," + container.getCurrentVehicle().getName() + "\n");
                } else if (container.getCurrentState() == ContainerState.ON_PORT) {
                    writer.write(container.getId() + "," + container.getWeight() + "," + container.getType() + "," + container.getCurrentState() + "," + container.getCurrentPort().getName() + "\n");
                } else {
                    writer.write(container.getId() + "," + container.getWeight() + "," + container.getType() + "," + container.getCurrentState() + "\n");
                }
            }
            writer.close();
            scanner.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Case 4.3: Vehicle move to port
    public void moveVehicleToPort() {
        System.out.println("\n══════════════════════════════════════");
        Vehicle.printListOfVehicles();
        System.out.print("Select vehicle by ID: ");
        String vehicle_Id = scanner.next();

        while (Vehicle.matchVehicleId(vehicle_Id) == null) {
            System.out.print("Enter vehicle ID again: ");
            vehicle_Id = scanner.next();
        }

        Port.printListOfPorts();
        System.out.println("Enter ID of the port you want to move to:");
        String port_Id = scanner.next();

        while (Port.matchPortID(port_Id) == null) {
            System.out.print("Enter port ID again: ");
            port_Id = scanner.next();
        }

        Objects.requireNonNull(Vehicle.matchVehicleId(vehicle_Id)).moveToPort(Port.matchPortID(port_Id));

        // Get trips from Trip class
        List<Trip> trips = Trip.getTrips();

        // Add trips to list
        List<Trip> allTrips = new ArrayList<>(trips);
        try {
            // Write trips to Trip.txt
            BufferedWriter tripWriter = new BufferedWriter(new FileWriter("src/Data/Trip.txt", false));
            for (Trip trip : allTrips) {
                tripWriter.write(trip.getId() + ",");
                tripWriter.write(trip.getVehicleName() + ",");
                tripWriter.write(trip.getDepartureDateString() + ",");
                tripWriter.write(trip.getdeparturePortName() + ",");
                tripWriter.write(trip.getArrivalDateString() + ",");
                tripWriter.write(trip.getarrivalPortName() + ",");
                tripWriter.write(trip.getStatus() + "\n");
            }
            tripWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Case 4.4: Fuel up vehicle
    public void fuelUpVehicle() {
        System.out.println("\n══════════════════════════════════════");
        System.out.print("Select vehicle by ID: ");
        String vehId = scanner.next();
        Vehicle vehicle = Vehicle.matchVehicleId(vehId);
        if (Objects.requireNonNull(vehicle).isFullFuel()) {
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

    // Case 6.1: Total fuel used today
    public void printTotalWeightByContainerType() {

        System.out.printf("╔%-30s╗%n", "═".repeat(30));

        System.out.print("║Container Type ");
        System.out.printf("%-15s║%n", "Total Weight");

        for (ContainerType type : ContainerType.values()) {

            System.out.print("║");
            System.out.printf("%-15s", type.name());
            System.out.printf("%-15.2f║%n",
                    Container.calculateTotalWeightByType(type));
        }
        System.out.printf("╚%-30s╝%n", "═".repeat(30));
    }

    // Case 6.3: List all ships in a port
    public void printListOfShipInPort() {
        System.out.println("\n══════════════════════════════════════");
        System.out.print("Enter ID of the port you want to view ships list: ");
        String port_Id = scanner.next();

        while (Port.matchPortID(port_Id) == null) {
            System.out.print("Enter port ID again: ");
            port_Id = scanner.next();
        }


        Port port = Port.matchPortID(port_Id);
        System.out.println("╔══════════════╦══════════════╦══════════════╦══════════════╦══════════════╦══════════════╦══════════════╗");
        System.out.println("║ Ship Name    ║     Fuel     ║   Max Fuel   ║   Capacity   ║   Max Load   ║     Port     ║ #Containers  ║");
        System.out.println("╠══════════════╬══════════════╬══════════════╬══════════════╬══════════════╬══════════════╬══════════════╣");
        for (Vehicle v : Objects.requireNonNull(port).getOnPortVehicles()) {
            if (v instanceof Ship) {
                System.out.printf("║ %-12s ║ %-12.1f ║ %-12.1f ║ %-12.1f ║ %-12.1f ║ %-12s ║ %-12d ║%n",
                        v.getName(),
                        v.getFuel(),
                        v.getMaxFuel(),
                        v.getCapacity(),
                        v.getMaxLoad(),
                        v.getCurrentPort().getName(),
                        v.getNumContainers());
            }
        }
        System.out.println("╚══════════════╩══════════════╩══════════════╩══════════════╩══════════════╩══════════════╩══════════════╝");
        scanner.nextLine();
    }

    // Case 6.4: List all ships in a day
    public void printListOfTripByDate() {
        System.out.println("\n══════════════════════════════════════");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search date (dd/mm/yyyy): ");
        String dateString = scanner.next();

        try {
            Date searchDate = format.parse(dateString);

            ArrayList<Trip> results = Trip.searchTripByDate(searchDate);

            if (!results.isEmpty()) {
                // print header
                System.out.println("╔════════════════╦════════════════╦═══════════════╦══════════════╦══════════════╦══════════════╦═══════════════╗");
                System.out.printf("║ %-12s   ║ %-12s   ║ %-13s ║ %-12s ║ %-12s ║ %-12s ║ %-13s ║\n", "ID","Vehicle", "Departure", "From", "Arrival", "To", "Status");
                System.out.println("╠════════════════╬════════════════╬═══════════════╬══════════════╬══════════════╬══════════════╬═══════════════╣");
                for (Trip trip : results) {
                    System.out.printf("║ %-12s   ║ %-12s   ║ %-13s ║ %-12s ║ %-12s ║ %-12s ║ %-13s ║\n",
                            trip.getId(),
                            trip.getVehicle().getName(),
                            trip.getDepartureDateString(),
                            trip.getDeparturePort().getName(),
                            trip.getArrivalDateString(),
                            trip.getArrivalPort().getName(),
                            trip.getStatus().name());
                }
                System.out.println("╚════════════════╩════════════════╩═══════════════╩══════════════╩══════════════╩══════════════╩═══════════════╝");
            } else {
                System.out.println("No trip found!");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date!");
        }
        scanner.nextLine();
    }

    // Case 6.5: List all trips from day A to day B
    public void printListOfTripFromDateToDate() {
        System.out.println("\n══════════════════════════════════════");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Enter start date (dd/mm/yyyy): ");
            String startDateString = scanner.next();
            Date startDate = format.parse(startDateString);
            System.out.print("Enter end date (dd/mm/yyyy): ");
            String endDateString = scanner.next();
            Date endDate = format.parse(endDateString);

            ArrayList<Trip> results = Trip.searchTripBetweenDates(startDate,endDate);

            if (!results.isEmpty()) {
                // print header
                System.out.println("╔════════════════╦════════════════╦══════════════╦══════════════╦══════════════╦══════════════╦═══════════════╗");
                System.out.printf("║ %-12s   ║ %-12s   ║ %-12s ║ %-12s ║ %-12s ║ %-12s ║ %-13s ║\n", "ID","Vehicle", "Departure", "From", "Arrival", "To", "Status");
                System.out.println("╠════════════════╬════════════════╬══════════════╬══════════════╬══════════════╬══════════════╬═══════════════╣");
                for (Trip trip : results) {
                    System.out.printf("║ %-12s   ║ %-12s   ║ %-12s ║ %-12s ║ %-12s ║ %-12s ║ %-13s ║\n",
                            trip.getId(),
                            trip.getVehicle().getName(),
                            trip.getDepartureDateString(),
                            trip.getDeparturePort().getName(),
                            trip.getArrivalDateString(),
                            trip.getArrivalPort().getName(),
                            trip.getStatus().name());
                }
                System.out.println("╚════════════════╩════════════════╩══════════════╩══════════════╩══════════════╩══════════════╩═══════════════╝");
            } else {
                System.out.println("No trip found!");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date!");
        }
        scanner.nextLine();
    }


    /*------After making operation, ask user if they want to continue------*/
    public void continueToOption() throws IOException {
        System.out.println("\n══════════════════════════════════════");
        System.out.print("Continue? (y/n) ");
        String continueToOption = scanner.next();

        // Validate "y" or "n" answer only
        while ((!continueToOption.equalsIgnoreCase("Y")) && (!continueToOption.equalsIgnoreCase("N"))) {
            System.out.println("Please enter a valid option y/n: ");
            continueToOption = scanner.next();
        }

        if(continueToOption.equalsIgnoreCase("Y")) {
            this.showMenuOptions();
        } else if(continueToOption.equalsIgnoreCase("N")) {
            User.endSession();
        }
    }
}
