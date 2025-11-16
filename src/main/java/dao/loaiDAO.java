package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ketNoi;
import model.loai;

public class loaiDAO {
	ArrayList<loai> ds=new ArrayList<loai>();
	public loai getTenLoai(String maloai) throws Exception{
		loai ten=new loai(maloai, "");
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="select * from loai where maloai=?";
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		ps.setString(1, maloai);
		
		ResultSet rs=ps.executeQuery();
		
		if (rs.next()) {
			ten.setMaLoai(rs.getString("maloai"));
			ten.setTenLoai(rs.getString("tenloai"));
		}
		return ten;
	}
	public ArrayList<loai> getLoai() throws Exception{
		String ten="";
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="select * from loai";
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		
		ResultSet rs=ps.executeQuery();
		while (rs.next())
			ds.add(new loai(rs.getString("maloai"), rs.getString("tenloai")));
		return ds;
	}
}
