import java.io.*;
import java.util.*;

// Class must implement Serializable to be saved to a file
class BookingState implements Serializable {
    private static final long serialVersionUID = 1L;
    Map<String, Integer> inventory;
    List<String> history;

    BookingState(Map<String, Integer> inventory, List<String> history) {
        this.inventory = inventory;
        this.history = history;
    }
}

public class UseCase12DataPersistenceRecovery {
    private static final String FILE_NAME = "system_state.ser";

    public static void main(String[] args) {
        System.out.println("--- UC12: Data Persistence & System Recovery ---");

        // 1. Initial State
        Map<String, Integer> currentInventory = new HashMap<>();
        currentInventory.put("Deluxe", 10);
        List<String> currentHistory = new ArrayList<>();
        currentHistory.add("Initial System Startup - " + new Date());

        // 2. SERIALIZATION: Saving the state
        saveState(new BookingState(currentInventory, currentHistory));

        // 3. DESERIALIZATION: Recovering the state
        BookingState recoveredState = loadState();

        if (recoveredState != null) {
            System.out.println("\n--- System Recovery Successful ---");
            System.out.println("Recovered Inventory: " + recoveredState.inventory);
            System.out.println("Recovered History: " + recoveredState.history);
        } else {
            System.out.println("\n--- Starting with Default State (No persistence found) ---");
        }
    }

    // Key Concept: Writing objects to a durable medium (File)
    private static void saveState(BookingState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state serialized and saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error during persistence: " + e.getMessage());
        }
    }

    // Key Concept: Reconstructing objects from persisted data
    private static BookingState loadState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (BookingState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Recovery failed (Corrupted file): " + e.getMessage());
            return null;
        }
    }
}