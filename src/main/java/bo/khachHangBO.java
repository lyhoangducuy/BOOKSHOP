package bo;

import dao.khachHangDAO;
import model.khachHang;

public class khachHangBO {
	khachHangDAO khDAO=new khachHangDAO();
	public khachHang kiemTraDangNhap(String tendn, String matKhau) throws Exception{
		return khDAO.kiemTraDangNhap(tendn, matKhau);
	}
	public boolean taoKhachHang(String tendn, String hoten, String diachi, String sodt, String email, String pass) {
		return khDAO.taoKhachHang(tendn, hoten, diachi, sodt, email, pass);
	}
}
