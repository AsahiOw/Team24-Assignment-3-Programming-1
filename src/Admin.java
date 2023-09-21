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
//  Open txt file
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
                        User.addUser();
                        continueToOption();
                        break;
                    case 2: // Remove user
                        User.removeUser();
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
                }
                break;
            case 7:
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

        System.out.print("\t\t Enter port's landing ability (true/false): ");
        String port_landingAbility = scanner.next();
        while ((!port_landingAbility.equalsIgnoreCase("true")) || (!port_landingAbility.equalsIgnoreCase("false"))) {
            System.out.print("Enter a true or false for port's landing ability: ");
            port_landingAbility = scanner.next();
        }

        boolean landingAbility;
        if (port_landingAbility.equalsIgnoreCase("true")) landingAbility = true;
        else landingAbility = false;

        Port port = new Port(port_name, port_latitude, port_longtitude, port_capacity, landingAbility);
//      add to data folder
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

    public void addNewVehicle() throws IOException {
        System.out.println("\n----------------------");

        System.out.println("\n\t Enter vehicle information: ");
        System.out.print("\t\t Enter vehicle's name: ");
        String veh_name = scanner.next();
        System.out.print("\t\t Enter vehicle's fuel: ");
        double veh_fuel = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's max fuel: ");
        double veh_maxFuel = scanner.nextDouble();

        while (veh_maxFuel < veh_fuel) {
            System.out.println("Max fuel can't be lower than capacity. Enter max fuel again: ");
            veh_maxFuel = scanner.nextDouble();
        }

        System.out.print("\t\t Enter vehicle's capacity: ");
        double veh_capacity = scanner.nextDouble();
        System.out.print("\t\t Enter vehicle's maxLoad: ");
        double veh_maxLoad = scanner.nextDouble();

        while (veh_maxLoad < veh_capacity) {
            System.out.println("Max load can't be lower than capacity. Enter max load again: ");
            veh_maxLoad = scanner.nextDouble();
        }


        System.out.print("\t\t Enter vehicle's port ID: ");
        String port_id = scanner.next();
        System.out.print("\t Which vehicle you want to add (Enter Truck/Ship): ");
        String vehicle_type = scanner.next();

        while (Port.matchPortID(port_id) == null) {
            System.out.print("\t\t Enter vehicle's port ID again: ");
            port_id = scanner.next();
        }

        if (vehicle_type.equalsIgnoreCase("Truck")) {
            System.out.print("\t\t Enter vehicle's type: ");
            String veh_type = scanner.next();


            if (Port.matchPortID(port_id).getLandingAbility()) {
                //      add to data folder
                Vehicle truck = new Truck(veh_name, veh_fuel, veh_maxFuel, veh_capacity, veh_maxLoad, Port.matchPortID(port_id), Truck.matchTruckType(veh_type));
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
        } else if (vehicle_type.equalsIgnoreCase("Ship")) {
            Vehicle ship = new Ship(veh_name, veh_fuel, veh_maxFuel, veh_capacity, veh_maxLoad, Port.matchPortID(port_id));
            //      add to data folder
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

    public void addNewContainer() throws IOException {
        Container.addNewContainer();
        scanner.nextLine();
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
    public static void removeSelectedPort() throws IOException {
        Port.printListOfPorts();
        System.out.println("----------------------");
        System.out.print("Enter ID of the port you want to remove: ");
        String portIdToRemove = scanner.next();
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
    }
    public static void removeSelectedVehicle() throws IOException {
        Vehicle.printListOfVehicles();
        System.out.println("----------------------");
        System.out.print("Enter ID of the vehicle you want to remove: ");
        String vehicleIdToRemove = scanner.next();
        File inputFile;
        if ("Ship" == Vehicle.matchVehicleId(vehicleIdToRemove).isShipOrTruck()) {
            inputFile = new File("src/Data/Ship.txt");
        } else {
            inputFile = new File("src/Data/Truck.txt");
        }
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        PrintWriter writer = new PrintWriter(new FileWriter(inputFile));
//
//        String lineToRemove = null;
//        String currentLine;
//
//        while((currentLine = reader.readLine()) != null) {
//            String trimmedLine = currentLine.trim();
//            if(trimmedLine.startsWith(vehicleIdToRemove)) {
//                lineToRemove = trimmedLine;
//                continue;
//            }
//            writer.println(currentLine);
//        }
//
//        scanner.nextLine();
//        writer.close();
//        reader.close();
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
    }

    public static void removeSelectedContainer() throws IOException {
        Container.getContainers();
        System.out.println("----------------------");
        Container.getContainers();
        System.out.print("Enter ID of the container you want to remove: ");
        String containerIdToRemove = scanner.next();
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
        System.out.println("Enter ID of the port you want to move to:");
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

    public void printListOfShipInPort() {
        System.out.print("Enter ID of the port you want to view ships list: ");
        String portId = scanner.next();
        Port port = Port.matchPortID(portId);
        System.out.println("╔══════════════╦══════════╦═══════════╦══════════╦══════════╦════════════╦═══════════╗");
        System.out.println("║ Ship Name    ║ Fuel     ║ Max Fuel  ║ Capacity ║ Max Load ║ Port       ║#Containers║");
        System.out.println("╠══════════════╬══════════╬═══════════╬══════════╬══════════╬════════════╬═══════════╣");
        for (Vehicle v : port.getOnPortVehicles()) {
            if (v.isShipOrTruck().equals("Ship")) {
                System.out.printf("║ %-12s ║ %-8.1f ║ %-9.1f ║ %-8.1f ║ %-8.1f ║ %-10s ║ %-9d ║%n",
                        v.getName(),
                        v.getFuel(),
                        v.getMaxFuel(),
                        v.getCapacity(),
                        v.getMaxLoad(),
                        v.getCurrentPort().getName(),
                        v.getNumContainers());
            }
        }
        System.out.println("╚══════════════╩══════════╩═══════════╩══════════╩══════════╩════════════╩═══════════╝");
        scanner.nextLine();
    }

    public void printListOfTripByDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search date (dd/mm/yyyy): ");
        String dateString = scanner.next();
        try {
            Date searchDate = format.parse(dateString);

            ArrayList<Trip> results = Trip.searchTripByDate(searchDate);

            if (!results.isEmpty()) {
                // print header
                System.out.println("╔═════════════╦═════════════╦════════════╦═══════════╦════════════╦═══════════╦═══════════════╗");
                System.out.printf("║ %-9s   ║ %-9s   ║ %-10s ║ %-9s ║ %-10s ║ %-9s ║ %-13s ║\n", "ID","Vehicle", "Departure", "From", "Arrival", "To", "Status");
                System.out.println("╠═════════════╬═════════════╬════════════╬═══════════╬════════════╬═══════════╬═══════════════╣");
                for (Trip trip : results) {
                    System.out.printf("║ %-9s   ║ %-9s   ║ %-10s ║ %-9s ║ %-10s ║ %-9s ║ %-13s ║\n",
                            trip.getId(),
                            trip.getVehicle().getName(),
                            format.format(trip.getDepartureDate()),
                            trip.getDeparturePort().getName(),
                            format.format(trip.getArrivalDate()),
                            trip.getArrivalPort().getName(),
                            trip.getStatus());
                }
                System.out.println("╚═════════════╩═════════════╩════════════╩═══════════╩════════════╩═══════════╩═══════════════╝");
            } else {
                System.out.println("No trip found!");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date!");
        }
        scanner.nextLine();
    }

    public void printListOfTripFromDateToDate() {
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
                System.out.println("╔═════════════╦═════════════╦════════════╦═══════════╦════════════╦═══════════╦═══════════════╗");
                System.out.printf("║ %-9s   ║ %-9s   ║ %-10s ║ %-9s ║ %-10s ║ %-9s ║ %-13s ║\n", "ID","Vehicle", "Departure", "From", "Arrival", "To", "Status");
                System.out.println("╠═════════════╬═════════════╬════════════╬═══════════╬════════════╬═══════════╬═══════════════╣");
                for (Trip trip : results) {
                    System.out.printf("║ %-9s   ║ %-9s   ║ %-10s ║ %-9s ║ %-10s ║ %-9s ║ %-13s ║\n",
                            trip.getId(),
                            trip.getVehicle().getName(),
                            format.format(trip.getDepartureDate()),
                            trip.getDeparturePort().getName(),
                            format.format(trip.getArrivalDate()),
                            trip.getArrivalPort().getName(),
                            trip.getStatus());
                }
                System.out.println("╚═════════════╩═════════════╩════════════╩═══════════╩════════════╩═══════════╩═══════════════╝");
            } else {
                System.out.println("No trip found!");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date!");
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
