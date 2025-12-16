package bo;

import java.util.ArrayList;

import dao.chiTietHoaDonDAO;
import model.chiTietHoaDon;

public class chiTietHoaDonBO {
	chiTietHoaDonDAO cDAO= new chiTietHoaDonDAO();
	public ArrayList<chiTietHoaDon> getDsChiTietHoaDon() throws Exception{
		return cDAO.getDsChiTietHoaDon();
	}

	public void xoaChiTietHoaDonTheoMaHoaDon(long maHoaDon,boolean daMua) throws Exception {
		cDAO.xoaChiTietHoaDonTheoMaHoaDon(maHoaDon,daMua);
	}
	public ArrayList<chiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(long maHoaDon) throws Exception
	{
		return cDAO.timChiTietHoaDonTheoMaHoaDon(maHoaDon);
	}
	public void themChiTietHoaDon(long maHoaDon, String maSach, int soLuong) throws Exception {
		 cDAO.themChiTietHoaDon(maHoaDon, maSach, soLuong);
	}
	public int suaSoLuongSachHoaDon(long mahd,String masach, int sl) throws Exception {
		return cDAO.suaSoLuongSachHoaDon(mahd, masach, sl);
	}
	public void xoaSachChiTietHoaDon(long maHoaDon,String masach) throws Exception {
		cDAO.xoaSachChiTietHoaDon(maHoaDon, masach);
	}
	public chiTietHoaDon timChiTietHoaDon(long maHoaDon, String maSach) throws Exception {
		return cDAO.timChiTietHoaDon(maHoaDon, maSach);
	}
}
