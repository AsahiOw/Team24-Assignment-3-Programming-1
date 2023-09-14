package Class;
import Enum.UserRole;
public class SystemAdmin extends User{
    public SystemAdmin(String username, String password, UserRole role) {
        super(username, password, role);
    }
}
