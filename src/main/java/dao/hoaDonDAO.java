package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.hoaDon;
import model.ketNoi;
import model.phuongThucThanhToan;
import model.trangThaiHoaDon;

public class hoaDonDAO {
    private ArrayList<hoaDon> ds = new ArrayList<>();


    // --- Lấy tất cả hóa đơn ---
    public ArrayList<hoaDon> getDsHoaDon() throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM hoadon";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        ds.clear();

        while (rs.next()) {
        	String ptStr = rs.getString("thanhToan");
        	phuongThucThanhToan pt = null;
        	if(ptStr != null) {
        	    pt = phuongThucThanhToan.valueOf(ptStr);
        	} else {
        	    // gán giá trị mặc định nếu muốn, ví dụ:
        	    pt = phuongThucThanhToan.TIEN_MAT;
        	}

            ds.add(new hoaDon(
                rs.getLong("MaHoaDon"),
                rs.getLong("makh"),
                rs.getTimestamp("NgayMua").toLocalDateTime().toLocalDate(),
                rs.getBoolean("damua"),
                rs.getString("TenNguoiNhan"),
                rs.getString("DiaChiGiaoHang"),
                rs.getString("SoDienThoai"),
                pt)
            );
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return ds;
    }

    public hoaDon timHoaDonTheoMa(long maHoaDon) throws Exception {
        getDsHoaDon();
        for (hoaDon h : ds) {
            if (h.getMaHoaDon() == maHoaDon) {
                return h;
            }
        }
        return null;
    }

    public ArrayList<hoaDon> getDsHoaDonDaMua() throws Exception {
        return getDsTheoTrangThaiMua(true);
    }

    public ArrayList<hoaDon> getDsHoaDonChuaMua() throws Exception {
        return getDsTheoTrangThaiMua(false);
    }

    public ArrayList<hoaDon> getDsHoaDonDaMuaTheoMaKh(long makh) throws Exception {
        return getDsTheoTrangThaiMuaVaKhach(true, makh);
    }

    public ArrayList<hoaDon> getDsHoaDonChuaMuaTheoMaKh(long makh) throws Exception {
        return getDsTheoTrangThaiMuaVaKhach(false, makh);
    }

    // --- Helper lấy ds theo damua ---
    private ArrayList<hoaDon> getDsTheoTrangThaiMua(boolean daMua) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM hoadon WHERE damua = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setBoolean(1, daMua);
        ResultSet rs = ps.executeQuery();

        ds.clear();

        while (rs.next()) {
        	String ptStr = rs.getString("thanhToan");
        	phuongThucThanhToan pt = null;
        	if(ptStr != null) {
        	    pt = phuongThucThanhToan.valueOf(ptStr);
        	} else {
        	    // gán giá trị mặc định nếu muốn, ví dụ:
        	    pt = phuongThucThanhToan.TIEN_MAT;
        	}
            ds.add(new hoaDon(
                rs.getLong("MaHoaDon"),
                rs.getLong("makh"),
                rs.getTimestamp("NgayMua").toLocalDateTime().toLocalDate(),
                rs.getBoolean("damua"),
                rs.getString("TenNguoiNhan"),
                rs.getString("DiaChiGiaoHang"),
                rs.getString("SoDienThoai"),
                pt
            ));
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return ds;
    }

    private ArrayList<hoaDon> getDsTheoTrangThaiMuaVaKhach(boolean daMua, long makh) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM hoadon WHERE damua = ? AND makh = ? ORDER BY MaHoaDon DESC";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setBoolean(1, daMua);
        ps.setLong(2, makh);
        ResultSet rs = ps.executeQuery();

        ds.clear();

        while (rs.next()) {
        	String ptStr = rs.getString("thanhToan");
        	phuongThucThanhToan pt = null;
        	if(ptStr != null) {
        	    pt = phuongThucThanhToan.valueOf(ptStr);
        	} else {
        	    // gán giá trị mặc định nếu muốn, ví dụ:
        	    pt = phuongThucThanhToan.TIEN_MAT;
        	}
            ds.add(new hoaDon(
                rs.getLong("MaHoaDon"),
                rs.getLong("makh"),
                rs.getTimestamp("NgayMua").toLocalDateTime().toLocalDate(),
                rs.getBoolean("damua"),
                rs.getString("TenNguoiNhan"),
                rs.getString("DiaChiGiaoHang"),
                rs.getString("SoDienThoai"),
                pt
            ));
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return ds;
    }

    public ArrayList<hoaDon> timHoaDonTheoMaKH(long makh) throws Exception {
        getDsHoaDon();
        ArrayList<hoaDon> dshd = new ArrayList<>();
        for (hoaDon h : ds) {
            if (h.getMakh() == makh) {
                dshd.add(h);
            }
        }
        return dshd;
    }

    public boolean capNhatTrangThaiDaMua(long maHoaDon, boolean daMuaStatus) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "UPDATE hoadon SET damua = ? WHERE MaHoaDon = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setBoolean(1, daMuaStatus);
        ps.setLong(2, maHoaDon);

        int soDongAnhHuong = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return soDongAnhHuong > 0;
    }

    public void xoaHoaDonTheoMaHoaDon(long maHoaDon, boolean daMua) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "DELETE FROM hoadon WHERE MaHoaDon = ? AND damua = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setBoolean(2, daMua);

        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }

    public hoaDon themHoaDon(long maKhachHang) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "INSERT INTO hoadon (makh, NgayMua, damua, TenNguoiNhan, DiaChiGiaoHang, SoDienThoai) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = kn.cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, maKhachHang);
        ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        ps.setBoolean(3, false);
        ps.setString(4, "");
        ps.setString(5, "");
        ps.setString(6, "");

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        long maHoaDon = -1;
        if (rs.next()) {
            maHoaDon = rs.getLong(1);
        }

        rs.close();
        ps.close();
        kn.cn.close();

        if (maHoaDon == -1) return null;

        return new hoaDon(
            maHoaDon,
            maKhachHang,
            LocalDate.now(),
            false,
            "", 
            "", 
            "",
            phuongThucThanhToan.TIEN_MAT
        );
    }

    public boolean capNhatThongTinNguoiNhan(long maHoaDon, String tenNguoiNhan, String diaChiGiaoHang, String soDienThoai) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "UPDATE hoadon SET TenNguoiNhan = ?, DiaChiGiaoHang = ?, SoDienThoai = ? WHERE MaHoaDon = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setString(1, tenNguoiNhan);
        ps.setString(2, diaChiGiaoHang);
        ps.setString(3, soDienThoai);
        ps.setLong(4, maHoaDon);

        int soDongAnhHuong = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return soDongAnhHuong > 0;
    }
    public boolean capNhatThongTinThanhToan(long maHoaDon, phuongThucThanhToan pttt) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "UPDATE hoadon thanhToan= ? WHERE MaHoaDon = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maHoaDon);
        ps.setString(2, pttt.name());

        int soDongAnhHuong = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return soDongAnhHuong > 0;
    }

    public long getThanhTien(long mhd) throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = "SELECT ISNULL(SUM(ct.SoLuongMua * s.gia), 0) AS TongTien " +
                     "FROM dbo.ChiTietHoaDon ct " +
                     "INNER JOIN dbo.sach s ON ct.MaSach = s.masach " +
                     "WHERE ct.MaHoaDon = ?";

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, mhd);

        ResultSet rs = ps.executeQuery();
        long t = 0;
        if (rs.next()) {
            t = rs.getLong("TongTien");
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return t;
    }
    public int demSoDonHomNay() throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = """
            SELECT COUNT(*) 
            FROM hoadon
            WHERE CAST(NgayMua AS DATE) = CAST(GETDATE() AS DATE)
        """;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        ps.close();
        kn.cn.close();
        return count;
    }
    public int demSoDonThangNay() throws Exception {
        ketNoi kn = new ketNoi();
        kn.ketnoi();

        String sql = """
            SELECT COUNT(*) 
            FROM hoadon
            WHERE MONTH(NgayMua) = MONTH(GETDATE())
              AND YEAR(NgayMua) = YEAR(GETDATE())
        """;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        ps.close();
        kn.cn.close();
        return count;
    }

}
