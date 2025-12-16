package dao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ketNoi;
import model.vaiTro;

public class vaiTroDAO {

	public vaiTro kiemTraDangNhap(String tendn, String matKhau) throws Exception {
		vaiTro vt = null;

		ketNoi kn = new ketNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM DangNhap WHERE TenDangNhap=? AND MatKhau=?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);

		ps.setString(1, tendn);
		ps.setString(2, md5(matKhau)); // dùng chung md5

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			vt = new vaiTro(
				rs.getString("TenDangNhap"),
				rs.getString("MatKhau"),
				rs.getBoolean("Quyen")
			);
		}

		rs.close();
		ps.close();
		kn.cn.close();

		return vt;
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
	public boolean taoVaiTro(String tendn, String matKhau, boolean quyen) {
        try {
            ketNoi kn = new ketNoi();
            kn.ketnoi();

            String sql = "INSERT INTO DangNhap(TenDangNhap, MatKhau, Quyen) VALUES (?, ?, ?)";
            PreparedStatement ps = kn.cn.prepareStatement(sql);

            ps.setString(1, tendn);
            ps.setString(2, md5(matKhau)); // dùng chung md5
            ps.setBoolean(3, quyen);

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
