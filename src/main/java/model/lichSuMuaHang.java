package model;

public class lichSuMuaHang {
	private String tensach;
	private Long gia;
	private Long soLuongMua;
	private Long TT;
	private Boolean damua;
	private Long makh;
	public lichSuMuaHang() {
	   super();
	   // TODO Auto-generated constructor stub
	}
	public lichSuMuaHang(String tensach, Long gia, Long soLuongMua, Long tT, Boolean damua, Long makh) {
	   super();
	   this.tensach = tensach;
	   this.gia = gia;
	   this.soLuongMua = soLuongMua;
	   this.TT = tT;
	   this.damua = damua;
	   this.makh = makh;
	}
	public String getTensach() {
	   return tensach;
	}
	public void setTensach(String tensach) {
	   this.tensach = tensach;
	}
	public Long getGia() {
	   return gia;
	}
	public void setGia(Long gia) {
	   this.gia = gia;
	}
	public Long getSoLuongMua() {
	   return soLuongMua;
	}
	public void setSoLuongMua(Long soLuongMua) {
		this.soLuongMua = soLuongMua;
	}
	public Long getTT() {
	   return TT;
	}
	public void setTT(Long tT) {
	   this.TT = tT;
	}
	public Boolean getDamua() {
	   return damua;
	}
	public void setDamua(Boolean damua) {
	   this.damua = damua;
	}
	public Long getMakh() {
	   return makh;
	}
	public void setMakh(Long makh) {
	   this.makh = makh;
	}
}
