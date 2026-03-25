import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public int getAvailableRooms(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrementRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

public class BookMyStayApp{

    public static void main(String[] args) {

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Abhi", "Single"));
        queue.add(new Reservation("Subha", "Single"));
        queue.add(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        Map<String, Integer> roomCounter = new HashMap<>();
        roomCounter.put("Single", 0);
        roomCounter.put("Double", 0);
        roomCounter.put("Suite", 0);

        Set<String> allocatedRooms = new HashSet<>();

        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            if (inventory.getAvailableRooms(r.roomType) > 0) {

                int count = roomCounter.get(r.roomType) + 1;
                roomCounter.put(r.roomType, count);

                String roomId = r.roomType + "-" + count;

                if (!allocatedRooms.contains(roomId)) {
                    allocatedRooms.add(roomId);
                    inventory.decrementRoom(r.roomType);

                    System.out.println("Booking confirmed for Guest: " + r.guestName + ", Room ID: " + roomId);
                }
            }
        }
    }
}