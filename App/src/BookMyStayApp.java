import java.io.*;
import java.util.*;

class RoomInventory implements Serializable {
    Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}

public class BookMyStayApp {

    private static final String FILE_NAME = "inventory.dat";

    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = loadInventory();

        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.getInventory().keySet()) {
            System.out.println(key + ": " + inventory.getInventory().get(key));
        }

        saveInventory(inventory);
        System.out.println("Inventory saved successfully.");
    }

    public static void saveInventory(RoomInventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public static RoomInventory loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (RoomInventory) ois.readObject();
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return new RoomInventory();
        }
    }
}