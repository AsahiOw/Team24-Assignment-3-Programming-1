import java.util.Scanner;
import Class.*;


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
        int sub_option = 0;
        System.out.println("Welcome Manager of Port " + managedPort.getName() + "! Select an option:");
        System.out.println("1. Listing Container in port");
        System.out.println("2. Add Container in port");
        System.out.println("3. Remove Container in port");
        System.out.println("4. Statistic Operation");
        System.out.println("5. Exit");

        // Get input choice and execute relevant method
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                managedPort.getOnPortContainers();
                continueToOption();
                break;

            case 2:
                System.out.print("Enter container weight: ");
                double Weight = scanner.nextDouble();

                System.out.print("Enter container type: ");
                break;
            case 3:
                managedPort.getOnPortContainers();

                System.out.print("Enter id of the container want to remove:");
                String idToRemove = scanner.nextLine();

                Container.removeContainer(idToRemove);
                break;
        }
    }
    public void continueToOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nContinue? (Y/N) ");
        String option = scanner.nextLine();

        if(option.equalsIgnoreCase("Y")) {
            showMenuOptions();
        } else if(option.equalsIgnoreCase("N")) {
            System.out.println("Session end.");
            return;
        }
    }
}
