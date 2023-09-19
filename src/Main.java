import java.util.*;
import Class.*;
import Enum.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        Port p1 = new Port("port1",12.3,456,1000,true);
        Port p2 = new Port("port2",12.3,456,1000,true);

        Truck t1 = new Truck("Truck1", 200, 500, 10, 1000, null, p1, TruckType.REEFER);
        Ship s1 = new Ship("Ship 1", 1000, 2000, 50, 10000, null, p2);

        User u1 = new Admin("a1","123");
        User u2 = new Manager("m1","123", p1);

        Container c1 = new Container(12.4, ContainerType.DRY, p1);
        Container c2 = new Container(42.1,ContainerType.OPEN_TOP, p2);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (User.matchUser(username) != null) {
            User user = User.matchUser(username);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (user.validateCredential(username, password)) { user.showMenuOptions(); }
            else System.out.println("Wrong password!");
        }
        else System.out.println("No matching username!");

        scanner.close();
    }
}