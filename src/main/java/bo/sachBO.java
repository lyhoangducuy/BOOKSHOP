package bo;

import java.util.ArrayList;

import dao.sachDAO;
import model.sach;

public class sachBO {
    sachDAO sdao = new sachDAO();
    ArrayList<sach> ds; // cache tất cả sách

    // Lấy tất cả sách
    public ArrayList<sach> getSach() throws Exception {
        if (ds == null)
            ds = sdao.getSach(); // lưu cache
        return ds;
    }

 // Theo loại phân trang
    public ArrayList<sach> getSachTheoLoai(String maLoai, int trang, int pageSize) throws Exception {
        return sdao.getSachTheoLoai(maLoai, trang, pageSize);
    }
    public int getTotalSachTheoLoai(String maLoai) throws Exception {
        return sdao.getTotalSachTheoLoai(maLoai);
    }

    // Theo tên phân trang
    public ArrayList<sach> getSachTheoTen(String tenNhap, int trang, int pageSize) throws Exception {
        return sdao.getSachTheoTen(tenNhap, trang, pageSize);
    }
    public int getTotalSachTheoTen(String tenNhap) throws Exception {
        return sdao.getTotalSachTheoTen(tenNhap);
    }



    // Tìm sách theo mã
    public sach getSachTheoMa(String masach) throws Exception {
        return sdao.getSachTheoMa(masach);
    }

    // --- Mới: Lấy sách theo phân trang ---
    public ArrayList<sach> getSachTheoTrang(int trang, int soLuongMoiTrang) throws Exception {
        // sdao.getSachTheoTrang() hiện tại fix 6 sách mỗi trang, mình sẽ tạo động
        ArrayList<sach> dsTrang = new ArrayList<>();
        int offset = soLuongMoiTrang * (trang - 1);

        for (sach s : getSach()) { // lấy từ cache ds
            if (ds.indexOf(s) >= offset && ds.indexOf(s) < offset + soLuongMoiTrang) {
                dsTrang.add(s);
            }
        }
        return dsTrang;
    }

    // Tổng số sách (để tính số trang)
    public int getTotalSach() throws Exception {
        return sdao.getTotalSach();
    }
}
