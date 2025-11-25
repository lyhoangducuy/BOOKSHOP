package bo;

import java.util.ArrayList;

import dao.lichSuMuaHangDAO;
import model.lichSuMuaHang;

public class lichSuMuaHangBO {
	lichSuMuaHangDAO lsdao= new lichSuMuaHangDAO();
	public ArrayList<lichSuMuaHang> getlichsu(Long makh) throws Exception{
	    return lsdao.getlichsu(makh);
	}
}
