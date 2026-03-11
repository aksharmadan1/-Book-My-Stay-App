import java.util.*;

public class UseCase6ReservationApp {
    // Map to track allocated room IDs grouped by Room Type
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Inventory: Room Type -> Count
    private static Map<String, Integer> inventory = new HashMap<>();

    public static void main(String[] args) {
        // Initialize Inventory
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);

        // Queue for incoming booking requests (FIFO)
        Queue<String> bookingRequests = new LinkedList<>();
        bookingRequests.add("Deluxe");
        bookingRequests.add("Deluxe");
        bookingRequests.add("Suite");
        bookingRequests.add("Suite"); // This one should fail (No inventory)

        System.out.println("--- UC6: Room Allocation System ---");

        while (!bookingRequests.isEmpty()) {
            String requestedType = bookingRequests.poll();
            System.out.println("\nProcessing request for: " + requestedType);

            // 1. Check Availability
            if (inventory.getOrDefault(requestedType, 0) > 0) {

                // 2. Generate Unique Room ID
                String roomId = requestedType.toUpperCase() + "-" + (100 + new Random().nextInt(900));

                // 3. Prevent Double Booking using Set uniqueness
                allocatedRooms.putIfAbsent(requestedType, new HashSet<>());

                if (!allocatedRooms.get(requestedType).contains(roomId)) {
                    allocatedRooms.get(requestedType).add(roomId);

                    // 4. Atomic-like Update: Decrement Inventory immediately
                    inventory.put(requestedType, inventory.get(requestedType) - 1);

                    System.out.println("Status: CONFIRMED");
                    System.out.println("Assigned Room ID: " + roomId);
                }
            } else {
                System.out.println("Status: REJECTED - No " + requestedType + " rooms available.");
            }
        }
    }
}