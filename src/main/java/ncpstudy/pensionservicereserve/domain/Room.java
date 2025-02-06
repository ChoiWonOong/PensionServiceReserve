package ncpstudy.pensionservicereserve.domain;

public class Room {
    private int id;
    private String roomName;
    private int stdNumPeople = 2;
    private int maxNumPeople = 4;

    public Room() {}

    public Room(String roomName, int stdNumPeople, int maxNumPeople) {
        this.roomName = roomName;
        this.stdNumPeople = stdNumPeople;
        this.maxNumPeople = maxNumPeople;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setStdNumPeople(int stdNumPeople) {
        this.stdNumPeople = stdNumPeople;
    }

    public void setMaxNumPeople(int maxNumPeople) {
        this.maxNumPeople = maxNumPeople;
    }

    public int getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getStdNumPeople() {
        return stdNumPeople;
    }

    public int getMaxNumPeople() {
        return maxNumPeople;
    }
}
