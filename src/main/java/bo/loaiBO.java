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

}
