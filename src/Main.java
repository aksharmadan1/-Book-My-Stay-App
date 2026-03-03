\import java.util.*;

/**
 * BOOK-MY-STAY HOTEL MANAGEMENT SYSTEM
 * Integrated Use Cases: UC1, UC2, UC3, UC4, and UC5
 * * @author aksharmadan1
 * @version 1.0
 */

// --- UC2: DOMAIN MODEL ---
abstract class Room {
    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() { return type; }
    public double getPrice() { return price; }
}

class SingleRoom extends Room { public SingleRoom() { super("Single", 1000.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double", 1800.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite", 3500.0); } }

// --- UC5: RESERVATION MODEL ---
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Room: " + roomType;
    }
}

// --- UC3: CENTRALIZED INVENTORY ---
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 1);
    }

    public int getCount(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// --- UC5: BOOKING REQUEST QUEUE (FIFO) ---
class BookingService {
    // Decoupling request intake from allocation using a Queue
    private Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(String guestName, String roomType) {
        Reservation res = new Reservation(guestName, roomType);
        requestQueue.add(res);
        System.out.println("LOG: Request added to queue for " + guestName);
    }

    public void processQueue() {
        System.out.println("\n--- PROCESSING BOOKING QUEUE (FIFO ORDER) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("No requests in queue.");
            return;
        }

        while (!requestQueue.isEmpty()) {
            // poll() retrieves and removes the head of the queue
            Reservation current = requestQueue.poll();
            System.out.println("Processing -> " + current);
        }
        System.out.println("Status: All queued requests sent for allocation processing.");
    }
}

// --- UC1: MAIN ENTRY POINT ---
public class Main {
    public static void main(String[] args) {
        // UC1: Welcome Message
        System.out.println("==============================================");
        System.out.println("   WELCOME TO BOOK-MY-STAY v1.0");
        System.out.println("==============================================\n");

        // UC3: Inventory Setup
        RoomInventory inventory = new RoomInventory();

        // UC5: Booking Request Intake
        BookingService bookingService = new BookingService();

        System.out.println("System: Accepting simultaneous requests during peak demand...");
        
        // Simulating Arrival Order: Alice -> Bob -> Charlie
        bookingService.addRequest("Alice", "Suite");
        bookingService.addRequest("Bob", "Single");
        bookingService.addRequest("Charlie", "Double");

        // Display and process order (FIFO)
        bookingService.processQueue();

        System.out.println("\nSystem State: Queue processed. Inventory remains unchanged (Allocation Phase pending).");
    }
}
