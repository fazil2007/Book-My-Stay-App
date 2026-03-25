import java.util.*;

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void incrementRoom(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public int getAvailableRooms(String type) {
        return inventory.get(type);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Stack<String> rollbackStack = new Stack<>();
        rollbackStack.push("Single-1");

        System.out.println("Booking Cancellation");

        if (!rollbackStack.isEmpty()) {

            String reservationId = rollbackStack.pop();
            String roomType = reservationId.split("-")[0];

            inventory.incrementRoom(roomType);

            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
            System.out.println("\nRollback History (Most Recent First):");
            System.out.println("Released Reservation ID: " + reservationId);
            System.out.println("\nUpdated " + roomType + " Room Availability: " + inventory.getAvailableRooms(roomType));
        }
    }
}