package Class;
import Enum.UserRole;
public class SystemAdmin extends User {
    public SystemAdmin(int id, String username, String password, UserRole role) {
        super(id, username, password, role);
    }
}

