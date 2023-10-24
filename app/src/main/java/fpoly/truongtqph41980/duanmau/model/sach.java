package fpoly.truongtqph41980.duanmau.model;

public class sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int maLoai;
    private int soluongdamuon;
    private String tenLoaiS;
    private int soLuongSach;

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public sach(int maSach, String tenSach, int giaThue, int maLoai, String tenLoaiS, int soLuongSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.tenLoaiS = tenLoaiS;
        this.soLuongSach = soLuongSach;
    }

//    public sach(String tenSach, int giaThue, int maLoai, int soLuongSach) {
//        this.tenSach = tenSach;
//        this.giaThue = giaThue;
//        this.maLoai = maLoai;
//        this.soLuongSach = soLuongSach;
//    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public sach() {
    }

    public sach(int maSach, String tenSach, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

//    public sach(int maSach, String tenSach, int giaThue, int maLoai, int soLuongSach) {
//        this.maSach = maSach;
//        this.tenSach = tenSach;
//        this.giaThue = giaThue;
//        this.maLoai = maLoai;
//        this.soLuongSach = soLuongSach;
//    }
//    sc.MASACH, sc.TENSACH, sc.GIATHUE, sc.MALOAI,sc.SOLUONG
    public String getTenLoaiS() {
        return tenLoaiS;
    }

    public void setTenLoaiS(String tenLoaiS) {
        this.tenLoaiS = tenLoaiS;
    }

//    public sach(int maSach, String tenSach, int giaThue, int maLoai, String tenLoaiS) {
//        this.maSach = maSach;
//        this.tenSach = tenSach;
//        this.giaThue = giaThue;
//        this.maLoai = maLoai;
//        this.tenLoaiS = tenLoaiS;
//    }

    public sach(int maSach, String tenSach, int soluongdamuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soluongdamuon = soluongdamuon;
    }

    public int getSoluongdamuon() {
        return soluongdamuon;
    }

    public void setSoluongdamuon(int soluongdamuon) {
        this.soluongdamuon = soluongdamuon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
