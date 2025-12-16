package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.gioHangSach;
import model.ketNoi;

public class gioHangSachDAO {

    // 1️⃣ Lấy danh sách sách trong giỏ hàng theo MaGioHang
    public ArrayList<gioHangSach> getGioHangSachTheoMaGioHang(long maGioHang) throws Exception {
        ArrayList<gioHangSach> ds = new ArrayList<>();

        try {
            ketNoi kn = new ketNoi();
            kn.ketnoi();

            String sql = "SELECT * FROM GioHang_Sach WHERE MaGioHang = ?";
            PreparedStatement ps = kn.cn.prepareStatement(sql);
            ps.setLong(1, maGioHang);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gioHangSach ghs = new gioHangSach(
                    rs.getLong("MaGioHang"),
                    rs.getString("MaSach"),
                    rs.getInt("SoLuong"),
                    rs.getLong("DonGia")
                );
                ds.add(ghs);
            }

            rs.close();
            ps.close();
            kn.cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    // 2️⃣ Kiểm tra 1 sách đã tồn tại trong giỏ hàng chưa
    public gioHangSach kiemTraTonTai(long maGioHang, String maSach) throws Exception {
        gioHangSach ghs = null;

        try {
            ketNoi kn = new ketNoi();
            kn.ketnoi();

            String sql = "SELECT * FROM GioHang_Sach WHERE MaGioHang = ? AND MaSach = ?";
            PreparedStatement ps = kn.cn.prepareStatement(sql);
            ps.setLong(1, maGioHang);
            ps.setString(2, maSach);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ghs = new gioHangSach(
                    rs.getLong("MaGioHang"),
                    rs.getString("MaSach"),
                    rs.getInt("SoLuong"),
                    rs.getLong("DonGia")
                );
            }

            rs.close();
            ps.close();
            kn.cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ghs;
    }
    public int tangSoLuong(long maGioHang, String maSach, int soLuongThem) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "UPDATE GioHang_Sach SET SoLuong = SoLuong + ? "
                   + "WHERE MaGioHang = ? AND MaSach = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setInt(1, soLuongThem);
        ps.setLong(2, maGioHang);
        ps.setString(3, maSach);

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq;
    }
    public int giamSoLuong(long maGioHang, String maSach, int soLuongGiam) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "UPDATE GioHang_Sach SET SoLuong = SoLuong - ? "
                   + "WHERE MaGioHang = ? AND MaSach = ? AND SoLuong >= ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setInt(1, soLuongGiam);
        ps.setLong(2, maGioHang);
        ps.setString(3, maSach);
        ps.setInt(4, soLuongGiam);

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq;
    }
    public void capNhatSoLuong(long maGioHang, String maSach, int soLuongMoi) throws Exception {

        ketNoi kn = new ketNoi();
        kn.ketnoi();


        // Cập nhật số lượng mới
        String sql = "UPDATE GioHang_Sach SET SoLuong = ? WHERE MaGioHang = ? AND MaSach = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setInt(1, soLuongMoi);
        ps.setLong(2, maGioHang);
        ps.setString(3, maSach);

        ps.executeUpdate();

        ps.close();
        kn.cn.close();

    }
    public long tongThanhTien(long maGioHang) throws Exception {
        long tong = 0;

        try {
            ketNoi kn = new ketNoi();
            kn.ketnoi();

            String sql = "SELECT SUM(SoLuong * DonGia) FROM GioHang_Sach WHERE MaGioHang = ?";
            PreparedStatement ps = kn.cn.prepareStatement(sql);
            ps.setLong(1, maGioHang);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tong = rs.getLong(1); // NULL → 0
            }

            rs.close();
            ps.close();
            kn.cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tong;
    }
    public int xoaGioHangSach(long maGioHang, String maSach) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM GioHang_Sach WHERE MaGioHang = ? AND MaSach = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setLong(1, maGioHang);
        ps.setString(2, maSach);

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq; // số dòng bị xóa (0 hoặc 1)
    }
    public int themGioHangSach(long maGioHang, gioHangSach ghs) throws Exception {
        int kq = 0;
        try {
            ketNoi kn = new ketNoi();
            kn.ketnoi();

            String sql = "INSERT INTO GioHang_Sach (MaGioHang, MaSach, SoLuong, DonGia) "
                       + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = kn.cn.prepareStatement(sql);
            ps.setLong(1, maGioHang);
            ps.setString(2, ghs.getMaSach());
            ps.setInt(3, ghs.getSoLuong());
            ps.setLong(4, ghs.getDonGia());

            kq = ps.executeUpdate();

            ps.close();
            kn.cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public int xoaAll(long maGioHang) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM GioHang_Sach WHERE MaGioHang = ? ";
        PreparedStatement ps = kn.cn.prepareStatement(sql);

        ps.setLong(1, maGioHang);

        int kq = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return kq;
    }




}
