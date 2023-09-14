package Class;

import Enum.UserRole;
public class PortManager extends User{
    private Port port;
    public PortManager(String username, String password, UserRole role) {
        super(username, password, role);
    }
}
