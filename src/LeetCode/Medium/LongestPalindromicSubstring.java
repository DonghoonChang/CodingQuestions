package LeetCode.Medium;

// start time: 10: 50
// end time: 11:00
/*
    TODO: Need Improvement -> Don't try to get all the palindromes. Just get the largest
    Runtime: 163 ms, faster than 38.04% of Java online submissions for Longest Palindromic Substring.
    Memory Usage: 45 MB, less than 52.78% of Java online submissions for Longest Palindromic Substring.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s.length() == 1){
            return s;
        }

        int max = 1;
        int start = -1;
        int end = -1;
        int l = s.length();
        boolean[][] dp = new boolean[l][l];

        for(int i = 0; i < l; i++){
            dp[i][i] = true;

            start = i;
            end = i;
        }

        for(int i = 0; i < l - 1; i++){
            dp[i][i+1] = s.charAt(i) == s.charAt(i + 1);

            if(dp[i][i+1]){
                max = 2;
                start = i;
                end = i + 1;
            }
        }

        for(int sub = 3; sub < l + 1; sub++){
            for(int i = 0; i < l - sub + 1; i++){
                dp[i][i + sub - 1] = dp[i + 1][i + sub - 2] && s.charAt(i) == s.charAt(i + sub - 1);

                if(dp[i][i + sub - 1]){
                    if(max < sub){
                        max = sub;
                        start = i;
                        end = i + sub - 1;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }


    /*
    Example
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
	}
     */

    public static void main(String[] args){
        String str = "ccc";
        String result = (new LongestPalindromicSubstring()).longestPalindrome(str);

        System.out.println(result);
    }
}
