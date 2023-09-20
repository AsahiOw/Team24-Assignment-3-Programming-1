import java.io.*;
import java.util.*;

public abstract class User implements Serializable {
    private String username;
    private String password;
    private String id;
    public static HashMap<String,User> matchUsername = new HashMap<String, User>();

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


    public abstract boolean validateCredential(String username, String password);
    public void showMenuOptions() throws IOException {};
}
