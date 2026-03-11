import java.util.*;

// Class to represent an individual Add-On Service
class Service {
    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        // Map to store: ReservationID -> List of selected Services (One-to-Many)
        Map<String, List<Service>> addonManager = new HashMap<>();

        // 1. Existing Reservation ID from UC6
        String reservationId = "DELUXE-101";

        // 2. Guest selects multiple services
        List<Service> selectedServices = new ArrayList<>();
        selectedServices.add(new Service("Breakfast Buffet", 25.0));
        selectedServices.add(new Service("Late Check-out", 15.0));
        selectedServices.add(new Service("Airport Shuttle", 40.0));

        // 3. Mapping the services to the Reservation ID
        addonManager.put(reservationId, selectedServices);

        // 4. Cost Aggregation (Calculating total extra cost)
        double totalExtraCost = 0;
        System.out.println("--- UC7: Add-On Service Selection ---");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Services:");

        for (Service s : addonManager.get(reservationId)) {
            System.out.println("- " + s);
            totalExtraCost += s.price;
        }

        System.out.println("------------------------------------");
        System.out.println("Total Additional Cost: $" + totalExtraCost);
        System.out.println("Core Inventory Status: Unchanged (Safe)");
    }
}