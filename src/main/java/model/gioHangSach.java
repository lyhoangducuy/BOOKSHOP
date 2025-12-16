package model;

public class gioHangSach {
    private long maGioHang;
    private String maSach;
    private int soLuong;
    private long donGia;

    public gioHangSach() {
    }

    public gioHangSach(long maGioHang, String maSach, int soLuong, long donGia) {
        this.maGioHang = maGioHang;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public long getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(long maGioHang) {
        this.maGioHang = maGioHang;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    // Tiện cho hiển thị
    public long getThanhTien() {
        return soLuong * donGia;
    }
}
