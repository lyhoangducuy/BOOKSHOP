package bo;

import java.util.ArrayList;

import dao.sachDAO;
import model.sach;

public class sachBO {
	ArrayList<sach> ds;
	sachDAO sdao=new sachDAO();
	public ArrayList<sach> getSachTheoTen(String tenNhap)throws Exception{
		return sdao.getSachTheoTen(tenNhap);
	}
	public ArrayList<sach> getSachTheoLoai(String maLoai)throws Exception{
		return sdao.getSachTheoLoai(maLoai);
	}
	public ArrayList<sach> getSach() throws Exception{
		return sdao.getSach();
	}
}
