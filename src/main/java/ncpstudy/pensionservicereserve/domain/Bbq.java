package ncpstudy.pensionservicereserve.domain;

import java.sql.Timestamp;

public class Bbq {
    private int id;
    private int reservationId;
    private Timestamp reservedTime;

    public Bbq() {
    }

    public Bbq(int reservationId, Timestamp reservedTime) {
        this.reservationId = reservationId;
        this.reservedTime = reservedTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setReservedTime(Timestamp reservedTime) {
        this.reservedTime = reservedTime;
    }

    public int getId() {
        return id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Timestamp getReservedTime() {
        return reservedTime;
    }
}
