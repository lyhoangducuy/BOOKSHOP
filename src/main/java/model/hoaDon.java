package model;

import java.time.LocalDate;

public class hoaDon {
	private long maHoaDon;
	private long makh;
	private LocalDate ngayMua;
	private boolean daMua;
	public hoaDon(long maHoaDon,long makh, LocalDate ngayMua, boolean daMua) {
		super();
		this.maHoaDon=maHoaDon;
		this.makh = makh;
		this.ngayMua = ngayMua;
		this.daMua = daMua;
	}
	
	public long getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public long getMakh() {
		return makh;
	}
	public void setMakh(long makh) {
		this.makh = makh;
	}
	public LocalDate getNgayMua() {
		return ngayMua;
	}
	public void setNgayMua(LocalDate ngayMua) {
		this.ngayMua = ngayMua;
	}
	public boolean isDaMua() {
		return daMua;
	}
	public void setDaMua(boolean daMua) {
		this.daMua = daMua;
	}
	
}
