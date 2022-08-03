package LeetCode.Medium;

import java.util.HashSet;
import java.util.Set;

public class LongestRepeatingCharacterReplacement {


    // Time complexity: O(26n)
    // Start time: 11:06
    // Break time: 11:12 because of Pocky
    // Resume time: 7:45
    // end time: 7:54

    /*
    Runtime: 22 ms, faster than 41.48% of Java online submissions for Longest Repeating Character Replacement.
    Memory Usage: 42.3 MB, less than 90.40% of Java online submissions for Longest Repeating Character Replacement.
     */

    public int characterReplacement(String s, int k) {
        int i = 0; // inclusive
        int j = 1; // exclusive

        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        int globalMax = 0;
        for (Character ch : set.toArray(Character[]::new)) {
            globalMax = Math.max(globalMax, longestRepeating(s, ch, k));
        }

        return globalMax;
    }

    private int longestRepeating(String s, char ch, int k) {
        int i = 0; // inclusive
        int j = 0; // exclusive
        int max = 0;

        while (j < s.length()) {
            char nextChar = s.charAt(j);

            if (nextChar == ch) {
                j++;
                max = Math.max(max, j - i);
                continue;
            }

            if (k > 0) {
                k--;
                j++;
                max = Math.max(max, j - i);
                continue;
            }

            if (k == 0) {
                int toBeRemoved = s.charAt(i);

                if (toBeRemoved != ch) {
                    k++;
                }

                i++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String str = "AAB";
        int k = 1;
        int result = (new LongestRepeatingCharacterReplacement()).characterReplacement(str, k);

        System.out.println(result);
    }
}
