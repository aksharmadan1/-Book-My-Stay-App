import java.util.HashMap;
import java.util.Map;

/**
 * BOOK-MY-STAY HOTEL MANAGEMENT SYSTEM
 * Integrated Use Cases: UC1, UC2, UC3, and UC4
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

// --- UC3: CENTRALIZED INVENTORY ---
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 0); // UC4 Test: Suite is currently unavailable
    }

    public int getCount(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Map<String, Integer> getAllInventory() {
        return new HashMap<>(inventory); // Defensive copy: Read-only access
    }
}

// --- UC4: SEARCH SERVICE ---
class SearchService {
    /**
     * UC4 Goal: Filter and display only available rooms.
     * Ensures inventory is not modified during the search.
     */
    public void performSearch(RoomInventory inventory, Room[] roomTypes) {
        System.out.println("\n--- GUEST SEARCH RESULTS (Available Only) ---");
        boolean found = false;

        for (Room room : roomTypes) {
            int availableCount = inventory.getCount(room.getType());
            
            // Validation Logic: Display only if availability > 0
            if (availableCount > 0) {
                System.out.println("-> " + room.getType() + " Room | Price: ₹" + room.getPrice() + 
                                   " | Status: " + availableCount + " Available");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Validation: No rooms currently meet your criteria.");
        }
    }
}

// --- UC1: MAIN ENTRY POINT ---
public class Main {
    public static void main(String[] args) {
        // UC1: Welcome Message
        System.out.println("==============================================");
        System.out.println("   WELCOME TO BOOK-MY-STAY v1.0");
        System.out.println("==============================================");

        // Initialize UC2 & UC3 components
        Room[] types = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };
        RoomInventory inventoryManager = new RoomInventory();
        
        // UC4: Search Service Execution
        SearchService searchService = new SearchService();
        searchService.performSearch(inventoryManager, types);

        System.out.println("\nSystem State: Search Complete. No Inventory Modified.");
    }
}
