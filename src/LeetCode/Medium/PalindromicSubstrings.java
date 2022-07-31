package LeetCode.Medium;

public class PalindromicSubstrings {
    /*
    Runtime: 18 ms, faster than 38.24% of Java online submissions for Palindromic Substrings.
    Memory Usage: 45.1 MB, less than 17.04% of Java online submissions for Palindromic Substrings.
     */
    public int countSubstrings(String s) {
        int l = s.length();
        int count = 0;
        boolean[][] isPalindromic = new boolean[l][l];

        // Single letters
        for(int i = 0; i < l; i++){
            isPalindromic[i][i] = true;
            count++;
        }

        // Two letters
        for(int i = 0; i < l - 1; i++){
            if(s.charAt(i) == s.charAt(i + 1)){
                isPalindromic[i][i + 1] = true;
                count++;
            }
        }

        // < 3 letters
        for(int size = 3; size <= l; size++){
            for(int start = 0; start < l - size + 1; start++){
                int end = start + size - 1;
                if(s.charAt(start) == s.charAt(end) && isPalindromic[start + 1][end - 1]){
                    isPalindromic[start][end] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args){
        int result = (new PalindromicSubstrings()).countSubstrings("abc");

        System.out.println(result);
    }
}
