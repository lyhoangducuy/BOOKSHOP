package bo;

import java.util.ArrayList;

import dao.loaiDAO;
import model.loai;
import model.sach;

public class loaiBO {
	loaiDAO ldao=new loaiDAO();
	ArrayList<loai> ds;

    // Lấy tất cả sách
    public ArrayList<loai> getLoai() throws Exception{
        if(ds == null)
            ds = ldao.getLoai(); // GÁN VÀ LƯU lại trong ds
        return ds;
    }
    public int updateLoai(loai l) throws Exception {
    	return ldao.updateLoai(l);
    }
    public int deleteLoai(String maloai) throws Exception {
    	return ldao.deleteLoai(maloai);
    }

	public loai getTenLoai(String maloai) throws Exception{
		return ldao.getTenLoai(maloai);
	}
	public int insertLoai(loai l) throws Exception {
	    return ldao.insertLoai(l);
	}
	public ArrayList<loai> getLoaiByTenLoai(String key) throws Exception{
		return ldao.getLoaiByTenLoai(key);
	}
	public ArrayList<loai> getLoaiPhanTrang(int n) throws Exception{
		return ldao.getLoaiPhanTrang(n);
	}
	public ArrayList<loai> getLoaiByTenLoaiTheoTrang(String key,int n) throws Exception{
		return ldao.getLoaiByTenLoaiTheoTrang(key, n);
	}
	public int getTotalLoaiTheoTen(String key) throws Exception {
		return ldao.getTotalLoaiTheoTen(key);
	}
	public int getTotalLoai() throws Exception {
		return ldao.getTotalLoai();
	}
}
