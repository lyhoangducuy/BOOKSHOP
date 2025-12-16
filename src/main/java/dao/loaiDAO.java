package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ketNoi;
import model.loai;

public class loaiDAO {
	
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
		rs.close();
		ps.close();
		kn.cn.close();
		return ten;
	}
	public ArrayList<loai> getLoai() throws Exception{
		ArrayList<loai> ds=new ArrayList<loai>();
		String ten="";
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="select * from loai";
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		
		ResultSet rs=ps.executeQuery();
		while (rs.next())
			ds.add(new loai(rs.getString("maloai"), rs.getString("tenloai")));
		rs.close();
		ps.close();
		kn.cn.close();
		return ds;
	}
	public ArrayList<loai> getLoaiPhanTrang(int trang) throws Exception{
		ArrayList<loai> ds=new ArrayList<loai>();
		String ten="";
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="SELECT * FROM loai "
				+ "ORDER BY maloai "
		        + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		int offset = 8 * trang - 8;
		ps.setInt(1, offset);
		ResultSet rs=ps.executeQuery();
		while (rs.next())
			ds.add(new loai(rs.getString("maloai"), rs.getString("tenloai")));
		rs.close();
		ps.close();
		kn.cn.close();
		return ds;
	}
	public int updateLoai(loai l) throws Exception {
        ketNoi kn=new ketNoi();
        kn.ketnoi();

        String sql="UPDATE loai SET tenloai = ? WHERE maloai = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setString(1, l.getTenLoai());
        ps.setString(2, l.getMaLoai());

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq;
    }
	public int deleteLoai(String maloai) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM loai WHERE maloai = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setString(1, maloai);

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq;
    }
	public int insertLoai(loai l) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "INSERT INTO loai(maloai, tenloai) VALUES(?, ?)";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);

	    ps.setString(1, l.getMaLoai());
	    ps.setString(2, l.getTenLoai());

	    int result = ps.executeUpdate();

	    ps.close();
	    kn.cn.close();
	    
	    return result;
	}
	public ArrayList<loai> getLoaiByTenLoai(String key) throws Exception{
		ArrayList<loai> ds=new ArrayList<loai>();
		String ten="";
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="select * from loai where maloai like ?";
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		ps.setString(1, "%" + key.toLowerCase().trim() + "%");
		ResultSet rs=ps.executeQuery();
		while (rs.next())
			ds.add(new loai(rs.getString("maloai"), rs.getString("tenloai")));
		rs.close();
		ps.close();
		kn.cn.close();
		return ds;
	}
	public ArrayList<loai> getLoaiByTenLoaiTheoTrang(String key,int trang) throws Exception{
		ArrayList<loai> ds=new ArrayList<loai>();
		String ten="";
		ketNoi kn=new ketNoi();
		kn.ketnoi();
		String sql="SELECT * FROM loai WHERE maloai LIKE ? "
		+ "ORDER BY maloai "
        + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
		PreparedStatement ps=kn.cn.prepareStatement(sql);
		ps.setString(1, "%" + key.toLowerCase().trim() + "%");
		int offset = 8 * trang - 8;
		ps.setInt(2, offset);
		ResultSet rs=ps.executeQuery();
		while (rs.next())
			ds.add(new loai(rs.getString("maloai"), rs.getString("tenloai")));
		rs.close();
		ps.close();
		kn.cn.close();
		return ds;
	}
	public int getTotalLoai() throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "SELECT COUNT(*) FROM loai";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    int total = 0;
	    if (rs.next()) {
	        total = rs.getInt(1);
	    }

	    rs.close();
	    ps.close();
	    kn.cn.close();

	    return total;
	}
	public int getTotalLoaiTheoTen(String key) throws Exception {
	    ketNoi kn = new ketNoi();
	    kn.ketnoi();

	    String sql = "SELECT COUNT(*) FROM loai WHERE maloai LIKE ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setString(1, "%" + key.toLowerCase().trim() + "%");

	    ResultSet rs = ps.executeQuery();

	    int total = 0;
	    if (rs.next()) {
	        total = rs.getInt(1);
	    }

	    rs.close();
	    ps.close();
	    kn.cn.close();

	    return total;
	}


}
