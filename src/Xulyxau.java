
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Loc Nguyen
 */
public class Xulyxau {

    public Xulyxau() {
    }

    public boolean isOperator(char c) {        
        char operator[] = {'+', '-', '*', '/', '(', ')'};
        Arrays.sort(operator);
        return Arrays.binarySearch(operator, c) > -1;
    } //xac dinh toan tu

    public int priority(char c) {        //xac dinh do uu tien cua toan tu     
        return switch (c) {
            case '+', '-' ->
                1;
            case '*', '/' ->
                2;
            default ->
                0;
        };
    }       

    public String[] processingString(String s) {    //xu ly xau nhap vao va tao chuoi xau
        String s1 = "", s2[] = null;
        Xulyxau xulyxau = new Xulyxau();
        s = s.trim();                   //bo dau cach thua o hai dau
        s = s.replaceAll("\\s+", " ");  //xoa dau cach thua o canh nhau
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!xulyxau.isOperator(c) && c >= '0' && c <= '9') { //tach cac so
                s1 = s1 + c;
            } else {                        //tach cac toan tu va bien
                s1 = s1 + " " + c + " ";
            }
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+", " ");
        s2 = s1.split(" ");
        return s2;
    }   

    public boolean checkInfix(String[] s) {            // kiem tra cu phap bieu thuc trung to
        if (isOperator(s[0].charAt(0)) && s[0].charAt(0) != '(') {  //dau chuoi la toan tu khac (
            return false;  
        }
        if (isOperator(s[s.length - 1].charAt(0)) && s[s.length - 1].charAt(0) != ')') { //cuoi chuoi la toan tu khac )
            return false;
        }

        for (int i = 0; i <= s.length - 2; i++) {
            if (!isOperator(s[i].charAt(0))) {
                if (!isOperator(s[i + 1].charAt(0)) || s[i + 1].charAt(0) == '(') {   //hai toan hang ke nhau || toan hang ke (
                    return false;
                }
            } else {
                switch (s[i].charAt(0)) {
                    case '+', '-', '*', '/', '(' -> {
                        if (isOperator(s[i + 1].charAt(0)) && s[i + 1].charAt(0) != '(') {
                            return false;
                        }
                    }
                    case ')' -> {
                        if (s[i + 1].charAt(0) == '(' || !isOperator(s[i + 1].charAt(0))) {
                            return false;
                        }
                    }
                    default -> {
                    }
                }
            }
        }
        int dem = 0;                                //kiem tra cac cap ngoac
        for (int i = 0; i <= s.length - 1; i++) { 
            char c = s[i].charAt(0);
            if (c == '(') {
                dem = dem + 1;
            } else if (c == ')') {
                dem = dem - 1;
            }
        }
        if (dem != 0) {
            return false;
        }
        return true;
    }       
}
