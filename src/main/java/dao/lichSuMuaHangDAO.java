package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ketNoi;
import model.lichSuMuaHang;

public class lichSuMuaHangDAO {
	public ArrayList<lichSuMuaHang> getlichsu(Long makh) throws Exception{
	    ArrayList<lichSuMuaHang> ds= new ArrayList<lichSuMuaHang>();
	    //b1: Kết nối vao csdl
	    ketNoi kn= new ketNoi();
	    kn.ketnoi();
	    //b2: Thiet lap cau lenh sql
	    String sql= "select * from VlSu where makh=?";
	    PreparedStatement cmd= kn.cn.prepareStatement(sql);
	    //b3: Truyen tham so vao cau lẹnh sql (neu co)
	    cmd.setLong(1, makh);
	    //b4: Cho thuc hien cau lenh sql
	    ResultSet rs= cmd.executeQuery();
	    //b5: Duyet rs va luu vao mang dong
	    while(rs.next()) {
	        String tensach=rs.getString("tensach");
	        Long gia=rs.getLong("gia");
	        Long soLuongMua=rs.getLong("soLuongMua");
	        Long tT=rs.getLong("thanhTien");
	        Boolean damua=rs.getBoolean("damua");
	        //Long makh=rs.getLong("makh");
	        ds.add(new lichSuMuaHang(tensach, gia, soLuongMua, tT, damua, makh));
	    }
	    //b6: Dong cac doi tuong dang mo: rs, ket noi
	    rs.close();kn.cn.close();
	    return ds;
	}
}

