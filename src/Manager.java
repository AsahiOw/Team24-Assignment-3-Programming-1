import java.io.FileNotFoundException;
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

                System.out.print("Enter id of the container want to remove: ");
                String idToRemove = scanner.next();

                Container.removeContainer(idToRemove);
                continueToOption();
                break;
            case 4:
                System.out.println("1. Total fuel used today in " + this.managedPort.getName() +" port");
                System.out.println("2. Total weight of each type of container in " + this.managedPort.getName()+" port");
                System.out.println("3. List all ships in " + this.managedPort.getName() + " port");
                System.out.println("4. List all trips in a day from " + this.managedPort.getName() + " port");
                System.out.println("5. List all trips from day A to day B from " + this.managedPort.getName() + " port");
//              Select Option
                System.out.println("\n----------------------");
                System.out.print("\nSelect Option: ");
                sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1: // Total fuel used today
                        continueToOption();
                        break;
                    case 2:
                        printTotalWeightByContainerType();
                        continueToOption();
                        break;
                    case 3:
                        printListOfShipInPort();
                        continueToOption();
                        break;
                    case 4:
                        continueToOption();
                        break;
                    case 5:
                        continueToOption();
                        break;
                }
        }
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
    public void printListOfShipInPort() {
        Port port = Port.matchPortID(managedPort.getId());
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
}
