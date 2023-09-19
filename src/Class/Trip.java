package Class;

import Interface.Vehicle;

import java.util.Date;

import Enum.TripStatus;
public class Trip {
    private String id;
    private static int nextId = 1;
    private Vehicle vehicle;
    private Date departureDate;
    private Port departurePort;
    private Date arrivalDate;
    private Port arrivalPort;
    private TripStatus status;

    // constructor, getters, setters

    public Trip(String id, Vehicle vehicle, Date departureDate, Port departurePort, Date arrivalDate, Port arrivalPort, TripStatus status) {
        this.id = "trip" + nextId++;
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.departurePort = departurePort;
        this.arrivalDate = arrivalDate;
        this.arrivalPort = arrivalPort;
        this.status = status;
    }

    public String getId() { return id; }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Port departurePort) {
        this.departurePort = departurePort;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(Port arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
}
