
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

    public boolean isOperator(char c) {          //xac dinh toan tu
        char operator[] = {'+', '-', '*', '/', '(', ')'};
        Arrays.sort(operator);
        return Arrays.binarySearch(operator, c) > -1;
    }

    public String processingString(String s) { //xu ly xau nhap vao 
        String s1 = "";
        Xulyxau xulyxau = new Xulyxau();
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!xulyxau.isOperator(c)) {
                s1 = s1 + c;
            } else {
                s1 = s1 + " " + c + " ";
            }
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+", " ");
        return s1;
    }
    
    public int priority(char c) {                //xac dinh do uu tien cua toan tu
        return switch (c) {
            case '+', '-' ->
                1;
            case '*', '/' ->
                2;
            default ->
                0;
        };
    }
}
