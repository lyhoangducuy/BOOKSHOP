package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.chiTietHoaDon;
import model.ketNoi;

public class chiTietHoaDonDAO {

    // Lấy tất cả chi tiết hóa đơn (nếu cần)
    public ArrayList<chiTietHoaDon> getDsChiTietHoaDon() throws Exception {
        ArrayList<chiTietHoaDon> ds = new ArrayList<>();
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM ChiTietHoaDon";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ds.add(new chiTietHoaDon(
                    rs.getString("MaChiTietHD"),
                    rs.getString("MaSach"),
                    rs.getInt("SoLuongMua"),
                    rs.getLong("MaHoaDon"),
                    rs.getBoolean("damua")
            ));
        }
        ps.close();
        kn.cn.close();
        return ds;
    }

    // Lấy chi tiết hóa đơn theo MaHoaDon trực tiếp từ DB
    public ArrayList<chiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(long maHoaDon) throws Exception {
        ArrayList<chiTietHoaDon> dshd = new ArrayList<>();
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            dshd.add(new chiTietHoaDon(
                    rs.getString("MaChiTietHD"),
                    rs.getString("MaSach"),
                    rs.getInt("SoLuongMua"),
                    rs.getLong("MaHoaDon"),
                    rs.getBoolean("damua")
            ));
        }

        ps.close();
        kn.cn.close();
        return dshd;
    }

    // Thêm chi tiết hóa đơn
    public void themChiTietHoaDon(long maHoaDon, String maSach, int soLuong) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaSach, SoLuongMua, damua) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setString(2, maSach);
        ps.setInt(3, soLuong);
        ps.setBoolean(4, false); // mặc định chưa mua
        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }

    // Xóa chi tiết hóa đơn theo MaHoaDon
    public void xoaChiTietHoaDonTheoMaHoaDon(long maHoaDon,boolean daMua) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? and damua=?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setBoolean(2, daMua);
        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }

}
