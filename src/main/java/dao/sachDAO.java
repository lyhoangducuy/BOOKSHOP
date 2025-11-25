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
	public ArrayList<sach> getSachTheoLoai(String maloai, int trang, int pageSize) throws Exception {
	    ArrayList<sach> dsTrang = new ArrayList<>();
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "SELECT * FROM sach WHERE maloai=? ORDER BY masach OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, maloai);
	    ps.setInt(2, (trang-1)*pageSize);
	    ps.setInt(3, pageSize);

	    ResultSet rs = ps.executeQuery();
	    while(rs.next()) {
	        dsTrang.add(new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"),
	                rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"), rs.getString("anh"),
	                rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate(), rs.getString("tacgia")));
	    }
	    rs.close(); ps.close(); kn.cn.close();
	    return dsTrang;
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

	// Lấy sách theo tên có phân trang
	public ArrayList<sach> getSachTheoTen(String tenNhap, int trang, int pageSize) throws Exception {
	    ArrayList<sach> dsTrang = new ArrayList<>();
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "SELECT * FROM sach WHERE tensach LIKE ? ORDER BY masach OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, "%" + tenNhap + "%");
	    ps.setInt(2, (trang-1)*pageSize);
	    ps.setInt(3, pageSize);

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
	               + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    int offset = 6 * trang - 6; // công thức bạn đưa
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

}
