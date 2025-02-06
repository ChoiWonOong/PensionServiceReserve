package ncpstudy.pensionservicereserve.domain;

public class ExtraCover {
    private int id;
    private int reservationId;
    private int num;

    public ExtraCover() {
    }

    public ExtraCover(int reservationId, int num) {
        this.reservationId = reservationId;
        this.num = num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getNum() {
        return num;
    }
}
