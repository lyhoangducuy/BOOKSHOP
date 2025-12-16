package bo;

import java.util.ArrayList;

import dao.sachDAO;
import model.sach;

public class sachBO {

    sachDAO dao = new sachDAO();

    // Lấy tất cả sách
    public ArrayList<sach> getTatCaSach() throws Exception {
        return dao.getSach();
    }

    // Phân trang không tìm kiếm
    public ArrayList<sach> getSachTheoTrang(int trang) throws Exception {
        return dao.getSachTheoTrang(trang);
    }

    public int getTotalSach() throws Exception {
        return dao.getTotalSach();
    }

    // Tìm kiếm theo tên (full)
    public ArrayList<sach> timKiemSach(String keyword,int trang) throws Exception {
        return dao.getSachTheoTen(keyword, trang); // lấy hết
    }

    // Tìm kiếm theo tên + phân trang
    public ArrayList<sach> timKiemSachTheoTrang(String keyword,int trang) throws Exception {
        return dao.getSachTheoTen(keyword, trang);
    }

    public int getTotalSachTheoTen(String keyword) throws Exception {
        return dao.getTotalSachTheoTen(keyword);
    }
    public int getTotalSachTheoLoai(String maloai) throws Exception {
    	return dao.getTotalSachTheoLoai(maloai);
    }
    public ArrayList<sach> getSachTheoLoai(String maloai, int trang) throws Exception {
    	return dao.getSachTheoLoai(maloai, trang);
    }
    public ArrayList<sach> getSachTheoTen(String tenNhap, int trang) throws Exception {
    	return dao.getSachTheoTen(tenNhap, trang);
    }
    // CRUD
    public sach getSachTheoMa(String ma) throws Exception {
        return dao.getSachTheoMa(ma);
    }

    public int insertSach(sach s) throws Exception {
        return dao.insertSach(s);
    }

    public int updateSach(sach s) throws Exception {
        return dao.updateSach(s);
    }

    public int deleteSach(String ma) throws Exception {
        return dao.deleteSach(ma);
    }
    public ArrayList<sach> getSachTheoTen(String ten) throws Exception {
    	return dao.getSachTheoTen(ten);
    }
    public int tangSoLuong(String maSach, int n) throws Exception {
    	return dao.tangSoLuong(maSach, n);
    }
    public int giamSoLuong(String maSach, int n) throws Exception {
    	return dao.giamSoLuong(maSach, n);
    }
    public int capNhatTonKho(String maSach, int delta) throws Exception {
    	return dao.capNhatTonKho(maSach, delta);
    }
}
