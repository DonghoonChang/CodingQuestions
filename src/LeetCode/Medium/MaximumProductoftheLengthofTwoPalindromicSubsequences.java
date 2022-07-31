package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/*
Runtime: 962 ms, faster than 42.66% of Java online submissions for Maximum Product of the Length of Two Palindromic Subsequences.
Memory Usage: 43.3 MB, less than 54.03% of Java online submissions for Maximum Product of the Length of Two Palindromic Subsequences.
 */
public class MaximumProductoftheLengthofTwoPalindromicSubsequences {

    // The most brute force apporach I've ever seen
    public int maxProduct(String s) {
        int l = s.length(); // l <= 12
        int max = 1 << l; // include all characters
        Map<Integer, Integer> map = new HashMap<>(); // binary representation of the palindrome strings and their lengths

        for (int i = 0; i <= max; i++) {
            String tmp = "";

            for (int j = 0; j < l; j++) {
                boolean include = (i >> j & 1) == 1;

                if (include) {
                    tmp += s.charAt(l - j - 1);
                }
            }

            if (isPalindrome(tmp)) {
                map.put(i, tmp.length());
            }
        }

        int xMax = 1;
        for (Map.Entry<Integer, Integer> entryA : map.entrySet()) {
            int strA = entryA.getKey();
            int lenA = entryA.getValue();

            for (Map.Entry<Integer, Integer> entryB : map.entrySet()) {
                int strB = entryB.getKey();
                int lenB = entryB.getValue();

                if ((strA & strB) == 0) {
                    xMax = Math.max(xMax, lenA * lenB);
                }
            }
        }

        return xMax;
    }

    private static boolean isPalindrome(String str) {
        int l = str.length();

        for (int i = 0, j = l - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int result = (new MaximumProductoftheLengthofTwoPalindromicSubsequences()).maxProduct("leetcodecom");

        System.out.println(result);
    }
}
