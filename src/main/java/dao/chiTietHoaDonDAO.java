package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.chiTietHoaDon;
import model.ketNoi;

public class chiTietHoaDonDAO {

    // Lấy tất cả chi tiết hóa đơn
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

    // Lấy chi tiết hóa đơn theo MaHoaDon
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
                    rs.getBoolean("damua")));
        }

        ps.close();
        kn.cn.close();
        return dshd;
    }

    // Lấy một chi tiết hóa đơn cụ thể
    public chiTietHoaDon timChiTietHoaDon(long maHoaDon, String maSach) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaSach = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setString(2, maSach);

        ResultSet rs = ps.executeQuery();

        chiTietHoaDon ct = null;
        if (rs.next()) {
            ct = new chiTietHoaDon(
                    rs.getString("MaChiTietHD"),
                    rs.getString("MaSach"),
                    rs.getInt("SoLuongMua"),
                    rs.getLong("MaHoaDon"),
                    rs.getBoolean("damua")
            );
        }

        ps.close();
        kn.cn.close();
        return ct;
    }

    // Thêm chi tiết hóa đơn (chưa mua, có thể thêm info giao hàng sau)
    public void themChiTietHoaDon(long maHoaDon, String maSach, int soLuong) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaSach, SoLuongMua, damua) " +
                     "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setString(2, maSach);
        ps.setInt(3, soLuong);
        ps.setBoolean(4, false);
        ps.executeUpdate();
        ps.close();
        kn.cn.close();
    }

    // Cập nhật số lượng sách
    public int suaSoLuongSachHoaDon(long mahd, String masach, int sl) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "UPDATE ChiTietHoaDon SET SoLuongMua = ? WHERE MaHoaDon = ? AND MaSach = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setInt(1, sl);
        ps.setLong(2, mahd);
        ps.setString(3, masach);

        ps.executeUpdate();

        ps.close();
        kn.cn.close();
        return sl;
    }

    // Xóa chi tiết hóa đơn theo MaHoaDon
    public void xoaChiTietHoaDonTheoMaHoaDon(long maHoaDon, boolean daMua) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND damua=?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setBoolean(2, daMua);
        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }

    // Xóa 1 sách trong chi tiết hóa đơn
    public void xoaSachChiTietHoaDon(long maHoaDon, String masach) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaSach=?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setString(2, masach);
        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }

   

}
