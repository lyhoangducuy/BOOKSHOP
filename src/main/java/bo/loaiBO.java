package bo;

import java.util.ArrayList;

import dao.loaiDAO;
import model.loai;

public class loaiBO {
	loaiDAO ldao=new loaiDAO();
	public ArrayList<loai> getLoai() throws Exception{
		return ldao.getLoai();
	}
}
