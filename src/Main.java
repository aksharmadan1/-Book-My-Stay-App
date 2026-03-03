import java.util.*;

// --- UC2: Room Domain Model ---
class Room {
    private int roomNumber;
    private String type;
    private boolean isAvailable;

    public Room(int roomNumber, String type, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() { return isAvailable; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + type + "] - " + (isAvailable ? "Available" : "Occupied");
    }
}

// --- UC5: Reservation Request Model ---
class Reservation {
    private String guestName;
    private String roomTypeRequested;

    public Reservation(String guestName, String roomTypeRequested) {
        this.guestName = guestName;
        this.roomTypeRequested = roomTypeRequested;
    }

    @Override
    public String toString() {
        return "[Guest: " + guestName + " | Requested: " + roomTypeRequested + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        // --- UC1: Welcome Message ---
        System.out.println("==========================================");
        System.out.println("   WELCOME TO BOOK-MY-STAY HOTEL SYSTEM   ");
        System.out.println("==========================================\n");

        // --- UC3: Centralized Room Inventory Management ---
        List<Room> inventory = new ArrayList<>();
        inventory.add(new Room(101, "Single", true));
        inventory.add(new Room(102, "Double", true));
        inventory.add(new Room(201, "Suite", false)); // Occupied
        inventory.add(new Room(301, "Suite", true));

        // --- UC4: Room Search & Availability Check (Read-Only) ---
        System.out.println("GUEST SEARCH: Looking for available rooms...");
        List<Room> availableRooms = new ArrayList<>();
        for (Room r : inventory) {
            if (r.isAvailable()) {
                availableRooms.add(r);
            }
        }

        for (Room r : availableRooms) {
            System.out.println("-> " + r);
        }

        // --- UC5: Booking Request Intake (FIFO Queue) ---
        System.out.println("\nSYSTEM: Receiving Booking Requests (Peak Demand)...");
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Guests arriving in order
        bookingQueue.add(new Reservation("Alice", "Suite"));
        bookingQueue.add(new Reservation("Bob", "Single"));
        bookingQueue.add(new Reservation("Charlie", "Double"));

        System.out.println("STATUS: Booking Queue established (Arrival Order preserved).");
        System.out.println("TOTAL REQUESTS IN LINE: " + bookingQueue.size());

        // Displaying the Queue
        for (Reservation res : bookingQueue) {
            System.out.println("   Waiting: " + res);
        }

        System.out.println("\n==========================================");
        System.out.println("   UC1-UC5 IMPLEMENTATION COMPLETE        ");
        System.out.println("==========================================");
    }
}