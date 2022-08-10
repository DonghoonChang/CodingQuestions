package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/*
    Start time: 8:01
    End time: 8:18
    Runtime: 29 ms, faster than 35.55% of Java online submissions for Zigzag Conversion.
    Memory Usage: 52.9 MB, less than 36.73% of Java online submissions for Zigzag Conversion.
 */
public class ZigzagConversion {
    public String convert(String s, int numRows) {
        List<StringBuilder> rows = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            rows.add(new StringBuilder());
        }

        int cycle = numRows <= 2 ? numRows : numRows * 2 - 2;
        for(int i = 0; i < s.length(); i++){
            int ordinal = i % cycle;
            int rowIndex = ordinal < numRows ? ordinal : cycle - ordinal;

            rows.get(rowIndex).append(s.charAt(i));
        }

        String temp = "";
        for(StringBuilder sb: rows){
            temp += sb.toString();
        }

        return temp;
    }
}
