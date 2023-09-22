package Class;
import Enum.TripStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Trip {
    private final String id;
    private static int nextId = 1;
    private Vehicle vehicle;
    private Date departureDate;
    private Port departurePort;
    private Date arrivalDate;
    private Port arrivalPort;
    private TripStatus status;
    private static ArrayList<Trip> trips = new ArrayList<>();

    // constructor, getters, setters

    public Trip(Vehicle vehicle, Date departureDate, Port departurePort, Date arrivalDate, Port arrivalPort, TripStatus status) {
        this.id = "trip" + nextId++;
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.departurePort = departurePort;
        this.arrivalDate = arrivalDate;
        this.arrivalPort = arrivalPort;
        this.status = status;
        trips.add(this);
    }

    public Trip(Vehicle vehicle, String departureDate, Port departurePort, String arrivalDate, Port arrivalPort, TripStatus status) {
        this.id = "trip" + nextId++;
        this.vehicle = vehicle;
        try {
            this.departureDate = new SimpleDateFormat("dd/MM/yyyy").parse(departureDate);
        } catch (ParseException e) {
            System.out.println("Failed!");
        }
        this.departurePort = departurePort;
        try {
            this.arrivalDate = new SimpleDateFormat("dd/MM/yyyy").parse(arrivalDate);
        } catch (ParseException e) {
            System.out.println("Invalid Date!");
        }
        this.arrivalPort = arrivalPort;
        this.status = status;
        trips.add(this);
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

    public static ArrayList<Trip> searchTripByDate(Date searchDate) {
        ArrayList<Trip> matchedTrip = new ArrayList<>();

        for (Trip trip : trips) {
            if (searchDate.equals(trip.departureDate) || searchDate.equals(trip.arrivalDate)) {
                matchedTrip.add(trip);
            }
        }

        return matchedTrip;
    }

    public static ArrayList<Trip> searchTripByDateInPort(Date searchDate, Port port) {
        ArrayList<Trip> matchedTrip = new ArrayList<>();

        for (Trip trip : trips) {
            if (searchDate.equals(trip.departureDate) || searchDate.equals(trip.arrivalDate))
                if (trip.arrivalPort == port || trip.departurePort == port) {
                    matchedTrip.add(trip);
                }
        }
        return matchedTrip;
    }

    public static ArrayList<Trip> searchTripBetweenDates(Date startDate, Date endDate) {
        ArrayList<Trip> matchedTrips = new ArrayList<>();

        for (Trip trip : trips) {
            if ((trip.departureDate.compareTo(startDate) >= 0 && trip.departureDate.compareTo(endDate) <= 0)
                    || (trip.arrivalDate.compareTo(startDate) >= 0 && trip.arrivalDate.compareTo(endDate) <= 0)) {
                matchedTrips.add(trip);
            }
        }

        return matchedTrips;
    }

    public static ArrayList<Trip> searchTripBetweenDatesInPort(Date startDate, Date endDate, Port port) {
        ArrayList<Trip> matchedTrips = new ArrayList<>();
        for (Trip trip : trips) {
            if ((trip.departureDate.compareTo(startDate) >= 0 && trip.departureDate.compareTo(endDate) <= 0)
                    || (trip.arrivalDate.compareTo(startDate) >= 0 && trip.arrivalDate.compareTo(endDate) <= 0))
                if (trip.arrivalPort == port || trip.departurePort == port) {
                    matchedTrips.add(trip);
                }
        }
        return matchedTrips;
    }

    public static ArrayList<Trip> searchTripsArrivedToday() {
        ArrayList<Trip> matchedTrips = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (Trip trip : trips) {
            LocalDate newArrivalDate = trip.arrivalDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (newArrivalDate.isEqual(now)){
                    matchedTrips.add(trip);
            }
        }
        return matchedTrips;
    }

    public static double totalFuelUsedToday() {
        double total = 0;
        ArrayList<Trip> tripsArrivedToday = searchTripsArrivedToday();

        for (Trip trip: tripsArrivedToday) {
            total += trip.getVehicle().getMaxFuel() - trip.getVehicle().getFuel();
//            trip.getVehicle().addFuel(trip.getVehicle().getMaxFuel() - trip.getVehicle().getFuel());
        }
        return total;
    }

    public static double totalFuelUsedInPort(Port port) {
        double total = 0;
        ArrayList<Trip> tripsArrivedToday = searchTripsArrivedToday();

        for (Trip trip: tripsArrivedToday) {
            if (trip.arrivalPort == port) {
                total += trip.getVehicle().getMaxFuel() - trip.getVehicle().getFuel();
            }
        }
        return total;
    }
}
