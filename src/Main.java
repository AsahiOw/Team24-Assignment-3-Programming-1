import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Class.*;
import Enum.*;

import static Class.Port.getPortByName;
import static Class.Vehicle.getVehicleByName;

public class Main {

    private static Date stringToDate(String dateString) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date " + dateString);
            return null;
        }

    }
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        Port p1 = new Port("port1",12.3,456,1000,true);
        Port p2 = new Port("port2",12.3,456,1000,true);

        Vehicle t1 = new Truck("Truck1", 200, 500, 10, 1000, p1, TruckType.REEFER);
        Vehicle s1 = new Ship("Ship 1", 1000, 2000, 50, 10000, p1);
        p1.printOnPortContainers();


        User u1 = new Admin("a1","123");
        User u2 = new Manager("m1","123", p1);

        Container c1 = new Container(12.4, ContainerType.DRY);
        Container c2 = new Container(42.1,ContainerType.OPEN_TOP);
        Container c3 = new Container(42.1,ContainerType.OPEN_TOP);

        System.out.println(t1.loadContainer(c1));
        System.out.println(t1.loadContainer(c2));
        System.out.println(t1.loadContainer(c3));
        t1.printListOfContainers();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (User.matchUser(username) != null) {
            User user = User.matchUser(username);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (user.validateCredential(username, password)) {
                user.showMenuOptions();

//              Open txt file
                File file1 = new File("src/Data/Port.txt");
                if(!file1.exists()){
                    file1.createNewFile();
                }
                File file2 = new File("src/Data/Container.txt");
                if(!file2.exists()){
                    file2.createNewFile();
                }
                File file3 = new File("src/Data/Trip.txt");
                if(!file3.exists()){
                    file3.createNewFile();
                }
                File file4 = new File("src/Data/Port manager.txt");
                if(!file4.exists()){
                    file4.createNewFile();
                }
                File file5 = new File("src/Data/Admin.txt");
                if(!file5.exists()){
                    file5.createNewFile();
                }
                File file6 = new File("src/Data/Ship.txt");
                if(!file6.exists()){
                    file6.createNewFile();
                }
                File file7 = new File("src/Data/Truck.txt");
                if(!file7.exists()){
                    file7.createNewFile();
                }

//                Add Data from Data folder to system
                Scanner fileScanner1 = new Scanner(file1);
                while (fileScanner1.hasNextLine()) {
                    String line = fileScanner1.nextLine();
                    String[] parts = line.split(",");

                    String name = parts[0];
                    double latitude = Double.parseDouble(parts[1]);
                    double longitude = Double.parseDouble(parts[2]);
                    double capacity = Double.parseDouble(parts[3]);
                    boolean landingAbility = parts[4].equals("true");

                    Port port = new Port(name, latitude, longitude, capacity, landingAbility);
                }
                Scanner fileScanner2 = new Scanner(file2);
                while (fileScanner2.hasNextLine()){
                    String line = fileScanner2.nextLine();
                    String[] parts = line.split(",");
                    double weight = Double.parseDouble(parts[0]);
                    ContainerType type = ContainerType.valueOf(parts[1]);

                    Container container = new Container(weight, type);
                }
                Scanner fileScanner3 = new Scanner(file3);
                while (fileScanner3.hasNextLine()){
                    String line = fileScanner3.nextLine();
                    String[] parts = line.split(",");
                    Vehicle vehicle = getVehicleByName(parts[0]);
                    Date departureDate = stringToDate(parts[1]);
                    Port departurePort = getPortByName(parts[2]);
                    Date arrivalDate = stringToDate(parts[3]);
                    Port arrivalPort = getPortByName(parts[4]);
                    TripStatus status = TripStatus.valueOf(parts[5]);

                    Trip trip = new Trip(vehicle, departureDate, departurePort, arrivalDate, arrivalPort, status);
                }
                Scanner fileScanner4 = new Scanner(file4);
                while (fileScanner4.hasNextLine()){
                    String line = fileScanner4.nextLine();
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String pass = parts[1];
                    Port managedPort = getPortByName(parts[3]);

                    User u = new Manager(name, pass, managedPort);
                }
                Scanner fileScanner5 = new Scanner(file5);
                while (fileScanner5.hasNextLine()){
                    String line = fileScanner5.nextLine();
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String pass = parts[1];

                    User u = new Admin(name, pass);
                }
                Scanner fileScanner6 = new Scanner(file6);
                while (fileScanner6.hasNextLine()){
                    String line = fileScanner6.nextLine();
                    String[] parts = line.split(",");
                    String name = parts[0];
                    double fuel = Double.parseDouble(parts[1]);
                    double maxFuel = Double.parseDouble(parts[2]);
                    double capacity = Double.parseDouble(parts[3]);
                    double maxLoad = Double.parseDouble(parts[4]);
                    Port currentPort = getPortByName(parts[5]);

                    Vehicle vehicle = new Ship(name, fuel, maxFuel, capacity, maxLoad, currentPort);
                }
                Scanner fileScanner7 = new Scanner(file7);
                while (fileScanner7.hasNextLine()){
                    String line = fileScanner7.nextLine();
                    String[] parts = line.split(",");
                    String name = parts[0];
                    double fuel = Double.parseDouble(parts[1]);
                    double maxFuel = Double.parseDouble(parts[2]);
                    double capacity = Double.parseDouble(parts[3]);
                    double maxLoad = Double.parseDouble(parts[4]);
                    Port currentPort = getPortByName(parts[5]);
                    TruckType type = TruckType.valueOf(parts[6]);

                    Vehicle vehicle = new Truck(name, fuel, maxFuel, capacity, maxLoad, currentPort, type);
                }
//              close scanner
                fileScanner1.close();
                fileScanner2.close();
                fileScanner3.close();
                fileScanner4.close();
                fileScanner5.close();
                fileScanner6.close();
                fileScanner7.close();
            }
            else System.out.println("Wrong password!");
        }
        else System.out.println("No matching username!");

        scanner.close();
    }
}