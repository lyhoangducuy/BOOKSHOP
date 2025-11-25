package model;

public class chiTietHoaDon {
	private String maChiTietHoaDon;
	private String maSach;
	private int soLuongMua;
	private long maHoaDon;
	private boolean daMua;
	public chiTietHoaDon(String maChiTietHoaDon,String maSach, int soLuongMua, long maHoaDon, boolean daMua) {
		super();
		this.maChiTietHoaDon=maChiTietHoaDon;
		this.maSach = maSach;
		this.soLuongMua = soLuongMua;
		this.maHoaDon = maHoaDon;
		this.daMua = daMua;
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public int getSoLuongMua() {
		return soLuongMua;
	}
	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}
	public long getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public boolean isDaMua() {
		return daMua;
	}
	public void setDaMua(boolean daMua) {
		this.daMua = daMua;
	}
	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}
	public void setMaChiTietHoaDon(String maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	
	
}
