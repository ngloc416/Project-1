
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
public class TinhPre_Post {

    public TinhPre_Post() {
    }

    Xulyxau xulyxau = new Xulyxau();

    private double tinh(char c, double m, double n) {
        double t = 0.0;
        switch (c) {
            case '+' ->
                t = m + n;
            case '-' ->
                t = m - n;
            case '*' ->
                t = m * n;
            case '/' ->
                t = m / n;
            default -> {
            }
        }
        return t;
    }

    public double tinhHauTo(String[] s) {
        double out;
        Stack S = new Stack();

        for (String item : s) {             //xet theo thu tu tu dau xuong cuoi
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {   //neu la toan hang thi chuyen thanh double roi dua vao stack
                double d = Double.parseDouble(item);
                S.push(d);
            } else {                        //neu la toan tu thi lay 2 ptu o dau stack de tinh toan 
                double m, n;
                n = (double) S.peek();
                S.pop();
                m = (double) S.peek();
                S.pop();
                S.push(tinh(c, m, n)); //ket qua tinh cho vao stack              
            }
        }
        out = (double) S.peek();
        S.pop();
        return out;
    }

    public double tinhTienTo(String[] s) {
        double out;
        Stack S = new Stack();

        for (int i = s.length - 1; i >= 0; i--) {   //xet theo thu tu tu cuoi len dau
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {   //neu la toan hang thi chuyen thanh double roi dua vao stack
                double d = Double.parseDouble(s[i]);
                S.push(d);
            } else {                        //neu la toan tu thi lay 2 ptu o dau stack de tinh toan  
                double m, n;
                m = (double) S.peek();
                S.pop();
                n = (double) S.peek();
                S.pop();
                S.push(tinh(c, m, n)); //ket qua tinh cho vao Sdouble                
            }
        }
        out = (double) S.peek();
        S.pop();
        return out;
    }
//phần tính trung tố cũ
    /*public double tinhTrungTo(String[] s) {
        double out;
        Stack Schar = new Stack();
        Stack Sdouble = new Stack();

        for (String item : s) {             //xet theo thu tu tu dau xuong cuoi
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {   //neu la toan hang thi chuyen thanh double roi cho vao Sdouble
                double d = Double.parseDouble(item);
                Sdouble.push(d);
            } else if (c == '(') {          //neu la ( thi cho vao Schar
                Schar.push(c);
            } else if (c == ')') { //neu la ) thi lay toan tu trong Schar thuc hien tinh toan voi 2 toan hang trong Sdouble cho den khi gap (
                char c1;
                do {
                    c1 = (char) Schar.peek();
                    if (c1 != '(') {
                        double m, n;
                        n = (double) Sdouble.peek();
                        Sdouble.pop();
                        m = (double) Sdouble.peek();
                        Sdouble.pop();
                        Sdouble.push(tinh(c1, m, n));   //ket qua tinh cho vao Sdouble
                    }
                    Schar.pop();
                } while (c1 != '(');
            } else {                        //neu la toan tu 
                while (!Schar.isEmpty() && xulyxau.priority((char) Schar.peek()) >= xulyxau.priority(c)) {
                    double m, n;            //neu toan tu trong Schar co do uu tien >= toan tu dang xet thi lay toan tu trong Schar de tinh toan
                    n = (double) Sdouble.peek();
                    Sdouble.pop();
                    m = (double) Sdouble.peek();
                    Sdouble.pop();
                    Sdouble.push(tinh((char) Schar.peek(), m, n)); //ket qua tinh cho vao Sdouble
                    Schar.pop();
                }
                Schar.push(c);                 //dua toan tu dang xet vao Schar
            }
        }

        while (!Schar.isEmpty()) {                //neu Schar con phan tu thi lan luot lay ra de tinh toan
            double m, n;
            n = (double) Sdouble.peek();
            Sdouble.pop();
            m = (double) Sdouble.peek();
            Sdouble.pop();
            Sdouble.push(tinh((char) Schar.peek(), m, n));
            Schar.pop();
        }
        out = (double) Sdouble.peek();
        Sdouble.pop();
        return out;
    }*/
}
