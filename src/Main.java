import java.util.*;

public class UseCase11ConcurrentBookingSimulation {
    // Shared Mutable State
    private static int deluxeInventory = 5;
    private static final Object lock = new Object(); // Synchronization lock

    public static void main(String[] args) {
        System.out.println("--- UC11: Concurrent Booking Simulation ---");
        System.out.println("Initial Inventory: " + deluxeInventory);
        System.out.println("Simulating 10 concurrent requests for 5 rooms...\n");

        // Creating multiple threads to simulate concurrent guests
        Thread[] guests = new Thread[10];

        for (int i = 0; i < guests.length; i++) {
            final int guestId = i + 1;
            guests[i] = new Thread(() -> {
                processBooking(guestId);
            });
            guests[i].start();
        }

        // Wait for all threads to finish
        for (Thread t : guests) {
            try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println("\nFinal Deluxe Inventory: " + deluxeInventory);
        System.out.println("Simulation Complete. System state is consistent.");
    }

    // Key Concept: Synchronized Access (Thread Safety)
    // Only one thread can enter this block at a time
    private static void processBooking(int guestId) {
        synchronized (lock) {
            System.out.print("Guest-" + guestId + " is attempting to book... ");

            if (deluxeInventory > 0) {
                // Simulate processing delay to emphasize race condition risk
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                deluxeInventory--;
                System.out.println("SUCCESS! Room allocated.");
            } else {
                System.out.println("FAILED. No rooms left.");
            }
        }
    }
}