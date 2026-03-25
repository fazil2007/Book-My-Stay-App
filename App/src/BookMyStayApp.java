import java.util.HashMap;
import java.util.Map;



public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Map<String, Room> rooms = new HashMap<>();
        rooms.put("Single", new Room("Single Room", 1, 250, 1500.0));
        rooms.put("Double", new Room("Double Room", 2, 400, 2500.0));
        rooms.put("Suite", new Room("Suite Room", 3, 750, 5000.0));

        System.out.println("Room Search\n");

        for (String key : rooms.keySet()) {
            int available = inventory.getAvailableRooms(key);

            if (available > 0) {
                Room r = rooms.get(key);

                System.out.println(r.type + ":");
                System.out.println("Beds: " + r.beds);
                System.out.println("Size: " + r.size + " sqft");
                System.out.println("Price per night: " + r.price);
                System.out.println("Available: " + available + "\n");
            }
        }
    }
}