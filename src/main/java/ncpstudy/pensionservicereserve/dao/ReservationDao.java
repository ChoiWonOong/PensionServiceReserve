package ncpstudy.pensionservicereserve.dao;

import ncpstudy.pensionservicereserve.db.connect.MysqlConnect;
import ncpstudy.pensionservicereserve.domain.Reservation;
import ncpstudy.pensionservicereserve.domain.RoomReservation;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDao {
    MysqlConnect db = new MysqlConnect();

    public ReservationDao() throws FileNotFoundException {
    }

    public void insertReservation(Reservation reservation){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into reservation values (null, ?, ?)";
        conn = db.getConnection();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,reservation.getRoomId());
            pstmt.setTimestamp(2, reservation.getReservedDate());

            pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(pstmt, conn);
        }
    }
}
