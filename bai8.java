class SinhVien {
    String maSV, hoTen;
    int tuoi;
    static int tongSV = 0;

    SinhVien(String ma, String ten, int t) {
        maSV = ma;
        hoTen = ten;
        tuoi = t;
        tongSV++;
    }

    void hienThi() {
        System.out.println("Mã SV: " + maSV +
                ", Họ tên: " + hoTen +
                ", Tuổi: " + tuoi);
    }

    static void hienThiTongSV() {
        System.out.println("Tổng số sinh viên: " + tongSV);
    }
}

public class bai8 {
    public static void main(String[] args) {
        SinhVien sv1 = new SinhVien("SV01", "Nguyễn Văn A", 20);
        SinhVien sv2 = new SinhVien("SV02", "Trần Thị B", 21);
        SinhVien sv3 = new SinhVien("SV03", "Lê Văn C", 19);

        sv1.hienThi();
        sv2.hienThi();
        sv3.hienThi();

        SinhVien.hienThiTongSV();
    }
}
