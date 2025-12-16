package dao;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.ketNoi;
import model.sach;


public class sachDAO {
	ArrayList<sach> ds=new ArrayList<sach>();
	// Lấy sách theo loại có phân trang  
	public ArrayList<sach> getSachTheoLoai(String maloai, int trang) throws Exception {
	    ArrayList<sach> dsTrang = new ArrayList<>();
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "SELECT * FROM sach WHERE maloai LIKE ? "
	               + "ORDER BY masach "
	               + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, "%" + maloai + "%");
	    int offset = 8 * trang - 8;
	    ps.setInt(2, offset);
	    ResultSet rs = ps.executeQuery();
	    while(rs.next()) {
	        dsTrang.add(new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"),
	                rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"), rs.getString("anh"),
	                rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate(), rs.getString("tacgia")));
	    }
	    rs.close(); ps.close(); kn.cn.close();
	    return dsTrang;
	}
	public ArrayList<sach> getSachTheoTen(String ten) throws Exception {
	    ArrayList<sach> dsSach = new ArrayList<>();
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    // Sử dụng LIKE để tìm tên sách chứa từ khóa (không phân biệt chữ hoa/chữ thường)
	    String sql = "SELECT * FROM sach WHERE tensach LIKE ? ORDER BY masach";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, "%" + ten + "%"); // %ten% nghĩa là chứa từ khóa

	    ResultSet rs = ps.executeQuery();
	    while(rs.next()) {
	        dsSach.add(new sach(
	                rs.getString("masach"),
	                rs.getString("tensach"),
	                rs.getInt("soluong"),
	                rs.getLong("gia"),
	                rs.getString("maloai"),
	                rs.getInt("sotap"),
	                rs.getString("anh"),
	                rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate(),
	                rs.getString("tacgia")
	        ));
	    }

	    rs.close();
	    ps.close();
	    kn.cn.close();

	    return dsSach;
	}

	// Tổng số sách theo loại
	public int getTotalSachTheoLoai(String maloai) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(*) FROM sach WHERE maloai=?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maloai);
	    ResultSet rs = ps.executeQuery();
	    int total = 0;
	    if(rs.next()) total = rs.getInt(1);
	    rs.close(); ps.close(); kn.cn.close();
	    return total;
	}
	public ArrayList<sach> getSachTheoTen(String tenNhap, int trang) throws Exception {
	    ArrayList<sach> dsTrang = new ArrayList<>();
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "SELECT * FROM sach WHERE tensach LIKE ? "
	               + "ORDER BY masach "
	               + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, "%" + tenNhap + "%");
	    int offset = 8 * trang - 8;
	    ps.setInt(2, offset);
	    ResultSet rs = ps.executeQuery();
	    while(rs.next()) {
	        dsTrang.add(new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"),
	                rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"), rs.getString("anh"),
	                rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate(), rs.getString("tacgia")));
	    }
	    rs.close(); ps.close(); kn.cn.close();
	    return dsTrang;
	}

	// Tổng số sách theo tên
	public int getTotalSachTheoTen(String tenNhap) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(*) FROM sach WHERE tensach LIKE ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, "%" + tenNhap + "%");
	    ResultSet rs = ps.executeQuery();
	    int total = 0;
	    if(rs.next()) total = rs.getInt(1);
	    rs.close(); ps.close(); kn.cn.close();
	    return total;
	}


	public ArrayList<sach> getSach() throws Exception{
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql = "select * from sach";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			LocalDate date = rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate();

			ds.add(new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"), rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"),rs.getString("anh"),date , rs.getString("tacgia")));
			
		}
		rs.close();
		ps.close();
		kn.cn.close();
		return ds;
	}
	public sach getSachTheoMa(String masach) throws Exception{
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql = "select * from sach where masach = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, masach);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			java.sql.Timestamp ts = rs.getTimestamp("NgayNhap");
			LocalDate date = (ts != null) ? ts.toLocalDateTime().toLocalDate() : null;



			return new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"), rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"),rs.getString("anh"),date , rs.getString("tacgia"));
			
		}
		rs.close();
		ps.close();
		kn.cn.close();
		return null;
	}
	public ArrayList<sach> getSachTheoTrang(int trang) throws Exception {
	    ArrayList<sach> dsTrang = new ArrayList<>();

	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    // SQL phân trang theo công thức 6*i - 6
	    String sql = "SELECT * FROM sach ORDER BY masach "
	               + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";

	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    int offset = 8 * trang - 8; // công thức bạn đưa
	    ps.setInt(1, offset);

	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        LocalDate date = rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate();
	        dsTrang.add(new sach(
	                rs.getString("masach"),
	                rs.getString("tensach"),
	                rs.getInt("soluong"),
	                rs.getLong("gia"),
	                rs.getString("maloai"),
	                rs.getInt("sotap"),
	                rs.getString("anh"),
	                date,
	                rs.getString("tacgia")
	        ));
	    }

	    rs.close();
	    ps.close();
	    kn.cn.close();

	    return dsTrang;
	}
	public int getTotalSach() throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(*) FROM sach";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    int total = 0;
	    if(rs.next()) {
	        total = rs.getInt(1);
	    }
	    rs.close();
	    ps.close();
	    kn.cn.close();
	    return total;
	}
	public int updateSach(sach s) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "UPDATE sach SET tensach=?, soluong=?, gia=?, maloai=?, sotap=?, anh=?, ngaynhap=?, tacgia=? " +
	                 "WHERE masach=?";

	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    ps.setString(1, s.getTenSach());
	    ps.setInt(2, s.getSoLuong());
	    ps.setLong(3, s.getGia());
	    ps.setString(4, s.getMaLoai());
	    ps.setInt(5, s.getSoTap());
	    ps.setString(6, s.getAnh());
	    ps.setDate(7, java.sql.Date.valueOf(s.getNgayNhap())); // LocalDate → SQL
	    ps.setString(8, s.getTacGia());

	    ps.setString(9, s.getMaSach());  // WHERE masach = ?

	    int kq = ps.executeUpdate();

	    ps.close();
	    kn.cn.close();

	    return kq;
	}
	public int deleteSach(String masach) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM sach WHERE masach = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setString(1, masach);

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq;
    }
	public int insertSach(sach s) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "INSERT INTO sach (masach, tensach, soluong, gia, maloai, sotap, anh, ngaynhap, tacgia) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    ps.setString(1, s.getMaSach());
	    ps.setString(2, s.getTenSach());
	    ps.setInt(3, s.getSoLuong());
	    ps.setLong(4, s.getGia());
	    ps.setString(5, s.getMaLoai());
	    ps.setInt(6, s.getSoTap());
	    ps.setString(7, s.getAnh());
	    ps.setDate(8, java.sql.Date.valueOf(s.getNgayNhap()));
	    ps.setString(9, s.getTacGia());

	    int result = ps.executeUpdate();

	    ps.close();
	    kn.cn.close();

	    return result;
	}
	public int tangSoLuong(String maSach, int n) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "UPDATE sach SET soluong = soluong + ? WHERE masach = ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    ps.setInt(1, n);
	    ps.setString(2, maSach);

	    int kq = ps.executeUpdate();

	    ps.close();
	    kn.cn.close();

	    return kq;
	}
	public int giamSoLuong(String maSach, int n) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "UPDATE sach SET soluong = soluong - ? "
	               + "WHERE masach = ? AND soluong >= ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    ps.setInt(1, n);
	    ps.setString(2, maSach);
	    ps.setInt(3, n);

	    int kq = ps.executeUpdate();

	    ps.close();
	    kn.cn.close();

	    return kq;
	}
	public int capNhatTonKho(String maSach, int delta) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql;
	    PreparedStatement ps;

	    if (delta > 0) {
	        // Mua thêm → trừ kho
	        sql = "UPDATE sach SET soluong = soluong - ? "
	            + "WHERE masach = ? AND soluong >= ?";
	        ps = kn.cn.prepareStatement(sql);
	        ps.setInt(1, delta);
	        ps.setString(2, maSach);
	        ps.setInt(3, delta);
	    } else {
	        // Trả bớt → cộng kho
	        sql = "UPDATE sach SET soluong = soluong + ? "
	            + "WHERE masach = ?";
	        ps = kn.cn.prepareStatement(sql);
	        ps.setInt(1, Math.abs(delta));
	        ps.setString(2, maSach);
	    }

	    int kq = ps.executeUpdate();

	    ps.close();
	    kn.cn.close();
	    return kq;
	}




}