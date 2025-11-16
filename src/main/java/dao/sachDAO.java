package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.ketNoi;
import model.sach;


public class sachDAO {
	ArrayList<sach> ds=new ArrayList<sach>();
	public ArrayList<sach> getSachTheoLoai(String maloai) throws Exception{
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="select * from sach where maloai=?";
		
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		ps.setString(1, maloai);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			LocalDate date = rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate();

			ds.add(new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"), rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"),rs.getString("anh"),date , rs.getString("tacgia")));
			
		}
		return ds;
	}
	public ArrayList<sach> getSachTheoTen(String tenNhap) throws Exception{
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql = "select * from sach where tensach like ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, "%" + tenNhap + "%");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			LocalDate date = rs.getTimestamp("NgayNhap").toLocalDateTime().toLocalDate();

			ds.add(new sach(rs.getString("masach"), rs.getString("tensach"), rs.getInt("soluong"), rs.getLong("gia"), rs.getString("maloai"), rs.getInt("sotap"),rs.getString("anh"),date , rs.getString("tacgia")));
			
		}
		return ds;
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
		return ds;
	}
}
