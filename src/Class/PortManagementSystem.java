//package Class;
//
//import Interface.Vehicle;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//public class PortManagementSystem {
//
//    //    method write
//    public void writeTrucksToFile(List<Truck> trucks) {
//
//        File file = new File("../Data/Trucks.txt");
//
//        try {
//            FileWriter writer = new FileWriter(file);
//
//            for (Truck truck : trucks) {
//
//                writer.write(truck.getId() + ",");
//
//                writer.write(truck.getName() + ",");
//
//                writer.write(truck.getFuel() + ",");
//
//                writer.write(truck.getMaxFuel() + ",");
//
//                writer.write(truck.getCapacity() + ",");
//
//                writer.write(truck.getMaxLoad() + ",");
//
//                for (Container c : truck.getContainers()) {
//                    writer.write(c.getId() + ";");
//                }
//                writer.write(",");
//
//                writer.write(truck.getType() + ",");
//
//                writer.write(truck.getDateAdded() + "\n");
//            }
//
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}