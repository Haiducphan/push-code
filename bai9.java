import java.util.*;

class TaiKhoan {
    String soTK, tenChuTK;
    double soDu;
    static double laiSuat = 0.05; // 5%/năm mặc định

    TaiKhoan(String so, String ten, double du) {
        soTK = so;
        tenChuTK = ten;
        soDu = du;
    }

    void napTien(double tien) {
        if (tien > 0) soDu += tien;
    }

    void rutTien(double tien) {
        if (tien > 0 && tien <= soDu) soDu -= tien;
    }

    void tinhLaiThang() {
        soDu += soDu * (laiSuat / 12);
    }

    void hienThi() {
        System.out.println("Số TK: " + soTK +
                ", Chủ TK: " + tenChuTK +
                ", Số dư: " + soDu);
    }

    static void doiLaiSuat(double ls) {
        laiSuat = ls;
    }

    static void hienThiLaiSuat() {
        System.out.println("Lãi suất hiện tại: " + (laiSuat * 100) + "%/năm");
    }
}

public class bai9 {
    public static void main(String[] args) {
        ArrayList<TaiKhoan> ds = new ArrayList<>();

        // Tạo danh sách tài khoản
        ds.add(new TaiKhoan("1001", "Nguyễn Văn A", 5000));
        ds.add(new TaiKhoan("1002", "Trần Thị B", 8000));
        ds.add(new TaiKhoan("1003", "Lê Văn C", 3000));

        // Thực hiện giao dịch
        ds.get(0).napTien(2000);   // A nạp thêm 2000
        ds.get(1).rutTien(1000);   // B rút 1000
        ds.get(2).tinhLaiThang();  // C tính lãi 1 tháng

        // Đổi lãi suất
        TaiKhoan.doiLaiSuat(0.06); // 6%/năm
        TaiKhoan.hienThiLaiSuat();

        // Hiển thị danh sách sau khi xử lý
        System.out.println("\nDanh sách tài khoản:");
        for (TaiKhoan tk : ds) {
            tk.hienThi();
        }

        // Tìm kiếm theo số tài khoản
        String tim = "1002";
        System.out.println("\nTìm kiếm số TK " + tim + ":");
        for (TaiKhoan tk : ds) {
            if (tk.soTK.equals(tim)) {
                tk.hienThi();
            }
        }

        // Sắp xếp theo số dư giảm dần
        ds.sort((a, b) -> Double.compare(b.soDu, a.soDu));
        System.out.println("\nDanh sách sau khi sắp xếp theo số dư giảm dần:");
        for (TaiKhoan tk : ds) {
            tk.hienThi();
        }
    }
}
