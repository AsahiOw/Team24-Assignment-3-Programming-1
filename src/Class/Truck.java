package Class;

public class Truck {
    private String name;
    private double fuel;
    private int capacity;

    //    constructor, getter, setter

    public Truck(String name, double fuel, int capacity) {
        this.name = name;
        this.fuel = fuel;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
