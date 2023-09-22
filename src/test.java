import Class.*;
import Enum.*;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        Port p1 = new Port("port1",12.3,456,1000,true);
        Port p2 = new Port("port2",12.3,456,1000,true);

        Vehicle t1 = new Truck("Truck1", 200, 500, 10, 1000, p1, TruckType.REEFER);
        Vehicle s1 = new Ship("Ship 1", 1000, 2000, 50, 10000, p1);
        Vehicle s2 = new Ship("Ship 2", 1000, 2050, 150, 100000, p1);

        new Trip(t1, "21/09/2023", p1, "21/09/2023", p2, TripStatus.IN_PROGRESS);
        new Trip(s1,"21/09/2023" , p1,"21/09/2023" , p2, TripStatus.COMPLETED);
        new Trip(s2, "21/09/2023", p2,"21/09/2023", p1, TripStatus.IN_PROGRESS);

        Container c1 = new Container(12.4, ContainerType.DRY, p1);
        Container c2 = new Container(42.1, ContainerType.OPEN_TOP, p1);
        Container c3 = new Container(42.1, ContainerType.OPEN_TOP, p1);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(p1.getOnPortContainers());

        p1.printOnPortContainers();



        User u1 = new Admin("a","a");
        User u2 = new Manager("m1","123", p1);



//        System.out.println(s1.loadContainer(c1));
//        System.out.println(s1.loadContainer(c2));
//        System.out.println(s2.loadContainer(c3));
//        t1.printListOfContainers();
//        System.out.println(Trip.searchTripsArrivedToday());
    }
}
