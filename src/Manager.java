import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Class.*;
import Enum.*;

public class Manager extends User {
    private Port managedPort;
    public Manager(String username, String password, Port managedPort) {
        super(username, password);
        this.managedPort = managedPort;
    }
    // Get input choice and execute relevant method
    static Scanner scanner = new Scanner(System.in);
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }
    @Override
    public void showMenuOptions() throws FileNotFoundException {
        int sub_option = 0;
        System.out.println("Welcome Manager of Port " + this.managedPort.getName() + "! Select an option:");
        System.out.println("1. Listing Container in port");
        System.out.println("2. Add Container in port");
        System.out.println("3. Remove Container in port");
        System.out.println("4. Statistic Operation");
        System.out.println("5. Exit");

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                managedPort.printOnPortContainers();
                continueToOption();
                break;
            case 2:
                Container.addNewContainer(managedPort);
                continueToOption();
                break;
            case 3:
                managedPort.printOnPortContainers();
                System.out.print("Enter ID of the container you want to remove: ");
                String idToRemove = scanner.next();
                if (Container.matchContainerId(idToRemove).getCurrentPort() == managedPort) {
                    Container.removeContainer(idToRemove);
                } else {
                    System.out.println("You don't have permission to remove this container. This container belongs to other port!");
                }
                continueToOption();
                break;
            case 4:
                System.out.println("1. Total fuel used today");
                System.out.println("2. Total weight of each type of container ");
                System.out.println("3. List all ships in managed port ");
                System.out.println("4. List all trips in a day ");
                System.out.println("5. List all trips from day A to day B");

//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Total fuel used today
                        Trip.totalFuelUsedInPort(managedPort);
                        continueToOption();
                        break;
                    case 2: // Total weight of each type of container
                        printTotalWeightByContainerTypeInPort();
                        continueToOption();
                        break;
                    case 3: // List all ships in managed port
                        printListOfShipInPort();
                        continueToOption();
                        break;
                    case 4: // List all trips in a day
                        printListOfTripByDateInPort();
                        continueToOption();
                        break;
                    case 5: // List all trips from day A to day B
                        printListOfTripFromDateToDateInPort();
                        continueToOption();
                        break;
                }
                continueToOption();
                break;
            case 5:
                break;
        }
    }

    public void printTotalWeightByContainerTypeInPort() {
        System.out.printf("╔%-30s╗%n", "═".repeat(30));

        System.out.print("║Container Type ");
        System.out.printf("%-15s║%n", "Total Weight");

        for (ContainerType type : ContainerType.values()) {
            System.out.print("║");
            System.out.printf("%-15s", type.name());
            System.out.printf("%-15.2f║%n", Container.calculateTotalWeightByTypeInPort(type, managedPort));
        }
        System.out.printf("╚%-30s╝%n", "═".repeat(30));
    }

    public void printListOfShipInPort() {
        System.out.println("╔══════════════╦══════════╦═══════════╦══════════╦══════════╦════════════╦═══════════╗");
        System.out.println("║ Ship Name    ║ Fuel     ║ Max Fuel  ║ Capacity ║ Max Load ║ Port       ║#Containers║");
        System.out.println("╠══════════════╬══════════╬═══════════╬══════════╬══════════╬════════════╬═══════════╣");
        for (Vehicle v : managedPort.getOnPortVehicles()) {
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

    public void printListOfTripByDateInPort() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search date (dd/mm/yyyy): ");
        String dateString = scanner.next();
        try {
            Date searchDate = format.parse(dateString);

            ArrayList<Trip> results = Trip.searchTripByDateInPort(searchDate, managedPort);

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

    public void printListOfTripFromDateToDateInPort() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Enter start date (dd/mm/yyyy): ");
            String startDateString = scanner.next();
            Date startDate = format.parse(startDateString);
            System.out.print("Enter end date (dd/mm/yyyy): ");
            String endDateString = scanner.next();
            Date endDate = format.parse(endDateString);

            ArrayList<Trip> results = Trip.searchTripBetweenDatesInPort(startDate,endDate, managedPort);

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



    public void continueToOption() throws FileNotFoundException {
        System.out.print("\nContinue? (Y/N) ");
        String continueToOption = scanner.next();

        if(continueToOption.equalsIgnoreCase("Y")) {
            this.showMenuOptions();
        } else if(continueToOption.equalsIgnoreCase("N")) {
            System.out.println("Session end.");
        }
    }
}
