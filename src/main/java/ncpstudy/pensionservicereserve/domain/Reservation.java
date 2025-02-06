package ncpstudy.pensionservicereserve.domain;

import java.sql.Timestamp;

public class Reservation {
    private int id;
    private int roomId;
    private Timestamp reservedDate;

    public Reservation() {
    }
    public Reservation(int roomId, Timestamp reservedDate) {
        this.roomId = roomId;
        this.reservedDate = reservedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setReservedDate(Timestamp reservedDate) {
        this.reservedDate = reservedDate;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public Timestamp getReservedDate() {
        return reservedDate;
    }
}
