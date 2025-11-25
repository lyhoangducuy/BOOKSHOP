package model;

import java.time.LocalDate;

public class sach {
	private String maSach;
	private String tenSach;
	private int soLuong;
	private long gia;
	private String maLoai;
	private int soTap;
	private String anh;
	private LocalDate ngayNhap;
	private String tacGia;
	public sach(String maSach, String tenSach, int soLuong, long gia, String maLoai, int soTap, String anh,
			LocalDate ngayNhap, String tacGia) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.gia = gia;
		this.maLoai = maLoai;
		this.soTap = soTap;
		this.anh = anh;
		this.ngayNhap = ngayNhap;
		this.tacGia = tacGia;
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
	public long getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public int getSoTap() {
		return soTap;
	}
	public void setSoTap(int soTap) {
		this.soTap = soTap;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public LocalDate getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	
}
