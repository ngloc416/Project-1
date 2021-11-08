
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loc Nguyen
 */
public class Trans {

    public Trans() {
    }

    public String[] inToPost(String[] s) {      //trung to thanh hau to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (String item : s) {             //xet theo thu tu tu dau xuong cuoi
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {   //neu la toan hang thi cho vao xau
                s1 = s1 + " " + item;
            } else if (c == '(') {          //neu la ( thi cho vao stack
                S.push(item);
            } else if (c == ')') {          //neu la ) thi dua cac phan tu trong stack ra xau den khi gap (
                char c1;
                do {
                    c1 = S.peek().charAt(0);
                    if (c1 != '(') {
                        s1 = s1 + " " + S.peek();
                    }
                    S.pop();
                } while (c1 != '(');
            } else {                        //neu la toan tu 
                while (!S.isEmpty() && xulyxau.priority(S.peek().charAt(0)) >= xulyxau.priority(c)) {
                    s1 = s1 + " " + S.peek(); //neu toan tu trong stack co do uu tien >= toan tu dang xet thi dua ra xau
                    S.pop();
                }
                S.push(item);                 //dua toan tu dang xet vao stack
            }
        }

        while (!S.isEmpty()) {                //neu stack con phan tu thi dua het ra xau
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        s1 = s1.trim();
        out = s1.split(" ");
        return out;
    }

    public String[] inToPre(String[] s) {       //trung to thanh tien to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (int i = s.length - 1; i >= 0; i--) {   //xet theo thu tu tu cuoi len dau
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {           //neu la toan hang thi dua ra xau
                s1 = s1 + " " + s[i];
            } else if (c == ')') {                  //neu la ) thi cho vao stack
                S.push(s[i]);
            } else if (c == '(') {                  //neu la ( thi dua cac ptu trong stack ra xau den khi gap )
                char c1;
                do {
                    c1 = S.peek().charAt(0);
                    if (c1 != ')') {
                        s1 = s1 + " " + S.peek();
                    }
                    S.pop();
                } while (c1 != ')');
            } else {                                //neu la toan tu
                while (!S.isEmpty() && xulyxau.priority(S.peek().charAt(0)) > xulyxau.priority(c)) {
                    s1 = s1 + " " + S.peek();       //neu toan tu o dau stack co do uu tien > toan tu dang xet thi dua ra xau
                    S.pop();
                }
                S.push(s[i]);                       //dua toan tu dang xet vao stack
            }
        }
        while (!S.isEmpty()) {                      //neu stack != rong thi dua het cac ptu ra xau
            s1 = s1 + " " + S.peek();
            S.pop();
        }

        s1 = s1.trim();
        String str2 = new StringBuffer(s1).reverse().toString();        //dao nguoc lai xau nhan duoc
        out = str2.split(" ");
        return out;
    }

    public String[] postToPre(String[] s) {         //hau to thanh tien to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (String item : s) {             //xet theo thu tu tu dau xuong cuoi
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {   //neu la toan hang thi dua vao stack
                S.push(item);
            } else {                        //neu la toan tu thi lay 2 ptu o dau stack cho vao xau theo thu tu toan tu + op2 + op1 
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push(item + " " + op2 + " " + op1);
            }
        }

        s1 = S.peek();
        S.pop();

        if (!S.isEmpty()) {
            s1 = null;        //neu stack != rong thi bieu thuc sai
        }
        out = s1.split(" ");
        return out;
    }

    public String[] postToIn(String[] s) {      //hau to thanh trung to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (String item : s) {             //xet theo thu tu tu dau xuong cuoi
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {   //neu la toan hang thi dua vao stack
                S.push(item);
            } else {            //neu la toan tu thi dua 2 ptu o dau stack ra xau theo thu tu op2 + toan tu + op1
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push("(" + op2 + " " + item + " " + op1 + ")");
            }
        }
        s1 = S.peek();
        S.pop();

        if (!S.isEmpty()) {
            s1 = null;        //neu stack != thi bieu thuc sai
        }

        if (s1.length() > 1) {
            s1 = s1.substring(1, s1.length() - 1);  //bo dau ngoac thua o dau va cuoi xau
        }
        out = s1.split(" ");
        return out;
    }

    public String[] preToIn(String[] s) {       //tien to thanh trung to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (int i = s.length - 1; i >= 0; i--) {   //xet theo thu tu tu cuoi len dau
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {           //neu la toan hang thi dua vao stack
                S.push(s[i]);
            } else {        //neu la toan tu thi dua 2 ptu o dau stack vao xau theo thu tu op1 + toan tu + op2
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push("(" + op1 + " " + s[i] + " " + op2 + ")");
            }
        }

        s1 = S.peek();
        S.pop();

        if (!S.isEmpty()) {
            s1 = null;        //neu stack != rong thi bieu thuc sai
        }

        if (s1.length() > 1) {
            s1 = s1.substring(1, s1.length() - 1);  //bo dau ngoac thua o dau va cuoi xau
        }
        out = s1.split(" ");
        return out;
    }

    public String[] preToPost(String[] s) {         //tien to thanh hau to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (int i = s.length - 1; i >= 0; i--) {   //xet theo thu tu tu cuoi len dau
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {           //neu la toan hang thi dua vao stack
                S.push(s[i]);
            } else {    //neu la toan tu thi dua 2 ptu o dau stack ra xau theo thu tu op1+op2+toan tu
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push(op1 + " " + op2 + " " + s[i]);
            }
        }

        s1 = S.peek();
        S.pop();

        if (!S.isEmpty()) {
            s1 = null;        //neu stack != rong thi bieu thuc sai
        }
        out = s1.split(" ");
        return out;
    }
}
