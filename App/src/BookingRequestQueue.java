import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class BookingRequestQueue {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Double"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        System.out.println("Booking Request Queue");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            System.out.println("Processing booking for Guest: " + r.guestName + ", Room Type: " + r.roomType);
        }
    }
}