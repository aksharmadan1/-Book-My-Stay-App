/**
 * BOOK-MY-STAY HOTEL MANAGEMENT SYSTEM
 * * UC1: Application Entry & Welcome Message
 * UC2: Basic Room Types & Static Availability
 * * This combined code demonstrates:
 * 1. JVM Entry Point (main method)
 * 2. Object Modeling (Abstract Classes & Inheritance)
 * 3. Encapsulation (Private fields with Getters)
 * 4. Polymorphism (Calling subclass methods via Superclass reference)
 * * @author aksharmadan1
 * @version 1.0
 */

// --- UC2: DOMAIN MODELING (ABSTRACT CLASS) ---

/**
 * Represents a generalized Room concept.
 * Cannot be instantiated directly due to 'abstract' keyword.
 */
abstract class Room {
    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    // Encapsulation: Using getters to access private data
    public String getType() { return type; }
    public double getPrice() { return price; }

    /**
     * Abstract method to be implemented by specific room types.
     * Ensures every room has a way to show its unique features.
     */
    public abstract void displayFeatures();
}

// --- UC2: INHERITANCE (CONCRETE CLASSES) ---

class SingleRoom extends Room {
    public SingleRoom() { super("Single", 1000.0); }
    
    @Override
    public void displayFeatures() {
        System.out.println("   Features: [1 Bed, High-speed Wifi, AC]");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double", 1800.0); }
    
    @Override
    public void displayFeatures() {
        System.out.println("   Features: [2 Beds, High-speed Wifi, AC, Smart TV]");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite", 3500.0); }
    
    @Override
    public void displayFeatures() {
        System.out.println("   Features: [King Bed, Mini Bar, Private Balcony, Luxury Tub]");
    }
}

// --- UC1: APPLICATION ENTRY POINT ---

public class Main {

    /**
     * Entry point for the JVM.
     */
    public static void main(String[] args) {
        // UC1: Welcome Message & Versioning
        System.out.println("==============================================");
        System.out.println("   WELCOME TO BOOK-MY-STAY HOTEL SYSTEM       ");
        System.out.println("   System Version: v1.0                       ");
        System.out.println("==============================================\n");

        // UC2: Static Availability (Simple Variables)
        // This intentionally shows the limitation before we use Data Structures in UC3
        int availableSingles = 5;
        int availableDoubles = 3;
        int availableSuites = 1;

        System.out.println("--- CURRENT ROOM CATEGORIES & STATUS ---");

        // UC2: Polymorphism in Action
        // We use the 'Room' superclass type to hold 'SingleRoom' objects, etc.
        Room s1 = new SingleRoom();
        Room d1 = new DoubleRoom();
        Room st1 = new SuiteRoom();

        // Displaying information using a helper method
        renderRoomUI(s1, availableSingles);
        renderRoomUI(d1, availableDoubles);
        renderRoomUI(st1, availableSuites);

        System.out.println("\nSystem: UC1 & UC2 logic executed successfully.");
        System.out.println("Status: Terminating session...");
    }

    /**
     * Helper method to print room details uniformly.
     */
    public static void renderRoomUI(Room room, int count) {
        System.out.println("Category: " + room.getType());
        System.out.println("Price   : ₹" + room.getPrice());
        room.displayFeatures();
        System.out.println("Status  : " + (count > 0 ? count + " Rooms Available" : "Sold Out"));
        System.out.println("----------------------------------------------");
    }
}
