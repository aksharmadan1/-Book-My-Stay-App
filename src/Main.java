public class Room {
    private int roomNumber;
    private String type;
    private boolean isAvailable;

    public Room(int roomNumber, String type, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = isAvailable;
    }
    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + type + "] - " + (isAvailable ? "Available" : "Occupied");
    }
}