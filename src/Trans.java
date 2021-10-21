
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

    public String[] inToPost(String s) { //chuyen trung to thanh hau to
        
        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", in[] = null, out[] = null;
        Stack<String> S = new Stack<>();
        
        in = s.split(" ");
        
        for (String item : in) {
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {
                s1 = s1 + " " + item;
            } else {
                if (c == '(') {
                    S.push(item);
                } else {
                    if (c == ')') {
                        char c1;
                        do {
                            c1 = S.peek().charAt(0);
                            if (c1 != '(') {
                                s1 = s1 + " " + S.peek();
                            }
                            S.pop();
                        } while (c1 != '(');
                    } else {
                        while (!S.isEmpty() && xulyxau.priority(S.peek().charAt(0)) >= xulyxau.priority(c)) {
                            s1 = s1 + " " + S.peek();
                            S.pop();
                        }
                        S.push(item);
                    }
                }
            }
        }
        while (!S.isEmpty()) {
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        
        s1 = s1.trim();
        out = s1.split(" ");
        return out;
    }

    public String[] inToPre(String s) { //chuyen trung to thanh tien to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", in[] = null, out[] = null;
        Stack<String> S = new Stack<>();
        
        String str1 = new StringBuffer(s).reverse().toString();
        in = str1.split(" ");
        
        for (String item : in) {
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {
                s1 = s1 + " " + item;
            } else {
                if (c == ')') {
                    S.push(item);
                } else {
                    if (c == '(') {
                        char c1;
                        do {
                            c1 = S.peek().charAt(0);
                            if (c1 != ')') {
                                s1 = s1 + " " + S.peek();
                            }
                            S.pop();
                        } while (c1 != ')');
                    } else {
                        while (!S.isEmpty() && xulyxau.priority(S.peek().charAt(0)) > xulyxau.priority(c)) {
                            s1 = s1 + " " + S.peek();
                            S.pop();
                        }
                        S.push(item);
                    }
                }
            }
        }
        while (!S.isEmpty()) {
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        
        s1 = s1.trim();
        String str2 = new StringBuffer(s1).reverse().toString();
        out = str2.split(" ");
        return out;
    }
}
