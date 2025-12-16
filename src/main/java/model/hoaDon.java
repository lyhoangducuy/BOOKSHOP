package model;

import java.time.LocalDate;

public class hoaDon {
    private long maHoaDon;
    private long makh;
    private LocalDate ngayMua;
    private boolean daMua;
    private String tenNguoiNhan;
    private String diaChiGiaoHang;
    private String soDienThoai;
    private phuongThucThanhToan ptt;

    public hoaDon(long maHoaDon, long makh, LocalDate ngayMua, boolean daMua,
                  String tenNguoiNhan, String diaChiGiaoHang, String soDienThoai,phuongThucThanhToan ptt) {
        this.maHoaDon = maHoaDon;
        this.makh = makh;
        this.ngayMua = ngayMua;
        this.daMua = daMua;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.soDienThoai = soDienThoai;
        this.ptt=ptt;
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

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

	public phuongThucThanhToan getPtt() {
		return ptt;
	}

	public void setPtt(phuongThucThanhToan ptt) {
		this.ptt = ptt;
	}

    
}
