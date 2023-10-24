package fpoly.truongtqph41980.duanmau.model;

public class thanhVien {
    private int maTV;
    private String hoTen;
    private String namSinh;
    private int sotaikhoan;

    public int getSotaikhoan() {
        return sotaikhoan;
    }

    public thanhVien(int maTV, String hoTen, String namSinh, int sotaikhoan) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.sotaikhoan = sotaikhoan;
    }

    public void setSotaikhoan(int sotaikhoan) {
        this.sotaikhoan = sotaikhoan;
    }

    public thanhVien() {
    }

    public thanhVien(int maTV, String hoTen, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public thanhVien(String hoTen, String namSinh, int sotaikhoan) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.sotaikhoan = sotaikhoan;
    }

    public thanhVien(String hoTen, String namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
