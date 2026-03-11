import java.util.*;

// 1. CUSTOM EXCEPTION: Explicitly handles booking-specific failures
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class UseCase9ErrorHandlingValidation {
    // Inventory state (Mocking UC6)
    private static Map<String, Integer> inventory = new HashMap<>();

    static {
        inventory.put("Deluxe", 1); // Only 1 room available
        inventory.put("Suite", 5);
    }

    // 2. FAIL-FAST VALIDATION: Logic to guard system state
    public static void validateBooking(String roomType) throws InvalidBookingException {
        // Validate Room Type exists
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Error: Invalid Room Type provided [" + roomType + "].");
        }

        // Prevent negative or zero inventory
        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("Error: No rooms available for [" + roomType + "].");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- UC9: Error Handling & Validation ---");

        // Test Cases: 1 Valid, 1 Out of Stock, 1 Invalid Type
        String[] requests = {"Deluxe", "Deluxe", "Penthouse"};

        for (String type : requests) {
            try {
                System.out.println("\nValidating request for: " + type);

                // Perform validation before any processing
                validateBooking(type);

                // If code reaches here, validation passed
                inventory.put(type, inventory.get(type) - 1);
                System.out.println("Status: SUCCESS. Room allocated.");

            } catch (InvalidBookingException e) {
                // 3. GRACEFUL FAILURE: Error communicated without crashing
                System.out.println("Status: FAILED. Reason: " + e.getMessage());
            } finally {
                System.out.println("Current " + type + " Inventory: " + inventory.getOrDefault(type, 0));
            }
        }

        System.out.println("\nSystem remains stable and continues to run.");
    }
}