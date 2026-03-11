import java.util.*;

// Class to represent a confirmed Reservation
class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    double totalCost;

    Reservation(String id, String name, String type, double cost) {
        this.reservationId = id;
        this.guestName = name;
        this.roomType = type;
        this.totalCost = cost;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Guest: %s | Room: %s | Paid: $%.2f",
                reservationId, guestName, roomType, totalCost);
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        // Key Concept: List preserves insertion order for chronological tracking
        List<Reservation> bookingHistory = new ArrayList<>();

        // 1. Simulating successful confirmations being added to history
        bookingHistory.add(new Reservation("RES101", "Alice", "Deluxe", 150.00));
        bookingHistory.add(new Reservation("RES102", "Bob", "Suite", 300.00));
        bookingHistory.add(new Reservation("RES103", "Charlie", "Deluxe", 150.00));

        // 2. Admin Request: Generate Operational Report
        System.out.println("--- UC8: Booking History & Audit Trail ---");
        System.out.println("Generating Report for Admin...");
        System.out.println("------------------------------------------");

        double totalRevenue = 0;
        int deluxeCount = 0;
        int suiteCount = 0;

        for (Reservation res : bookingHistory) {
            System.out.println(res);
            totalRevenue += res.totalCost;

            if (res.roomType.equalsIgnoreCase("Deluxe")) deluxeCount++;
            else if (res.roomType.equalsIgnoreCase("Suite")) suiteCount++;
        }

        // 3. Summary Reporting
        System.out.println("------------------------------------------");
        System.out.println("SUMMARY REPORT");
        System.out.println("Total Reservations: " + bookingHistory.size());
        System.out.println("Deluxe Rooms Booked: " + deluxeCount);
        System.out.println("Suites Booked: " + suiteCount);
        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("------------------------------------------");
    }
}