import java.util.Scanner;

class HinhChuNhat {
    double dai, rong;

    HinhChuNhat(double d, double r) {
        dai = d;
        rong = r;
    }

    double chuVi() {
        return 2 * (dai + rong);
    }

    double dienTich() {
        return dai * rong;
    }

    void hienThi() {
        System.out.println("Chiều dài: " + dai +
                ", Chiều rộng: " + rong +
                ", Chu vi: " + chuVi() +
                ", Diện tích: " + dienTich());
    }
}

public class bai7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập 1 hình chữ nhật từ bàn phím
        System.out.print("Nhập chiều dài: ");
        double d = sc.nextDouble();
        System.out.print("Nhập chiều rộng: ");
        double r = sc.nextDouble();

        HinhChuNhat hcnNhap = new HinhChuNhat(d, r);
        System.out.println("\n Hình chữ nhật do bạn nhập:");
        hcnNhap.hienThi();

        // Tạo thêm một số hình chữ nhật khác nhau
        HinhChuNhat h1 = new HinhChuNhat(5, 3);
        HinhChuNhat h2 = new HinhChuNhat(7, 4);
        HinhChuNhat h3 = new HinhChuNhat(10, 2);

        System.out.println("\n Một số hình chữ nhật khác:");
        h1.hienThi();
        h2.hienThi();
        h3.hienThi();

        sc.close();
    }
}
