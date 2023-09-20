package Class;

import java.util.ArrayList;

public class Port {
    private String id;
    private static int nextId = 1;
    private String name;
    private double latitude;
    private double longitude;
    private double capacity;
    private boolean landingAbility;
    private static ArrayList<Port> ports = new ArrayList<Port>();
    private ArrayList<Vehicle> onPortVehicles;
    private ArrayList<Container> onPortContainers;


    public static Port matchPortID(String portId) {
        for (Port p: ports) {
            if (p.id.equalsIgnoreCase(portId)) {
                return p;
            }
        }
        System.out.println("No matching port!");
        return null;
    }

    public void addVehicle(Vehicle vehicle) {
        onPortVehicles.add(vehicle);
    }
    public void addContainerToPort(Container container) {
        onPortContainers.add(container);
        container.setPort(this);
    }

    public ArrayList<Container> getOnPortContainers() {
        return onPortContainers;
    }
    public void printOnPortContainers() {
        System.out.println("List of containers on " + this.name + ": ");
        for (Container c: onPortContainers) {
            System.out.println("\t" + c.toString());
        }
    }

    public ArrayList<Vehicle> getOnPortVehicles() {
        return onPortVehicles;
    }

    public void removeContainer(Container c) {
        onPortContainers.remove(c);
    }

    // constructor, getters, setters

    public Port(String name, double latitude, double longitude, double capacity, boolean landingAbility) {
        this.id = "port"+nextId++;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.landingAbility = landingAbility;
        ports.add(this);
        onPortVehicles = new ArrayList<Vehicle>();
        onPortContainers = new ArrayList<Container>();
    }
    public void addContainer(Container c) {
        onPortContainers.add(c);
    }

    public String getId() {
        return id;
    }
    public static void printListOfPorts() {
        for (Port p: ports) {
            System.out.println(p.toString());
        }
    }

    public static void removePort(String idToRemove) {
            Port port = Port.matchPortID(idToRemove);

            port.removeAllVehicle();
            // Update to remove container in file
            ports.remove(port);
            port = null;
            System.out.println("Port " + idToRemove + " removed successfully!");
    }
    public void removeVehicle(Vehicle vehicle) {
        onPortVehicles.remove(vehicle);
    }
    public void removeAllVehicle() {
        while (onPortVehicles.size() > 0) {
            Vehicle.removeVehicle(onPortVehicles.get(onPortVehicles.size()-1).getId());
        }
    }

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

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

//    method
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
        double distance = R * c * 1000; // convert to meters

        return distance;
    }

    public static Port getPortByName(String name) {

        for (Port port : ports) {
            if (port.getName().equals(name)) {
                return port;
            }
        }

        return null;
    }
}
