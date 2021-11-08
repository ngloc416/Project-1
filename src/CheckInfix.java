/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loc Nguyen
 */
public class CheckInfix {   //kiem tra cu phap va tinh bieu thuc trung to

    String token, str[];
    int i = 0;              //chỉ số của phần tử đang xét
    boolean kt = true;

    public CheckInfix(String[] str) {
        this.str = str;
        token = str[i];
    }

    public boolean check() {
        compileExpression();
        return !(!kt || token != null);   //bieu thuc dung khi kt == true va token == null
    }

    private String tokenType(String s) {  //gan nhan cho cac phan tu
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
        } else {
            kt = false;
        }
    }

    private void compileExpression() {
        switch (tokenType(token)) {
            case "SB_PLUS" -> {
                eat("SB_PLUS");
                compileExpression2();
            }
            case "SB_MINUS" -> {
                eat("SB_MINUS");
                compileExpression2();
            }
            default -> {
                compileExpression2();
            }
        }
    }

    private void compileExpression2() {
        compileTerm();
        compileExpression3();
    }

    private void compileExpression3() {
        switch (tokenType(token)) {
            case "SB_PLUS" -> {
                eat("SB_PLUS");
                compileTerm();
                compileExpression3();
            }
            case "SB_MINUS" -> {
                eat("SB_MINUS");
                compileTerm();
                compileExpression3();
            }
            case "SB_RPAR" -> {

            }
            case "NULL" -> {

            }
            default -> {
                kt = false;
            }
        }
    }

    private void compileTerm() {
        compileFactor();
        compileTerm2();
    }

    private void compileTerm2() {
        switch (tokenType(token)) {
            case "SB_TIMES" -> {
                eat("SB_TIMES");
                compileFactor();
                compileTerm2();
            }
            case "SB_SLASH" -> {
                eat("SB_SLASH");
                compileFactor();
                compileTerm2();
            }
            case "SB_PLUS" -> {

            }
            case "SB_MINUS" -> {

            }
            case "SB_RPAR" -> {

            }
            case "NULL" -> {

            }
            default -> {
                kt = false;
            }
        }
    }

    private void compileFactor() {
        switch (tokenType(token)) {
            case "TK_NUMBER" -> {
                eat("TK_NUMBER");
            }
            case "TK_CHAR" -> {
                eat("TK_CHAR");
            }
            case "SB_LPAR" -> {
                eat("SB_LPAR");
                compileExpression();
                eat("SB_RPAR");
            }
            default -> {
                kt = false;
            }
        }
    }
}
