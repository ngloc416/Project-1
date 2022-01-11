
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loc Nguyen
 */
public class Main {

    public static void menu() {
        System.out.println("__Chọn công việc bạn muốn thực hiện: ");
        System.out.println("1. Chuyển biểu thức dạng trung tố sang tiền tố và hậu tố (nhấn phím 1)");
        System.out.println("2. Chuyển biểu thức dạng tiền tố sang trung tố và hậu tố (nhấn phím 2)");
        System.out.println("3. Chuyển biểu thức dạng hậu tố sang tiền tố và trung tố (nhấn phím 3)");
        System.out.println("4. Tính giá trị biểu thức (nhấn phím 4)");
        System.out.println("5. Kết thúc chương trình (nhấn phím 5)");
    }

    public static void print(String[] s) {

        for (String item : s) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Xulyxau xulyxau = new Xulyxau();
        Trans trans = new Trans();
        TinhPre_Post tinhPre_Post = new TinhPre_Post();
        Scanner sc = new Scanner(System.in);

        String s, str[] = null, s1[] = null, s2[] = null;
        String cviec = "0";

        while (cviec.charAt(0) != '5') {
            System.out.println("___________________________________________");
            menu();                             //hien thi menu

            cviec = sc.nextLine();              //nhap cong viec
            while (("".equals(cviec)) || (cviec.length() > 1) || (cviec.charAt(0) < '1') || (cviec.charAt(0) > '5')) {
                System.out.println("....Nhập các giá trị từ 1 - 5: ");
                cviec = sc.nextLine();
            }
            System.out.println("___________________________________________");
            
            switch (cviec.charAt(0)) {          //thuc hien cong viec
                case '1' -> {
                    System.out.println("__Nhập biểu thức dạng trung tố: ");
                    s = sc.nextLine();
                    str = xulyxau.processingString(s);
                    while (!new CheckInfix(str).check()) {
                        System.out.println("....Biểu thức nhập vào sai! Vui lòng nhập lại: ");
                        s = sc.nextLine();
                        str = xulyxau.processingString(s);
                    }

                    s1 = trans.inToPre(str);
                    s2 = trans.inToPost(str);
                    System.out.print("=> Dạng tiền tố là: ");
                    print(s1);
                    System.out.print("=> Dạng hậu tố là: ");
                    print(s2);
                }
                case '2' -> {
                    System.out.println("__Nhập biểu thức dạng tiền tố: ");
                    s = sc.nextLine();
                    str = xulyxau.processingString(s);
                    boolean bl = true; //điều kiện để chạy vòng lặp while

                    while (bl) {
                        try {
                            s1 = trans.preToIn(str);
                            s2 = trans.preToPost(str);
                            System.out.print("=> Dạng trung tố là: ");
                            print(s1);
                            System.out.print("=> Dạng hậu tố là: ");
                            print(s2);
                            bl = false;
                        } catch (Exception e) {
                            System.out.println("....Biểu thức nhập vào sai! Vui lòng nhập lại: ");
                            s = sc.nextLine();
                            str = xulyxau.processingString(s);
                        }
                    }
                }
                case '3' -> {
                    System.out.println("__Nhập biểu thức dạng hậu tố: ");
                    s = sc.nextLine();
                    str = xulyxau.processingString(s);
                    boolean bl = true; //điều kiện để chạy vòng lặp while

                    while (bl) {
                        try {
                            s1 = trans.postToPre(str);
                            s2 = trans.postToIn(str);
                            System.out.print("=> Dạng tiền tố là: ");
                            print(s1);
                            System.out.print("=> Dạng trung tố là: ");
                            print(s2);
                            bl = false;
                        } catch (Exception e) {
                            System.out.println("....Biểu thức nhập vào sai! Vui lòng nhập lại: ");
                            s = sc.nextLine();
                            str = xulyxau.processingString(s);
                        }
                    }
                }
                case '4' -> {
                    System.out.println("__Nhập biểu thức số học (chỉ gồm các toán tử và chữ số): ");
                    s = sc.nextLine();
                    str = xulyxau.processingString(s);
                    while ((!xulyxau.check(s)) || (xulyxau.phan_loai(str) == 0)) {
                        if (!xulyxau.check(s)) {
                            System.out.println("....Nhập lại (chỉ gồm các toán tử và chữ số): ");
                        } else {
                            System.out.println("....Biểu thức nhập vào sai! Vui lòng nhập lại: ");
                        }
                        s = sc.nextLine();
                        str = xulyxau.processingString(s);
                    }

                    switch (xulyxau.phan_loai(str)) {
                        case 1 ->
                            System.out.println("=> Giá trị biểu thức là: " + tinhPre_Post.tinhTienTo(str));
                        case 2 ->
                            System.out.println("=> Giá trị biểu thức là: " + new TinhInfix(str).tinh());
                        case 3 ->
                            System.out.println("=> Giá trị biểu thức là: " + tinhPre_Post.tinhHauTo(str));
                        default -> {
                        }
                    }
                }
                case '5' -> {
                    return;
                }
            }
        }
    }
}
