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
                trips.remove(trip);
            }

        }

    }
}
