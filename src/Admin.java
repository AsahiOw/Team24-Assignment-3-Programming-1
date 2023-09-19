import java.util.Scanner;
import Class.*;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }

    @Override
    public void showMenuOptions() {
        System.out.println("Welcome Admin! Select an option: \n");
        System.out.println("1. Listing Option");
        System.out.println("2. Entity Management (Add)");
        System.out.println("3. Remove");
        System.out.println("4. Vehicle Options");
        System.out.println("5. User Management");
        System.out.println("6. Statistic Operation");
        System.out.println("7. Exit");

        // Get input choice and execute relevant method
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:

//              List of Options
                System.out.println("1. List all ports");
                System.out.println("2. List all vehicles");
                System.out.println("3. List all containers");
                System.out.println("4. List all managers");

//              Select Option
                System.out.print("\nSelect Option: ");
                int sub_option = scanner.nextInt();
                switch (sub_option) {
                    case 1:
                        Port.getPorts();
                        continueToOption();
                        break;
                    case 2:
                        Truck.getTrucks();
                        Ship.getShips();
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

            case 2:
//              List of Options
                System.out.println("1. Add new port");
                System.out.println("2. Add new vehicle");
                System.out.println("3. Add new container");

//              Select Option


                default:
                    System.out.println("Unexpected value: " + option + ", please select again!");
                    showMenuOptions();
                    break;
                break;
            case 3:
                System.out.println("1. Remove port");
                System.out.println("2. Remove vehicle");
                System.out.println("3. Remove container");
            case 4:
                System.out.println("1. Vehicle load container");
                System.out.println("2. Vehicle unload container");
                System.out.println("3. Vehicle move to port");
                System.out.println("4. Fuel up vehicle");
            case 5:
                System.out.println("1. Add user");
                System.out.println("2. Remove user");
            case 6:
                System.out.println("1. Total fuel used today");
                System.out.println("2. Total weight of each type of container ");
                System.out.println("3. List all ships in a port ");
                System.out.println("4. List all trips in a day ");
                System.out.println("5. List all trips from day A to day B");
            default:
                System.out.println("Unexpected value: " + option + ", please select again!");
                showMenuOptions();
                break;
        }
    }

    public void continueToOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nContinue? (Y/N) ");
        String option = scanner.nextLine();

        if(option.equalsIgnoreCase("Y")) {
            showMenuOptions();
        } else if(option.equalsIgnoreCase("N")) {
            System.out.println("Session end.");
            return;
        }
    }
}
