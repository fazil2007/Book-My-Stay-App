import java.util.*;

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> counter = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        counter.put("Single", 0);
        counter.put("Double", 0);
        counter.put("Suite", 0);
    }

    public synchronized String allocateRoom(String guest, String type) {
        if (inventory.get(type) > 0) {
            int count = counter.get(type) + 1;
            counter.put(type, count);

            inventory.put(type, inventory.get(type) - 1);

            String roomId = type + "-" + count;
            System.out.println("Booking confirmed for Guest: " + guest + ", Room ID: " + roomId);
            return roomId;
        }
        return null;
    }

    public void printInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}

class BookingTask implements Runnable {
    private RoomInventory inventory;
    private String guest;
    private String type;

    public BookingTask(RoomInventory inventory, String guest, String type) {
        this.inventory = inventory;
        this.guest = guest;
        this.type = type;
    }

    public void run() {
        inventory.allocateRoom(guest, type);
    }
}

public class BookMyStayApp{

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        System.out.println("Concurrent Booking Simulation");

        Thread t1 = new Thread(new BookingTask(inventory, "Abhi", "Single"));
        Thread t2 = new Thread(new BookingTask(inventory, "Vanmathi", "Double"));
        Thread t3 = new Thread(new BookingTask(inventory, "Kural", "Suite"));
        Thread t4 = new Thread(new BookingTask(inventory, "Subha", "Single"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {}

        inventory.printInventory();
    }
}