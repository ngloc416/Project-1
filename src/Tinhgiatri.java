/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Loc Nguyen
 */
public class Tinhgiatri {

    public Tinhgiatri() {
    }

    Xulyxau xulyxau = new Xulyxau();
    Trans trans = new Trans();

    public int phan_loai(String[] s) {
        if (xulyxau.isOperator(s[0].charAt(0)) && s[0].charAt(0) != '(') {
            String str[] = null;
            try {
                str = trans.preToIn(s);
            } catch (Exception e) {
                return 0;                                            //bieu thuc sai           
            }
            return 1;                                                //la bieu thuc tien to
        } else if (xulyxau.isOperator(s[s.length - 1].charAt(0)) && s[s.length - 1].charAt(0) != ')') {
            String str[] = null;
            try {
                str = trans.postToIn(s);
            } catch (Exception e) {
                return 0;                                            //bieu thuc sai           
            }
            return 3;                                                //la bieu thuc hau to
        } else if (xulyxau.checkInfix(s)) {
            return 2;                                                //la bieu thuc trung to
        } else {
            return 0;                                                //bieu thuc sai
        }
    }

    public boolean check(String s) {               //kiem tra bieu thuc so hoc
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!xulyxau.isOperator(c) && c != ' ') {
                if ((c < '0') || c > '9') {
                    return false;
                }
            }
        }
        return true;
    }

}
