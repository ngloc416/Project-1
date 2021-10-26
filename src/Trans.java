
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

    public String[] inToPost(String[] s) { //chuyen trung to thanh hau to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (String item : s) {
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {
                s1 = s1 + " " + item;
            } else if (c == '(') {
                S.push(item);
            } else if (c == ')') {
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

        while (!S.isEmpty()) {
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        s1 = s1.trim();
        out = s1.split(" ");
        return out;
    }

    public String[] inToPre(String[] s) { //chuyen trung to thanh tien to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (int i = s.length - 1; i >= 0; i--) {
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {
                s1 = s1 + " " + s[i];
            } else if (c == ')') {
                S.push(s[i]);
            } else if (c == '(') {
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
                S.push(s[i]);
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

    public String[] postToPre(String[] s) { //chuyen hau to thanh tien to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (String item : s) {
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {
                S.push(item);
            } else {
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push(item + " " + op2 + " " + op1);
            }
        }

        s1 = S.peek();
        S.pop();
        
        if (!S.isEmpty()) s1 = null;
        
        out = s1.split(" ");
        return out;
    }

    public String[] postToIn(String[] s) { //chuyen hau to thanh trung to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", out[] = null;
        Stack<String> S = new Stack<>();

        for (String item : s) {
            char c = item.charAt(0);
            if (!xulyxau.isOperator(c)) {
                S.push(item);
            } else {
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push("(" + op2 + " " + item + " " + op1 + ")");
            }
        }
        s1 = S.peek();
        S.pop();
        
        if (!S.isEmpty()) s1 = null;
        
        out = s1.split(" ");
        return out;
    }

    public String[] preToIn(String[] s) { //chuyen tien to thanh trung to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", in[] = null, out[] = null;
        Stack<String> S = new Stack<>();

        for (int i = s.length - 1; i >= 0; i--) {
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {
                S.push(s[i]);
            } else {
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push("(" + op1 + " " + s[i] + " " + op2 + ")");
            }
        }

        s1 = S.peek();
        S.pop();
        
        if (!S.isEmpty()) s1 = null;
        
        out = s1.split(" ");
        return out;
    }

    public String[] preToPost(String[] s) { //chuyen tien to thanh hau to

        Xulyxau xulyxau = new Xulyxau();
        String s1 = "", in[] = null, out[] = null;
        Stack<String> S = new Stack<>();

        for (int i = s.length - 1; i >= 0; i--) {
            char c = s[i].charAt(0);
            if (!xulyxau.isOperator(c)) {
                S.push(s[i]);
            } else {
                String op1 = S.peek();
                S.pop();
                String op2 = S.peek();
                S.pop();
                S.push(op1 + " " + op2 + " " + s[i]);
            }
        }

        s1 = S.peek();
        S.pop();
        
        if (!S.isEmpty()) s1 = null;
        
        out = s1.split(" ");
        return out;
    }
}
