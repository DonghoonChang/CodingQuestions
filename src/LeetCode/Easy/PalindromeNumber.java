package LeetCode.Easy;

/*
    Start time: 9:35
    End time: 9:38
    Runtime: 19 ms, faster than 25.21% of Java online submissions for Palindrome Number.
    Memory Usage: 46.1 MB, less than 16.48% of Java online submissions for Palindrome Number.
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        String reverse = (new StringBuilder()).append(str).reverse().toString();

        return str.equals(reverse);
    }
}
