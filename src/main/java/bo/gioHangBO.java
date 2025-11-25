package bo;

import java.util.ArrayList;

import model.gioHang;
import model.sach;

public class gioHangBO {
	ArrayList<gioHang> ds=new ArrayList<>();
	public ArrayList<gioHang> getGioHang(){
		return ds;
	}
	public void Xoa(String masach) {
        ds.removeIf(g -> g.getMaSach().equalsIgnoreCase(masach));
    }
	public void themVaoGioHang(String masach, String tensach, String anh, int soluong, long gia) throws Exception {
	    for (gioHang g : ds) {
	        if (g.getMaSach().equals(masach)) {
	        	if (soluong <= 0) { // Nếu sau cộng lại vẫn <=0 thì xóa luôn
	                Xoa(masach);
	                return;
	            }
	            g.setSoluong(g.getSoluong() + soluong);
	            return; // đã tăng số lượng, không thêm mới
	        }
	    }
	    // Nếu không tìm thấy sách trùng, thêm mới
	    ds.add(new gioHang(masach, tensach, anh, 1, gia));
	}
	public long tongThanhTien() {
		long tong=0;
		getGioHang();
		for (gioHang g:ds)
		{
			tong+=g.getThanhTien();
		}
		return tong;
	}
}
