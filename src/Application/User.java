package Application;

import java.io.*;
import java.util.*;
import Class.*;
import Enum.*;

public abstract class User implements Serializable {
    private final String username;
    private String password;
    private final String id;
    private static int nextId = 1;
    public static HashMap<String,User> matchUsername = new HashMap<>();
    public static ArrayList<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static User matchUser(String username) {
        return matchUsername.get(username);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.id = "user"+nextId++;
        this.username = username;
        this.password = password;
        matchUsername.put(username, this);
        users.add(this);
        saveUserToFile(this);
    }

    public String getId() {
        return id;
    }

    public static void saveUserToFile(User user) {
        try {
            FileOutputStream file = new FileOutputStream("users.data");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(user);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser() throws IOException {
        System.out.println("Enter Application.User information ");
        System.out.print("\t Username: ");
        String username = scanner.next();
        while (matchUsername.get(username) != null) {
            username = new String();
            System.out.println("Application.User already exists!");
            System.out.print("\t Enter new username: ");
            username = scanner.next();
        }

        System.out.print("\t Password: ");
        String password = scanner.next();
        System.out.print("\t Confirm password: ");
        String confirmPassword = scanner.next();
        while (!password.equals(confirmPassword)) {
            confirmPassword = new String();

            System.out.println("Password do not match, please enter new password.");
            System.out.print("\t Password: ");
            password = scanner.next();
            System.out.print("\t Confirm password: ");
            confirmPassword = scanner.next();
        }
        while ((password.trim().isEmpty()) || (password.contains(" "))) {
            confirmPassword = new String();
            System.out.println("Password cannot be empty or contain space, please enter new password.");
            System.out.print("\t Password: ");
            password = scanner.next();
            System.out.print("\t Confirm password: ");
            confirmPassword = scanner.next();
        }
        System.out.print("Is this user an admin or manager? (admin/manager) ");
        boolean isAdmin = scanner.next().equalsIgnoreCase("admin");

        if (isAdmin) {
            User newUser = new Admin(username, password);
            FileWriter writer = new FileWriter("src/Data/Application.Admin.txt", true);
            writer.write(newUser.getId() + ",");
            writer.write(newUser.getUsername() + ",");
            writer.write(newUser.getPassword() + "\n");
            writer.close();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║     New Application.Admin created successfully!    ║");
            System.out.println("╚════════════════════════════════════════╝");
        } else {
            Port.printListOfPorts();
            System.out.print("\t Which portID this manager manage: ");
            String portID = scanner.next();
            User newUser = new Manager(username, password, Port.matchPortID(portID));
            FileWriter writer = new FileWriter("src/Data/Port manager.txt", true);
            writer.write(newUser.getId() + ",");
            writer.write(newUser.getUsername() + ",");
            writer.write(newUser.getPassword() + ",");
            writer.write(((Manager) newUser).getManagedPortName() + "\n");
            writer.close();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║    New Application.Manager created successfully!   ║");
            System.out.println("╚════════════════════════════════════════╝");
        }
        scanner.nextLine();
    }

    public static void promptRemoveUser() throws IOException {
        System.out.println("Enter User information ");
        System.out.print("\t Username: ");
        String username = scanner.next();
        while (matchUsername.get(username) == null) {
            username = new String();
            System.out.println("User does not exists!");
            System.out.print("\t Enter username again: ");
            username = scanner.next();
        }

        System.out.print("Confirm that you want to delete user " + username + " (y/n): ");
        boolean isConfirmed = scanner.next().equalsIgnoreCase("y");

        if (isConfirmed) {
            removeUser(matchUser(username));
        }
        scanner.nextLine();
    }

    public static void removeUser(User user) throws IOException {
        File inputFile;
        inputFile = new File("src/Data/Port manager.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<String> lines = new ArrayList<>();
        String lineFromFile;
        while ((lineFromFile = reader.readLine()) != null) {
            lines.add(lineFromFile);
        }
        reader.close();

        // Remove matching line from array
        for (int i=0; i<lines.size(); i++) {
            if (lines.get(i).startsWith(user.getUsername())) {
                lines.remove(i);
                break;
            }
        }
        // Write array back to file
        PrintWriter writer = new PrintWriter(inputFile);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
        users.remove(user);
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       User deleted successfully!       ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    public static void listAllManagers() {
        System.out.println("List of containers: ");
        System.out.println("╔══════════════╦══════════════╗");
        System.out.println("║  Username    ║ Managed Port ║");
        System.out.println("╠══════════════╬══════════════╣");
        for (User user : users) {
            if (user instanceof Manager) {
                System.out.printf("║ %-12s ║ %-12s ║\n", user.getUsername(), ((Manager) user).getManagedPortName());
            }
        }
        System.out.println("╚══════════════╩══════════════╝");
    }

    public static User matchPortManager(Port port) {
        for (User user: users) {
            if ((user instanceof Manager) && ((Manager) user).getManagedPortName() == port.getName()) {
                return user;
            }
        }
        return null;
    }

    public static void endSession() {
        System.out.println("You have been logged out!");
        System.out.println("Session ended.");
    }


    public abstract boolean validateCredential(String username, String password);
    public void showMenuOptions() throws IOException {}
}
