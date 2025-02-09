package ncpstudy.pensionservicereserve.domain;

public class Room {
    private int id;
    private String roomName;
    private int stdNumPeople = 2;
    private int maxNumPeople = 4;
    private String link;

    public Room() {}

    public Room(String roomName, int stdNumPeople, int maxNumPeoplem, String link) {
        this.roomName = roomName;
        this.stdNumPeople = stdNumPeople;
        this.maxNumPeople = maxNumPeople;
        this.link = link;
    }

    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
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
