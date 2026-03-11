import java.util.*;

public class UseCase10BookingCancellation {
    // Current Inventory
    private static Map<String, Integer> inventory = new HashMap<>();
    // Active Reservations: ReservationID -> RoomType
    private static Map<String, String> activeReservations = new HashMap<>();
    // Rollback Structure: Tracks recently released Room IDs
    private static Stack<String> releasedRoomIds = new Stack<>();

    static {
        inventory.put("Deluxe", 0); // Assume all booked
        activeReservations.put("RES101", "Deluxe");
    }

    public static void cancelBooking(String resId) {
        System.out.println("\nInitiating cancellation for: " + resId);

        // 1. Validation: Ensure reservation exists
        if (activeReservations.containsKey(resId)) {
            String roomType = activeReservations.get(resId);

            // 2. Rollback Logic: Record the "released" state
            // In a real app, this room ID would go back to the available pool
            releasedRoomIds.push("ROOM-ID-FOR-" + resId);

            // 3. Inventory Restoration: Increment count
            inventory.put(roomType, inventory.get(roomType) + 1);

            // 4. State Update: Remove from active bookings
            activeReservations.remove(resId);

            System.out.println("Status: SUCCESS. Inventory rolled back for " + roomType);
            System.out.println("Room ID added to Rollback Stack: " + releasedRoomIds.peek());
        } else {
            // 5. Reject invalid or duplicate cancellations
            System.out.println("Status: FAILED. Reservation ID not found or already cancelled.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- UC10: Booking Cancellation & Inventory Rollback ---");
        System.out.println("Initial Deluxe Inventory: " + inventory.get("Deluxe"));

        // Valid Cancellation
        cancelBooking("RES101");

        // Invalid Cancellation (Already removed)
        cancelBooking("RES101");

        // Summary of state
        System.out.println("\n------------------------------------------");
        System.out.println("Final Deluxe Inventory: " + inventory.get("Deluxe"));
        System.out.println("Total Released Rooms in Stack: " + releasedRoomIds.size());
    }
}