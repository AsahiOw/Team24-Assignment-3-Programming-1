package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Enum.*;
import Application.*;

public class Port {
    private final String id;
    private static int nextId = 1;
    private String name;
    private double latitude;
    private double longitude;
    private double capacity;
    private boolean landingAbility;
    private static ArrayList<Port> ports = new ArrayList<>();
    private ArrayList<Vehicle> onPortVehicles;
    private ArrayList<Container> onPortContainers;

    public Port(String name, double latitude, double longitude, double capacity, boolean landingAbility) {
        this.id = "port"+nextId++;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.landingAbility = landingAbility;
        ports.add(this);
        onPortVehicles = new ArrayList<>();
        onPortContainers = new ArrayList<>();
    }

//  Setter and Getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean getLandingAbility() {
        return landingAbility;
    }
    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public ArrayList<Vehicle> getOnPortVehicles() {
        return onPortVehicles;
    }

    public ArrayList<Container> getOnPortContainers() {
        return onPortContainers;
    }


//  Adding/Removing entities of Port
    public void addContainer(Container c) {
    onPortContainers.add(c);
}
    public void removeContainer(Container c) {
        onPortContainers.remove(c);
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle instanceof Truck) {
            if (!landingAbility) {
                System.out.println("This port can't have truck.");
            } else onPortVehicles.add(vehicle);
        } else {
            onPortVehicles.add(vehicle);
        }
    }
    public static String getIdByName(String name) {

        for (Port port : ports) {
            if (port.getName().equals(name)) {
                return port.getId();
            }
        }
        return null;
    }
    public static String getNameById(String id) {

        for (Port port : ports) {
            if (port.getId().equals(id)) {
                return port.getName();
            }
        }
        return null;
    }
    public void addContainerToPort(Container container) {
        onPortContainers.add(container);
        container.setPort(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        onPortVehicles.remove(vehicle);
    }
    public void removeAllVehicle() throws IOException {
        while (!onPortVehicles.isEmpty()) {
            Vehicle.removeVehicle(onPortVehicles.get(onPortVehicles.size() - 1).getId());
        }

        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Truck> trucks = new ArrayList<>();
        for (Vehicle v : Vehicle.getVehicles()) {
            if (v instanceof Ship) {
                ships.add((Ship) v);
            } else if (v instanceof Truck) {
                trucks.add((Truck) v);
            }
        }
        try {
            // Write ships to ship.txt
            BufferedWriter shipWriter = new BufferedWriter(new FileWriter("src/Data/Ship.txt", false));
            for (Ship ship : ships) {
                shipWriter.write(ship.getId() + ",");
                shipWriter.write(ship.getName() + ",");
                shipWriter.write(ship.getFuel() + ",");
                shipWriter.write(ship.getMaxFuel() + ",");
                shipWriter.write(ship.getCapacity() + ",");
                shipWriter.write(ship.getMaxLoad() + ",");
                shipWriter.write(ship.getCurrentPortName() + "\n");
            }
            shipWriter.close();

            // Write trucks to truck.txt
            BufferedWriter truckWriter = new BufferedWriter(new FileWriter("src/Data/Truck.txt", false));
            for (Truck truck : trucks) {
                truckWriter.write(truck.getId() + ",");
                truckWriter.write(truck.getName() + ",");
                truckWriter.write(truck.getFuel() + ",");
                truckWriter.write(truck.getMaxFuel() + ",");
                truckWriter.write(truck.getCapacity() + ",");
                truckWriter.write(truck.getMaxLoad() + ",");
                truckWriter.write(truck.getCurrentPortName() + ",");
                truckWriter.write(truck.getType() + "\n");
            }
            truckWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeAllContainer() throws IOException {
        while (!onPortContainers.isEmpty()) {
            File inputFile = new File("src/Data/Container.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            List<String> lines = new ArrayList<>();
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                lines.add(lineFromFile);
            }
            reader.close();

            // Remove matching line from array
            for (int i=0; i<lines.size(); i++) {
                if (lines.get(i).startsWith(onPortContainers.get(onPortContainers.size()-1).getId())) {
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
            Container.removeContainer(onPortContainers.get(onPortContainers.size()-1).getId());
        }
    }
//  Other method

    // Calculating distance from this port to other port
    public double distanceTo(Port port) {
        double lat1 = this.latitude;
        double lon1 = this.longitude;
        double lat2 = port.getLatitude();
        double lon2 = port.getLongitude();
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }

    // Find port by String of port ID
    public static Port matchPortID(String portId) {
        for (Port p: ports) {
            if (p.id.equalsIgnoreCase(portId)) {
                return p;
            }
        }
//        System.out.println("No matching port!");
        return null;
    }

    // Print all containers in a port
    public void printOnPortContainers() {
        String onPortorVehicle;

        System.out.println("List of containers on " + this.name + ": ");
        System.out.println("╔══════════════╦══════════╦═══════════╦══════════╦══════════════╗");
        System.out.println("║  Container   ║ Weight   ║ Type      ║ State    ║ Port/Vehicle ║");
        System.out.println("╠══════════════╬══════════╬═══════════╬══════════╬══════════════╬");

        for (Container container: onPortContainers) {
            if (container.getCurrentState() == ContainerState.NEITHER) onPortorVehicle = "None";
            else onPortorVehicle = (container.getCurrentState() == ContainerState.ON_PORT) ? container.getCurrentPort().getId() : container.getCurrentVehicle().getId();
            System.out.printf("║ %-12s ║ %-8.1f ║ %-9s ║ %-8s ║ %-12s ║%n",
                    container.getId(),
                    container.getWeight(),
                    container.getType().name(),
                    container.getCurrentState().name(),
                    onPortorVehicle);
        }
        System.out.println("╚══════════════╩══════════╩═══════════╩══════════╩══════════════╝");
    }

    // Print all ports available
    public static void printListOfPorts() {
        System.out.println("╔═════════════╦══════════════╦════════════╦═══════════╦═════════════════╗");
        System.out.printf("║ %-9s   ║ %-10s   ║ %-10s ║ %-9s ║ %-15s ║\n", "Port","Latitude", "Longitude", "Capacity", "Landing Ability");
        System.out.println("╠═════════════╬══════════════╬════════════╬═══════════╬═════════════════╣");
        for (Port port: ports) {
            System.out.printf("║ %-9s   ║ %-10.1f   ║ %-10.1f ║ %-9f ║ %-15s ║\n",
                    port.getId(),
                    port.getLatitude(),
                    port.getLongitude(),
                    port.getCapacity(),
                    port.getLandingAbility());
        }
        System.out.println("╚═════════════╩══════════════╩════════════╩═══════════╩═════════════════╝");
    }

    // Remove port selected by ID
    public static void removePort(String idToRemove) throws IOException {
        Port port = Port.matchPortID(idToRemove);

        for (Trip trip: Trip.getTrips()) {
            if ((trip.getArrivalPort() == port) || (trip.getDeparturePort() == port)) {
                Trip.removeTrip(trip);
            }
        }

        User.removeUser(User.matchPortManager(port));

        if (!Objects.requireNonNull(port).onPortVehicles.isEmpty()) port.removeAllVehicle();
        // Update to remove container in file

        port.removeAllContainer();
        System.out.println("Port " + idToRemove + " removed successfully!");
        ports.remove(port);
        port = null;
    }
// toString method
    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", capacity=" + capacity +
                ", landingAbility=" + landingAbility +
                '}';
    }
}
