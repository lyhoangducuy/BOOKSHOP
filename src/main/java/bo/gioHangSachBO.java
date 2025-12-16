package bo;

import java.util.ArrayList;

import dao.gioHangSachDAO;
import model.gioHangSach;

public class gioHangSachBO {
	gioHangSachDAO gDAO=new gioHangSachDAO();
	 public gioHangSach kiemTraTonTai(long maGioHang, String maSach) throws Exception {
		 return gDAO.kiemTraTonTai(maGioHang, maSach);
	 }
	 public int tangSoLuong(long maGioHang, String maSach, int soLuongThem) throws Exception {
		 return gDAO.giamSoLuong(maGioHang, maSach, soLuongThem);
	 }
	 public int giamSoLuong(long maGioHang, String maSach, int soLuongGiam) throws Exception {
		 return gDAO.giamSoLuong(maGioHang, maSach, soLuongGiam);
	 }
	 public void capNhatSoLuong(long maGioHang, String maSach, int soLuongMoi) throws Exception {
		  gDAO.capNhatSoLuong(maGioHang, maSach, soLuongMoi);
	 }
	 public ArrayList<gioHangSach> getGioHangSachTheoMaGioHang(long maGioHang) throws Exception {
		 return gDAO.getGioHangSachTheoMaGioHang(maGioHang);
	 } 
	 public long tongThanhTien(long maGioHang) throws Exception {
		 return gDAO.tongThanhTien(maGioHang);
	 }
	 public int xoaGioHangSach(long maGioHang, String maSach) throws Exception {
		 return gDAO.xoaGioHangSach(maGioHang, maSach);
	 }
	 public int themGioHangSach(long maGioHang, gioHangSach ghs) throws Exception {
		 return gDAO.themGioHangSach(maGioHang, ghs);
	 }
	 public int xoaAll(long maGioHang) throws Exception {
		 return gDAO.xoaAll(maGioHang);
	 }
}
