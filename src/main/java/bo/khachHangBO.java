package bo;

import java.security.MessageDigest;

import dao.khachHangDAO;
import dao.vaiTroDAO;
import model.khachHang;
import model.vaiTro;

public class khachHangBO {
	khachHangDAO khDAO=new khachHangDAO();
	vaiTroDAO vDAO=new vaiTroDAO();
	public khachHang kiemTraDangNhap(String tendn, String matKhau) throws Exception{
		return khDAO.kiemTraDangNhap(tendn, matKhau);
	}
	public boolean taoKhachHang(String tendn, String hoten, String diachi, String sodt, String email, String pass) {
		return khDAO.taoKhachHang(tendn, hoten, diachi, sodt, email, pass);
	}
	public boolean taoVaiTro(String tendn, String matKhau, boolean quyen) {
		return vDAO.taoVaiTro(tendn, matKhau, quyen);
	}
	public vaiTro kiemTraDangNhapRole(String tendn, String matKhau) throws Exception {
		return vDAO.kiemTraDangNhap(tendn, matKhau);
	}
	public String md5(String input) throws Exception {
		return khDAO.md5(input);
	}
	
}
