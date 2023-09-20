import javax.lang.model.type.ArrayType;
import java.io.*;
import java.util.*;
import Class.*;

public abstract class User implements Serializable {
    private String username;
    private String password;
    private String id;
    public static HashMap<String,User> matchUsername = new HashMap<String, User>();
    public static ArrayList<User> users = new ArrayList<User>();
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
        this.username = username;
        this.password = password;
        matchUsername.put(username, this);
        users.add(this);
        saveUserToFile(this);
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

    public static void addUser() {
        System.out.println("Enter User information ");
        System.out.print("\t Username: ");
        String username = scanner.next();
        while (matchUsername.get(username) != null) {
            username = new String();
            System.out.println("User already exists!");
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
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║     New Admin created successfully!    ║");
            System.out.println("╚════════════════════════════════════════╝");
        } else if (!isAdmin) {
            System.out.print("\t Which portID this manager manage: ");
            String portID = scanner.next();
            User newUser = new Manager(username, password, Port.matchPortID(portID));
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║    New Manager created successfully!   ║");
            System.out.println("╚════════════════════════════════════════╝");
        }
        scanner.nextLine();
    }

    public static void removeUser() {
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
            User user = matchUser(username);
            users.remove(user);
            user = null;
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║       User deleted successfully!       ║");
            System.out.println("╚════════════════════════════════════════╝");
        }
        scanner.nextLine();
    }


    public abstract boolean validateCredential(String username, String password);
    public void showMenuOptions() throws IOException {};
}
