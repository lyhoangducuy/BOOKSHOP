package dao;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.gioHang;
import model.hoaDon;
import model.ketNoi;

public class hoaDonDAO {
    ArrayList<hoaDon> ds = new ArrayList<>();

    public ArrayList<hoaDon> getDsHoaDon() throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM hoadon";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ds.clear();  // ⭐ FIX

        while (rs.next()) {
            LocalDate date = rs.getTimestamp("NgayMua")
                               .toLocalDateTime().toLocalDate();
            ds.add(new hoaDon(
                rs.getLong("MaHoaDon"), 
                rs.getLong("makh"),
                date,
                rs.getBoolean("damua")
            ));
        }

        ps.close();
        kn.cn.close();
        return ds;
    }

    public hoaDon timHoaDonTheoMa(long maHoaDon) throws Exception {
        getDsHoaDon();

        for (hoaDon h : ds) {
            if (h.getMaHoaDon()==maHoaDon) {  // ⭐ FIX
                return h;
            }
        }
        return null;
    }
    public ArrayList<hoaDon> timHoaDonTheoMaKH(long makh) throws Exception {
        getDsHoaDon();
        ArrayList<hoaDon> dshd=new ArrayList<hoaDon>();
        for (hoaDon h : ds) {
            if (h.getMakh()==makh) {  // ⭐ FIX
                dshd.add(h);
            }
        }
        return dshd;
    }
    public void xoaHoaDonTheoMaHoaDon(long maHoaDon, boolean daMua) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();
        
        String sql = "DELETE FROM hoadon WHERE MaHoaDon=? AND damua=?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setBoolean(2, daMua);

        ps.executeUpdate(); // <= dùng executeUpdate

        ps.close();
        kn.cn.close();
    }



    public hoaDon themHoaDon(long maKhachHang) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "INSERT INTO hoadon (makh, NgayMua, damua) VALUES (?, ?, ?)";
        PreparedStatement ps = kn.cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, maKhachHang);
        ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        ps.setBoolean(3, false);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        long maHoaDon =-1;
        if (rs.next()) {
            maHoaDon = rs.getLong(1);
        }

        rs.close();
        ps.close();
        kn.cn.close();

        if (maHoaDon==-1 ) return null;

        return new hoaDon(maHoaDon, maKhachHang, LocalDate.now(), false);
    }
}
