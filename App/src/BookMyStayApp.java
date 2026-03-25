import java.util.*;

class AddOnService {
    String name;
    double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> servicesMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        servicesMap.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    public double getTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = servicesMap.getOrDefault(reservationId, new ArrayList<>());
        for (AddOnService s : services) {
            total += s.cost;
        }
        return total;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId, new AddOnService("Breakfast", 500.0));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000.0));

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + manager.getTotalCost(reservationId));
    }
}