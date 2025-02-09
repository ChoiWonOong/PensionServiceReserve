package ncpstudy.pensionservicereserve.dao;

import ncpstudy.pensionservicereserve.db.connect.MysqlConnect;
import ncpstudy.pensionservicereserve.domain.Bbq;

import java.io.FileNotFoundException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BbqDao {
    MysqlConnect db = new MysqlConnect();

    public BbqDao() throws FileNotFoundException {
    }
    public Bbq findBbq(int reservationId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from bbq where reservation_id=?";
        conn = db.getConnection();
        Bbq bbq = new Bbq();
        bbq.setReservationId(reservationId);
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
            rs = pstmt.executeQuery();

            bbq.setId(rs.getInt("id"));
            bbq.setReservedTime(rs.getTimestamp("reserved_time"));
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(rs,pstmt, conn);
        }
        return bbq;
    }
    public void insertBbq(Bbq bbq){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into bbq values (null, ?, ?)";
        conn = db.getConnection();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bbq.getReservationId());
            pstmt.setTimestamp(2,bbq.getReservedTime());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(pstmt, conn);
        }
    }
    public void updateBbq(Bbq bbq){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update bbq set reserved_time = ? where id = ?";
        conn = db.getConnection();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1,bbq.getReservedTime());
            pstmt.setInt(2, bbq.getId());
            pstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.dbClose(pstmt, conn);
        }
    }
    public void deleteBbq(Bbq bbq){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from bbq where id = ?";
        conn = db.getConnection();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bbq.getId());
            pstmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(pstmt, conn);
        }
    }
    public List<Bbq> listTodayBbq(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(date);
        String sql = "select * from bbq where reserved_time like ? ";
        conn = db.getConnection();
        List<Bbq> list = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            System.out.println(strDate);
            pstmt.setString(1, strDate + "%");
            rs = pstmt.executeQuery();
            if(rs.next()){
                Bbq bbq = new Bbq();
                bbq.setId(rs.getInt("id"));
                bbq.setReservationId(rs.getInt("reservation_id"));
                bbq.setReservedTime(rs.getTimestamp("reserved_time"));

                list.add(bbq);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(pstmt, conn);
        }
        return list;
    }
}
