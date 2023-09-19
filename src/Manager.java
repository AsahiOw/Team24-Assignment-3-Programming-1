import java.util.Scanner;
import Class.Port;

public class Manager extends User {
    private Port managedPort;
    public Manager(String username, String password, Port managedPort) {
        super(username, password);
        this.managedPort = managedPort;
    }
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }
    //    public Port managedPort;
    @Override
    public void showMenuOptions() {
        System.out.println("Welcome Manager of Port " + managedPort.getName() + "! Select an option:");
        System.out.println("1. List vehicles in this port");
        System.out.println("2. List containers in this port");
        System.out.println("3. Add new vehicle");
        System.out.println("4. Add new container");
        System.out.println("5. Remove vehicle");
        System.out.println("6. Remove container");
        System.out.println("7. Vehicle load container");
        System.out.println("8. Vehicle unload container");
        System.out.println("9. Exit");

        // Get input choice and execute relevant method
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
    }

}
