import java.util.Scanner;
import Class.*;

public class Manager extends User {
    private Port managedPort;
    public Manager(String username, String password, Port managedPort) {
        super(username, password);
        this.managedPort = managedPort;
    }
    // Get input choice and execute relevant method
    static Scanner scanner = new Scanner(System.in);
    @Override
    public boolean validateCredential(String username, String password) {
        return super.getPassword().equals(password);
    }
    //    public Port managedPort;

    @Override
    public void showMenuOptions() {
        int sub_option = 0;
        System.out.println("Welcome Manager of Port " + this.managedPort.getName() + "! Select an option:");
        System.out.println("1. Listing Container in port");
        System.out.println("2. Add Container in port");
        System.out.println("3. Remove Container in port");
        System.out.println("4. Statistic Operation");
        System.out.println("5. Exit");

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                managedPort.listOnPortContainers();
                continueToOption();
                break;
            case 2:
                Container.addContainer(managedPort);
                continueToOption();
                break;
            case 3:
                managedPort.listOnPortContainers();

                System.out.print("Enter id of the container want to remove: ");
                String idToRemove = scanner.next();

                Container.removeContainer(idToRemove);
                continueToOption();
                break;
        }
    }
    public void continueToOption() {
        System.out.print("\nContinue? (Y/N) ");
        String continueToOption = scanner.next();

        if(continueToOption.equalsIgnoreCase("Y")) {
            this.showMenuOptions();
        } else if(continueToOption.equalsIgnoreCase("N")) {
            System.out.println("Session end.");
        }
    }
}
