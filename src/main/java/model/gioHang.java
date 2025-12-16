package model;

import java.util.Date;

public class gioHang {
    private long maGioHang;
    private long maKH;
    private Date ngayTao;

    public gioHang() {
    }

    public gioHang(long maGioHang, long maKH, Date ngayTao) {
        this.maGioHang = maGioHang;
        this.maKH = maKH;
        this.ngayTao = ngayTao;
    }

    public long getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(long maGioHang) {
        this.maGioHang = maGioHang;
    }

    public long getMaKH() {
        return maKH;
    }

    public void setMaKH(long maKH) {
        this.maKH = maKH;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
