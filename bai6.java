import java.util.Scanner;

public class bai6 {
    static class Rectangle {
        private int width;
        private int height;

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getPerimeter() {
            return 2 * (width + height);
        }

        public int getArea() {
            return width * height;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int width = sc.nextInt();
        int height = sc.nextInt();
        System.out.print("Nhập chiều dài: ");
        System.out.print("Nhập chiều rộng: ");

        if (width <= 0 || height <= 0) {
            System.out.println("Chiều dài và chiều rộng phải lớn hơn 0!");
        } else {
            Rectangle rect = new Rectangle(width, height);
            System.out.println("Chu vi hình chữ nhật: " + rect.getPerimeter());
            System.out.println("Diện tích hình chữ nhật: " + rect.getArea());

        }
        sc.close();

    }
}