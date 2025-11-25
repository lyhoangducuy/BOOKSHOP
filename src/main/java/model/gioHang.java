package model;

public class gioHang {
	private String maSach;
	private String tenSach;
	private String anh;
	private int soLuong;
	private long gia;
	private long thanhTien;
	public gioHang(String maSach, String tenSach, String anh, int soluong, long gia) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.anh = anh;
		this.soLuong = soluong;
		this.gia=gia;
		this.thanhTien=this.soLuong*this.gia;
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public int getSoluong() {
		return soLuong;
	}
	public void setSoluong(int soluong) {
		soLuong = soluong;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public long getThanhTien() {
		return  this.soLuong * this.gia;
	}
	
	
}
