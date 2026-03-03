import java.util.ArrayList;
import java.util.List;

public class RoomInventory {
    private List<Room> rooms = new ArrayList<>();

    public void initializeRooms() {
        rooms.add(new Room(101, "Single", true));
        rooms.add(new Room(102, "Double", true));
        rooms.add(new Room(201, "Suite", false));
    }

    public void displayInventory() {
        for (Room r : rooms) {
            System.out.println(r);
        }
    }
}