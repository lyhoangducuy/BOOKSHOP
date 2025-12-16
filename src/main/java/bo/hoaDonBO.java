package bo;

import java.util.ArrayList;

import dao.hoaDonDAO;
import model.hoaDon;
import model.phuongThucThanhToan;

public class hoaDonBO {
    hoaDonDAO hDAO = new hoaDonDAO();

    public ArrayList<hoaDon> getDsHoaDon() throws Exception {
        return hDAO.getDsHoaDon();  // ⭐ FIX
    }

    public hoaDon timHoaDonTheoMa(long maHoaDon) throws Exception {
        return hDAO.timHoaDonTheoMa(maHoaDon);
    }
    public ArrayList<hoaDon> timHoaDonTheoMaKh(long makh) throws Exception {
        return hDAO.timHoaDonTheoMaKH(makh);
    }
    public boolean capNhatThongTinThanhToan(long maHoaDon, phuongThucThanhToan pttt) throws Exception {
    	return hDAO.capNhatThongTinThanhToan(maHoaDon, pttt);
    }


    public hoaDon themHoaDon(long makh) throws Exception {
        return hDAO.themHoaDon(makh);
    }
    public void xoaHoaDonTheoMaHoaDOn(long maHoaDon,boolean daMua) throws Exception {
    	hDAO.xoaHoaDonTheoMaHoaDon(maHoaDon,daMua);
    }
    public ArrayList<hoaDon> getDsHoaDonDaMua() throws Exception {
    	return hDAO.getDsHoaDonDaMua();
    }
    public ArrayList<hoaDon> getDsHoaDonChuaMua() throws Exception {
    	return hDAO.getDsHoaDonChuaMua();
    }
    public boolean capNhatTrangThaiDaMua(long maHoaDon, boolean daMuaStatus) throws Exception {
    	return hDAO.capNhatTrangThaiDaMua(maHoaDon, daMuaStatus);
    }
    public long getThanhTien(long mhd) throws Exception {
    	return hDAO.getThanhTien(mhd);
    }
    public ArrayList<hoaDon> getDsHoaDonChuaMuaTheoMaKh(long makh) throws Exception {
    	return hDAO.getDsHoaDonChuaMuaTheoMaKh(makh);
    }
    public ArrayList<hoaDon> getDsHoaDonDaMuaTheoMaKh(long makh) throws Exception {
    	return hDAO.getDsHoaDonDaMuaTheoMaKh(makh);
    }
    public boolean capNhatThongTinNguoiNhan(long maHoaDon, String tenNguoiNhan,String diaChiGiaoHang, String soDienThoai) throws Exception {
    	return hDAO.capNhatThongTinNguoiNhan(maHoaDon, tenNguoiNhan, diaChiGiaoHang, soDienThoai);
    }
    public int demSoDonHomNay() throws Exception {
    	return hDAO.demSoDonHomNay();
    }
    public int demSoDonThangNay() throws Exception{
    	return hDAO.demSoDonThangNay();
    } 
}
