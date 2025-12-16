package bo;

import java.util.ArrayList;

import dao.gioHangDAO;
import model.gioHang;

public class gioHangBO {
	gioHangDAO gDAO=new gioHangDAO();
	public gioHang taoGioHangValid(long maKH) throws Exception {
		return gDAO.taoGioHangValid(maKH);
	}
	public gioHang getGioHangTheoMaKH(long maKH) throws Exception {
		return gDAO.getGioHangTheoMaKH(maKH);
	}
    
    
}
