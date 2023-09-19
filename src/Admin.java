import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import Class.*;
import Enum.*;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }

    // Get input choice and execute relevant method
    static Scanner scanner = new Scanner(System.in);
    @Override
    public void showMenuOptions() {
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

                        System.out.println("New port has been added: " + "\n" + new Port(port_name, port_latitude, port_longtitude, port_capacity, port_landingAbility).toString());
                        continueToOption();
                        break;

                    case 2: // Add new vehicle

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
                            System.out.println("New Truck has been added: " + "\n" + new Truck(veh_name, veh_fuel, veh_maxFuel, veh_capacity, veh_maxLoad, null, Port.getPortBasedOnID(port_id), Truck.matchTruckType(veh_type)).toString());
                        } else if (vehicle_type.equalsIgnoreCase("Ship")) {
                            System.out.println("New Ship has been added: " + "\n" + new Ship(veh_name, veh_fuel, veh_maxFuel, veh_capacity, veh_maxLoad, null, Port.getPortBasedOnID(port_id)).toString());
                        }

                        continueToOption();
                        break;

                    case 3: // Add new container
                        System.out.println("\n\t Enter port you want to load this container on: ");
                        String con_portid = scanner.next();


                        Container.addContainer(Port.matchPortID(con_portid));
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

                    case 2: // Remove vehicle

                    case 3: // Remove container
                        Container.getContainers();

                        System.out.println("\n----------------------");
                        System.out.print("Enter id of the container want to remove: ");
                        String containerIdToRemove = scanner.next();
                        Container.removeContainer(containerIdToRemove);

                        continueToOption();
                        break;
                }


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
                    case 1:
                        ArrayList<Container> listOfContainers = new ArrayList<Container>();
                        System.out.print("Select vehicles by ID: ");
                        String veh_ID = scanner.next();

                        System.out.print("Enter container(s) name (type 'stop' at the end): ");
                        String con_Id = scanner.next();
                        while (con_Id.equalsIgnoreCase("stop")) {
                            listOfContainers.add(Container.matchContainerId(con_Id));
                            con_Id = scanner.next();
                        }

                        int countContainers = 0;

                        for (Container c: listOfContainers) {
                            if (!Objects.requireNonNull(Vehicle.matchVehicleId(veh_ID)).loadContainer(c)) {
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
                        continueToOption();
                        break;
                }

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
