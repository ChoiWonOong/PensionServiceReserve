package ncpstudy.pensionservicereserve.dao;

import ncpstudy.pensionservicereserve.db.connect.MysqlConnect;
import ncpstudy.pensionservicereserve.domain.Room;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    MysqlConnect db = new MysqlConnect();

    public RoomDao() throws FileNotFoundException {
    }
    public Room findRoom(int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from room where id=?";
        conn = db.getConnection();
        Room room = new Room();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                room.setId(rs.getInt("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setStdNumPeople(rs.getInt("std_num_people"));
                room.setMaxNumPeople(rs.getInt("max_num_people"));
                room.setLink(rs.getString("link"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return room;
    }
    public List<Room> listRoom(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from room order by room_name";
        conn = db.getConnection();

        List<Room> list = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setStdNumPeople(rs.getInt("std_num_people"));
                room.setMaxNumPeople(rs.getInt("max_num_people"));
                room.setLink(rs.getString("link"));
                list.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return list;
    }
}
