package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/*
    Runtime: 11 ms, faster than 45.05% of Java online submissions for Roman to Integer.
    Memory Usage: 46.7 MB, less than 37.45% of Java online submissions for Roman to Integer.
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        Map<Character , Integer> T = new HashMap<>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };

        Integer sum = T.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2 ; i >= 0; --i) {
            if (T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
                sum -= T.get(s.charAt(i));
            } else {
                sum += T.get(s.charAt(i));
            }
        }

        return sum;
    }
}
