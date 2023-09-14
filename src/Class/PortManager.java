package Class;

import Enum.UserRole;
public class PortManager extends User{
    private Port port;

    public PortManager(int id, String username, String password, UserRole role) {
        super(id, username, password, role);
    }
}
