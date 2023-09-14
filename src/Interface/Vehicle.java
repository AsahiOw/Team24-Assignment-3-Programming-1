package Interface;
import Class.Container;
import Class.Port;

public interface Vehicle {
    // Get vehicle info
    public String getName();
    public double getFuelLevel();
    public int getCapacity();

    // Fuel vehicle
    public void refuel(double fuel);

    // Load / unload containers
    public void loadContainer(Container c);
    public void unloadContainer(Container c);

    // Get container info
    public int getNumContainers();
    public double getTotalContainerWeight();

    // Move vehicle
    public void moveToPort(Port port);

    // Get current port
    public Port getCurrentPort();

    // Set current port
    public void setCurrentPort(Port port);

    // Check if vehicle can move to port
    public boolean canMoveToPort(Port port);
}
