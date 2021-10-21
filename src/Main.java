
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
        System.out.println("___Chọn công việc bạn muốn thực hiện: ");
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
        Scanner sc_int = new Scanner(System.in);
        Scanner sc_str = new Scanner(System.in);
        String s, s1[] = null, s2[] = null;
        int cviec = 0;

        while (cviec != 5 && cviec != 7) {

            menu();
            cviec = sc_int.nextInt();
            while ((cviec < 1) || (cviec > 5)) {
                System.out.println("___Nhập các giá trị từ 1 - 5: ");
                cviec = sc_int.nextInt();
            }

            if (cviec == 5) {
                return;
            }

            System.out.print("___Nhập biểu thức: ");
            s = sc_str.nextLine();
            s = xulyxau.processingString(s);

            if (cviec == 1) {
                s1 = trans.inToPre(s);
                s2 = trans.inToPost(s);
                System.out.print("Dạng tiền tố là: ");
                print(s1);
                System.out.print("Dạng hậu tố là: ");
                print(s2);
            } else if (cviec == 2){
                
            } else if (cviec == 3){
                s1 = trans.postToPre(s);
                s2 = trans.postToIn(s);
                System.out.print("Dạng tiền tố là: ");
                print(s1);
                System.out.print("Dạng trung tố là: ");
                print(s2);
            }

            System.out.println("___Bạn có muốn tiếp tục sử dụng chương trình không? 6: Yes    7: No");
            cviec = sc_int.nextInt();
            while (cviec != 6 && cviec != 7) {
                System.out.println("___Nhập các giá trị 6 hoặc 7: ");
                cviec = sc_int.nextInt();
            }
        }
    }
}
