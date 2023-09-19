package Class;
import Enum.UserRole;

import static Enum.UserRole.ADMIN;
import static Enum.UserRole.PORT_MANAGER;

abstract class User {
    private int id;
    private String username;
    private String password;
    private static UserRole role;
    private boolean isLoggedIn = false;
    private boolean Administration = false;

    //    constructor, setter, getter
    public User(int id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public abstract void continueToOption();
    public abstract void showMenuOptions();
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isAdministration() {
        return Administration;
    }

    public void setAdministration(boolean administration) {
        Administration = administration;
    }

    //    method
    public boolean login(String inputUsername, String inputPassword) {

        // Check if username matches
        if (!inputUsername.equals(this.username)) {
            System.out.println("Invalid Login");
            return false;
        }

        // Check if password matches
        if (!inputPassword.equals(this.password)) {
            System.out.println("Invalid Login");
            return false;
        }

        // Login successful
        isLoggedIn = true;
        System.out.println("Login successful");
        // Check user role
        if (User.getRole() == ADMIN) {
            setAdministration(true);
        } else if (User.getRole() == PORT_MANAGER) {
            setAdministration(false);
        }
        return true;
    }

    public void logout() {
        // Reset login status
        isLoggedIn = false;
    }
}




