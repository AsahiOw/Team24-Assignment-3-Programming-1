package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TripManager {
    private List<Trip> trips;

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void deleteTrip(){
        List<Trip> allTrips = new ArrayList<>();

        // Get trips from Trip class
        List<Trip> trips = Trip.getTrips();

        // Add trips to list
        for (Trip trip : trips) {
            allTrips.add(trip);
        }
        try {
            // Write trips to Trip.txt
            BufferedWriter tripWriter = new BufferedWriter(new FileWriter("src/Data/Trip.txt", false));
            for (Trip trip : allTrips) {
                tripWriter.write(trip.getId() + ",");
                tripWriter.write(trip.getVehicleName() + ",");
                tripWriter.write(trip.getDepartureDateString() + ",");
                tripWriter.write(trip.getdeparturePortName() + ",");
                tripWriter.write(trip.getArrivalDateString() + ",");
                tripWriter.write(trip.getarrivalPortName() + ",");
                tripWriter.write(trip.getStatus() + "\n");
            }
            tripWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDataAfter7Days() throws IOException {

        // Get current system date
        Date currentDate = new Date();
        List<Trip> tripsCopy = new ArrayList<>(trips);
        // Loop through all trips
        for(Trip trip : tripsCopy) {

            // Check if arrival date is more than 7 days ago
            Calendar arrivalCalendar = Calendar.getInstance();
            arrivalCalendar.setTime(trip.getArrivalDate());
            arrivalCalendar.add(Calendar.DAY_OF_MONTH, 7);

            if(currentDate.after(arrivalCalendar.getTime())) {
                // Remove old trip
                System.out.println("The " +trip.getId() + " has date of " + trip.getArrivalDateString() + " has been removed.");
                System.out.println("To keep the data for validation, the trip data will not be Deleted in Data folder.");
                System.out.println("If You want to remove this function, Add comment line 118 to 120 in Main.java and rerun the code.");
                trips.remove(trip);
            }

        }

    }
}
