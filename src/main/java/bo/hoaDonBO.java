package bo;

import java.util.ArrayList;

import dao.hoaDonDAO;
import model.hoaDon;

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



    public hoaDon themHoaDon(long makh) throws Exception {
        return hDAO.themHoaDon(makh);
    }
    public void xoaHoaDonTheoMaHoaDOn(long maHoaDon,boolean daMua) throws Exception {
    	hDAO.xoaHoaDonTheoMaHoaDon(maHoaDon,daMua);
    }
}
