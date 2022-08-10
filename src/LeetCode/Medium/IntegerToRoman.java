package LeetCode.Medium;

import LeetCode.Easy.RomanToInteger;

import java.util.HashMap;
import java.util.Map;

/*
    Start time: 10:33
    End time: 10:43
    Runtime: 9 ms, faster than 65.54% of Java online submissions for Integer to Roman.
    Memory Usage: 44.7 MB, less than 81.93% of Java online submissions for Integer to Roman.
 */
public class IntegerToRoman {
    static Map<Integer, String> map = new HashMap<>(){
        {
            put(1000, "M");
            put(900, "CM");
            put(500, "D");
            put(400, "CD");
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }
    };

    static int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for(int n: nums){
            int quotient = num / n;
            num %= n;

            for(int i = 0; i < quotient; i++){
                sb.append(map.get(n));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println((new IntegerToRoman()).intToRoman(1994));
    }
}
