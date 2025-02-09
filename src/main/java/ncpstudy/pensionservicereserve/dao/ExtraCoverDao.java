package ncpstudy.pensionservicereserve.dao;

import ncpstudy.pensionservicereserve.db.connect.MysqlConnect;
import ncpstudy.pensionservicereserve.domain.ExtraCover;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtraCoverDao {
    MysqlConnect db = new MysqlConnect();

    public ExtraCoverDao() throws FileNotFoundException {
    }
    public void insertExtCover(ExtraCover extCover){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into ext_cover values (null, ?, ?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, extCover.getReservationId());
            pstmt.setInt(2, extCover.getNum());
            pstmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(pstmt, conn);
        }
    }
    public boolean isApplied(int reservationId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "select count(*) as num from ext_cover group by id where id=?";
        ResultSet rs = null;
        int num=-1;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,reservationId);
            rs = pstmt.executeQuery();
            num = rs.getInt("num");
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return (num==0)?true:false;
    }
    public ExtraCover getExtCover(int reservationId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "select * from ext_cover where reservation_id = ?";
        ResultSet rs = null;
        ExtraCover extraCover = new ExtraCover();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,reservationId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                extraCover.setId(rs.getInt("id"));
                extraCover.setReservationId(rs.getInt("reservation_id"));
                extraCover.setNum(rs.getInt("num"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            db.dbClose(rs, pstmt, conn);
        }
        return extraCover;
    }
}
