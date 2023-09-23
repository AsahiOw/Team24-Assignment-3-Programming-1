import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import Class.*;
import Enum.*;

public class Main {
    public static void main(String[] args) throws IOException {

//      Open txt file
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

            String name = parts[1];
            double latitude = Double.parseDouble(parts[2]);
            double longitude = Double.parseDouble(parts[3]);
            double capacity = Double.parseDouble(parts[4]);
            boolean landingAbility = parts[5].equals("true");

            Port port = new Port(name, latitude, longitude, capacity, landingAbility);
        }
        Scanner fileScanner4 = new Scanner(file4);
        while (fileScanner4.hasNextLine()){
            String line = fileScanner4.nextLine();
            String[] parts = line.split(",");
            String name = parts[1];
            String pass = parts[2];
            Port managedPort = Port.matchPortID(Port.getIdByName(parts[3]));
            User u = new Manager(name, pass, managedPort);
        }
        Scanner fileScanner5 = new Scanner(file5);
        while (fileScanner5.hasNextLine()){
            String line = fileScanner5.nextLine();
            String[] parts = line.split(",");
            String name = parts[1];
            String pass = parts[2];

            User u = new Admin(name, pass);
        }
        Scanner fileScanner6 = new Scanner(file6);
        while (fileScanner6.hasNextLine()){
            String line = fileScanner6.nextLine();
            String[] parts = line.split(",");
            String name = parts[1];
            double fuel = Double.parseDouble(parts[2]);
            double maxFuel = Double.parseDouble(parts[3]);
            double capacity = Double.parseDouble(parts[4]);
            double maxLoad = Double.parseDouble(parts[5]);
            Port currentPort = Port.matchPortID(Port.getIdByName(parts[6]));
            Vehicle vehicle = new Ship(name, fuel, maxFuel, capacity, maxLoad, currentPort);
        }
        Scanner fileScanner7 = new Scanner(file7);
        while (fileScanner7.hasNextLine()){
            String line = fileScanner7.nextLine();
            String[] parts = line.split(",");
            String name = parts[1];
            double fuel = Double.parseDouble(parts[2]);
            double maxFuel = Double.parseDouble(parts[3]);
            double capacity = Double.parseDouble(parts[4]);
            double maxLoad = Double.parseDouble(parts[5]);
            Port currentPort = Port.matchPortID(Port.getIdByName(parts[6]));
            TruckType type = TruckType.valueOf(parts[7]);

            Vehicle vehicle = new Truck(name, fuel, maxFuel, capacity, maxLoad, currentPort, type);
        }
        List<Trip> trips = new ArrayList<>();
        Scanner fileScanner3 = new Scanner(file3);
        while (fileScanner3.hasNextLine()){
            String line = fileScanner3.nextLine();
            String[] parts = line.split(",");
            Vehicle vehicle = Vehicle.matchVehicleId(Vehicle.getIdByName(parts[1]));
            String departureDate = parts[2];
            Port departurePort = Port.matchPortID(Port.getIdByName(parts[3]));
            String arrivalDate = parts[4];
            Port arrivalPort = Port.matchPortID(Port.getIdByName(parts[5]));
            TripStatus status = TripStatus.valueOf(parts[6]);

            Trip trip = new Trip(vehicle, departureDate, departurePort, arrivalDate, arrivalPort, status);
            trips.add(trip);
        }
        TripManager manager = new TripManager();
        manager.setTrips(trips);
        manager.deleteDataAfter7Days();

        Scanner fileScanner2 = new Scanner(file2);
        while (fileScanner2.hasNextLine()) {
            String line = fileScanner2.nextLine();
            String[] parts = line.split(",");
            double weight = Double.parseDouble(parts[1]);
            ContainerType type = ContainerType.valueOf(parts[2]);
            ContainerState containerState = ContainerState.valueOf(parts[3]);

            if (containerState == ContainerState.NEITHER) {
                Container container = new Container(weight, type);
            } else if (containerState == ContainerState.ON_PORT) {
                String port_name = String.valueOf(parts[4]);
                Container container = new Container(weight, type, Objects.requireNonNull(Port.matchPortID(Port.getIdByName(port_name))));
            } else if (containerState == ContainerState.ON_VEHICLE) {
                String vehicle_name = String.valueOf(parts[4]);
                Container container = new Container(weight, type, Objects.requireNonNull(Vehicle.matchVehicleId(Vehicle.getIdByName(vehicle_name))));
            }
        }
//              close scanner
        fileScanner1.close();
        fileScanner2.close();
        fileScanner3.close();
        fileScanner4.close();
        fileScanner5.close();
        fileScanner6.close();
        fileScanner7.close();

//        Main program
        Scanner scanner = new Scanner(System.in);
        LocalDate now = LocalDate.now();

//      Delete trip has arrival date 7 days earlier than today
        for (Trip trip: Trip.getTrips()) {
            LocalDate newArrivalDate = trip.getArrivalDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (newArrivalDate.compareTo(now) <= -7) {
                Trip.removeTrip(trip);
            }
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (User.matchUser(username) != null) {
            User user = User.matchUser(username);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (user.validateCredential(username, password)) {
                user.showMenuOptions();
            }
            else System.out.println("Wrong password!");
        }
        else System.out.println("No matching username!");

        scanner.close();
    }
}