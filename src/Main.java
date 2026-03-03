public class Main {
    public static void main(String[] args) {
        // Setup
        RoomInventory inventory = new RoomInventory();
        inventory.initializeRooms();

        System.out.println("--- UC4: Room Search & Availability Check ---");

        // Guest initiates search
        System.out.println("Guest Request: Show me available rooms...");

        var availableRooms = inventory.getAvailableRooms();

        if (availableRooms.isEmpty()) {
            System.out.println("Validation: No rooms currently available.");
        } else {
            System.out.println("Search Results (Filtered):");
            for (Room r : availableRooms) {
                System.out.println("-> " + r);
            }
        }

        System.out.println("System State: Unchanged (Read-only operation complete).");
    }
}