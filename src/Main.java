
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

    public static void main(String[] args) {

        Xulyxau xulyxau = new Xulyxau();
        Trans trans = new Trans();
        Scanner sc_int = new Scanner(System.in);
        Scanner sc_str = new Scanner(System.in);

        String s, s1[] = null, s2[] = null;

        System.out.println("Chọn công việc bạn muốn thực hiện: ");
        System.out.println("1. Chuyển biểu thức dạng trung tố sang tiền tố và hậu tố (nhấn phím 1)");
        System.out.println("2. Chuyển biểu thức dạng tiền tố  sang trung tố và hậu tố (nhấn phím 2)");
        System.out.println("3. Chuyển biểu thức dạng hậu tố sang tiền tố và trung tố (nhấn phím 3)");
        System.out.println("4. Tính giá trị biểu thức (nhấn phím 4)");

        int cviec = sc_int.nextInt();
        System.out.print("Nhập biểu thức: ");
        s = sc_str.nextLine();
        s1 = xulyxau.processingString(s);

        if (cviec == 1) {
            s2 = trans.inToPost(s1);
            System.out.print("  Dạng hậu tố là:");
            for (String item : s2) {
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }
}
