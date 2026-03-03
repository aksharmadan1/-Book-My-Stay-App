import java.util.HashMap;
import java.util.Map;

/**
 * UC3: Centralized Room Inventory Management
 * This class handles the state of room availability using a HashMap.
 */
class RoomInventory {
    // HashMap to store Room Type (Key) and Available Count (Value)
    private Map<String, Integer> inventory;

    /**
     * Constructor to initialize the inventory with starting values.
     */
    public RoomInventory() {
        this.inventory = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 1);
    }

    /**
     * Retrieves the current count for a specific room type.
     * Uses O(1) average time complexity for the lookup.
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates room availability in a controlled manner.
     */
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
            System.out.println("LOG: Inventory updated for " + roomType + " to " + newCount);
        } else {
            System.out.println("ERROR: Room type " + roomType + " does not exist.");
        }
    }

    /**
     * Displays the entire current state of the inventory.
     */
    public void displayInventory() {
        System.out.println("\n--- CENTRALIZED INVENTORY STATUS ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Available: " + entry.getValue());
        }
        System.out.println("------------------------------------");
    }
}

// --- KEEPING THE ROOM MODELS FROM UC2 ---
abstract class Room {
    private String type;
    public Room(String type) { this.type = type; }
    public String getType() { return type; }
}

class SingleRoom extends Room { public SingleRoom() { super("Single"); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double"); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite"); } }

// --- MAIN ENTRY POINT ---
public class Main {
    public static void main(String[] args) {
        System.out.println("BOOK-MY-STAY: UC3 Inventory System Initialized\n");

        // Initialize Centralized Inventory
        RoomInventory inventoryManager = new RoomInventory();

        // Display initial state
        inventoryManager.displayInventory();

        // Simulate a Search (Read-only lookup)
        String searchType = "Double";
        System.out.println("Search Request: Checking availability for " + searchType + "...");
        System.out.println("Result: " + inventoryManager.getAvailability(searchType) + " rooms found.");

        // Simulate an Update (e.g., after a manual adjustment or booking)
        inventoryManager.updateAvailability("Suite", 0);
        
        // Final display of system state
        inventoryManager.displayInventory();
    }
}
