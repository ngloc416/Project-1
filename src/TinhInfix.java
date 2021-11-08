

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loc Nguyen
 */
public class TinhInfix {   //kiem tra cu phap bieu thuc trung to

    String token, str[];
    int i = 0;              //chỉ số của phần tử đang xét
    double KQ;

    public TinhInfix(String[] str) {
        this.str = str;
        token = str[i];
    }

    public double tinh() {
        KQ = compileExpression();
        return KQ;
    }

    private String tokenType(String s) {
        String type;
        if (s == null) {
            type = "NULL";
        } else if (s.charAt(0) == '+') {
            return type = "SB_PLUS";
        } else if (s.charAt(0) == '-') {
            return type = "SB_MINUS";
        } else if (s.charAt(0) == '*') {
            return type = "SB_TIMES";
        } else if (s.charAt(0) == '/') {
            return type = "SB_SLASH";
        } else if (s.charAt(0) == '(') {
            return type = "SB_LPAR";
        } else if (s.charAt(0) == ')') {
            return type = "SB_RPAR";
        } else if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            return type = "TK_NUMBER";
        } else {
            type = "TK_CHAR";
        }
        return type;
    }

    private void scan() {
        if (i + 1 < str.length) {
            token = str[i + 1];
            i = i + 1;
        } else {
            token = null;
        }
    }

    private void eat(String s) {
        if (tokenType(token).equals(s)) {
            scan();
        } 
    }

    public double compileExpression() {
        switch (tokenType(token)) {
            case "SB_PLUS" -> {
                eat("SB_PLUS");
                double Ex2 = compileExpression2();
                return Ex2;
            }
            case "SB_MINUS" -> {
                eat("SB_MINUS");
                double Ex2 = compileExpression2();
                return -(Ex2);
            }
            default -> {
                double Ex2 = compileExpression2();
                return Ex2;
            }
        }
    }

    private double compileExpression2() {
        double T = compileTerm();
        double Ex3 = compileExpression3();
        return T + Ex3;
    }

    private double compileExpression3() {
        switch (tokenType(token)) {
            case "SB_PLUS" -> {
                eat("SB_PLUS");
                double T = compileTerm();
                double Ex3 = compileExpression3();
                return T + Ex3;
            }
            case "SB_MINUS" -> {
                eat("SB_MINUS");
                double T = compileTerm();
                double Ex3 = compileExpression3();
                return -(T + Ex3);
            }
            case "SB_RPAR" -> {

            }
            case "NULL" -> {

            }
        }
        return 0;
    }

    private double compileTerm() {
        double F = compileFactor();
        String t = token;
        double T2 = compileTerm2();
        if ("SB_TIMES".equals(tokenType(t)) ) {
            return F * T2;
        } else if ("SB_SLASH".equals(tokenType(t))){
            return F/T2;
        }else {
            return F;
        }
    }

    private double compileTerm2() {
        switch (tokenType(token)) {
            case "SB_TIMES" -> {
                eat("SB_TIMES");
                double F = compileFactor();
                String t = token;
                double T2 = compileTerm2();
                if ("SB_TIMES".equals(tokenType(t))) {
                    return F * T2;
                } else if ("SB_SLASH".equals(tokenType(t))) {
                    return F / T2;
                } else {
                    return F;
                }
            }
            case "SB_SLASH" -> {
                eat("SB_SLASH");
                double F = compileFactor();
                String t = token;
                double T2 = compileTerm2();
                if ("SB_TIMES".equals(tokenType(t))) {
                    return F / T2;
                } else if ("SB_SLASH".equals(tokenType(t))) {
                    return (F * T2);
                } else {
                    return F;
                }
            }
            case "SB_PLUS" -> {

            }
            case "SB_MINUS" -> {

            }
            case "SB_RPAR" -> {

            }
            case "NULL" -> {

            }
        }
        return 0;
    }

    private double compileFactor() {
        switch (tokenType(token)) {
            case "TK_NUMBER" -> {
                double d = Double.parseDouble(token);
                eat("TK_NUMBER");
                return d;
            }
            case "TK_CHAR" -> {
                eat("TK_CHAR");
            }
            case "SB_LPAR" -> {
                eat("SB_LPAR");
                double Ex = compileExpression();
                eat("SB_RPAR");
                return Ex;
            }
        }
        return 0;
    }
}
