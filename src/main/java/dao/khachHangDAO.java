package dao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ketNoi;
import model.khachHang;

public class khachHangDAO {
	public khachHang kiemTraDangNhap(String tendn,String matKhau)throws Exception {
		khachHang kh=null;
		try {
			ketNoi kn=new ketNoi();
			kn.ketnoi();
			String sql="select * from KhachHang where tendn=? and pass=?";
			PreparedStatement ps=kn.cn.prepareStatement(sql);
			
			ps.setString(1, tendn);
			ps.setString(2, matKhau);
			
			ResultSet rs=ps.executeQuery();
			
			if (rs.next()) {
				kh=new khachHang(rs.getLong("makh"), rs.getString("hoten"), rs.getString("diachi"), rs.getString("sodt"), rs.getString("email"), rs.getString("tendn"), rs.getString("pass"));
			}
			rs.close();
			ps.close();
			kn.cn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kh;
	}
	public static String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(input.getBytes("UTF-8"));
        
        // Chuyển sang chuỗi hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	public boolean taoKhachHang(String tendn, String hoten, String diachi, String sodt, String email, String pass) {
	    try {
	        ketNoi kn = new ketNoi();
	        kn.ketnoi();

	        // check trùng
	        String checkSql = "select count(*) from KhachHang where tendn=?";
	        PreparedStatement psCheck = kn.cn.prepareStatement(checkSql);
	        psCheck.setString(1, tendn);
	        ResultSet rs = psCheck.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            rs.close(); psCheck.close(); kn.cn.close();
	            return false;
	        }
	        rs.close(); psCheck.close();

	        String sql = "insert into KhachHang(tendn, hoten, diachi, sodt, email, pass) values (?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = kn.cn.prepareStatement(sql);
	        ps.setString(1, tendn);
	        ps.setString(2, hoten);
	        ps.setString(3, diachi);
	        ps.setString(4, sodt);
	        ps.setString(5, email);
	        ps.setString(6, md5(pass));

	        ps.executeUpdate();
	        ps.close();
	        kn.cn.close();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
