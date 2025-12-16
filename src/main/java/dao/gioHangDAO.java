package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.gioHang;
import model.ketNoi;


public class gioHangDAO {
	public gioHang getGioHangTheoMaKH(long maKH) throws Exception {
	    gioHang gh = null;

	    try {
	        ketNoi kn = new ketNoi();
	        kn.ketnoi();

	        String sql = "SELECT * FROM GioHang WHERE MaKH = ?";
	        PreparedStatement ps = kn.cn.prepareStatement(sql);
	        ps.setLong(1, maKH);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            gh = new gioHang(
	                rs.getLong("MaGioHang"),
	                rs.getLong("MaKH"),
	                rs.getTimestamp("NgayTao")
	            );
	        }

	        rs.close();
	        ps.close();
	        kn.cn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return gh;
	}

	public gioHang taoGioHang(long maKH) throws Exception {
	    gioHang gh = null;

	    try {
	        ketNoi kn = new ketNoi();
	        kn.ketnoi();

	        String sql = "INSERT INTO GioHang(MaKH) VALUES (?)";
	        PreparedStatement ps = kn.cn.prepareStatement(
	            sql,
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );
	        ps.setLong(1, maKH);
	        ps.executeUpdate();

	        ResultSet rsKey = ps.getGeneratedKeys();
	        if (rsKey.next()) {
	            long maGioHang = rsKey.getLong(1);
	            gh = new gioHang(
	                maGioHang,
	                maKH,
	                new java.util.Date()
	            );
	        }

	        rsKey.close();
	        ps.close();
	        kn.cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return gh;
	}
	public gioHang taoGioHangValid(long maKH) throws Exception {
	    gioHang gh = getGioHangTheoMaKH(maKH);
	    if (gh == null) {
	        gh = taoGioHang(maKH);
	    }
	    return gh;
	}

}
