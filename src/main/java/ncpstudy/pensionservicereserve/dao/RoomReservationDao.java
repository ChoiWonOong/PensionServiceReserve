package ncpstudy.pensionservicereserve.dao;

import ncpstudy.pensionservicereserve.db.connect.MysqlConnect;
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

public class RoomReservationDao {
    MysqlConnect db = new MysqlConnect();

    public RoomReservationDao() throws FileNotFoundException {
    }
    public RoomReservation getRoomReservation(int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from reservation where room.id = reservation.room_id and reservation.id = ?";
        conn = db.getConnection();
        RoomReservation roomReservation = new RoomReservation();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            while(rs.next()){
                roomReservation.setRoomId(rs.getInt("room.id"));
                roomReservation.setRoomName(rs.getString("room_name"));
                roomReservation.setReservationId(rs.getInt("reservation.id"));
                roomReservation.setStdNumPeople(rs.getInt("std_num_people"));
                roomReservation.setMaxNumPeople(rs.getInt("max_num_people"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return roomReservation;
    }
    public List<RoomReservation> listDayReservation(Date date){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from reservation, room where reservation.room_id = room.id and reserved_date = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        conn = db.getConnection();
        List<RoomReservation> list = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sdf.format(date)+"%");
            rs = pstmt.executeQuery();
            while(rs.next()){
                RoomReservation roomReservation = new RoomReservation();
                roomReservation.setRoomId(rs.getInt("room.id"));
                roomReservation.setRoomName(rs.getString("room_name"));
                roomReservation.setReservationId(rs.getInt("reservation.id"));
                roomReservation.setStdNumPeople(rs.getInt("std_num_people"));
                roomReservation.setMaxNumPeople(rs.getInt("max_num_people"));

                list.add(roomReservation);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return list;
    }
}
