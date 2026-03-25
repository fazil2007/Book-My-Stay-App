import java.util.*;

class InvalidRoomTypeException extends Exception {
    public InvalidRoomTypeException(String message) {
        super(message);
    }
}

public class BookMyStayApp {

    public static void validateRoomType(String type) throws InvalidRoomTypeException {
        if (!type.equals("Single") && !type.equals("Double") && !type.equals("Suite")) {
            throw new InvalidRoomTypeException("Invalid room type selected.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Booking Validation");

        System.out.print("Enter guest name: ");
        String name = sc.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = sc.nextLine();

        try {
            validateRoomType(roomType);
            System.out.println("Booking successful for " + name);
        } catch (InvalidRoomTypeException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}