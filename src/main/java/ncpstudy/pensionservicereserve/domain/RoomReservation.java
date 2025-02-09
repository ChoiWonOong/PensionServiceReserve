package ncpstudy.pensionservicereserve.domain;

public class RoomReservation {
    private int roomId;
    private String roomName;
    private int reservationId;
    private int stdNumPeople;
    private int maxNumPeople;
    private int reservedDate;

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setStdNumPeople(int stdNumPeople) {
        this.stdNumPeople = stdNumPeople;
    }

    public void setMaxNumPeople(int maxNumPeople) {
        this.maxNumPeople = maxNumPeople;
    }

    public void setReservedDate(int reservedDate) {
        this.reservedDate = reservedDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getStdNumPeople() {
        return stdNumPeople;
    }

    public int getMaxNumPeople() {
        return maxNumPeople;
    }

    public int getReservedDate() {
        return reservedDate;
    }

    public RoomReservation() {
    }

}
