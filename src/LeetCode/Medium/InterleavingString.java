package LeetCode.Medium;

// start time: 11:00
// End time: 11:40
import java.util.HashSet;
import java.util.Set;


/*
    Runtime: 12 ms, faster than 14.69% of Java online submissions for Interleaving String.
    Memory Usage: 56.1 MB, less than 5.15% of Java online submissions for Interleaving String.
 */
public class InterleavingString {

    private static int count = 0;
    public boolean isInterleave(String s1, String s2, String s3) {
        count = 0;

        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();

        boolean[][][] memo = new boolean[l1 + 1][l2 + 1][l3 + 1];

        inner(s1, s2, s3, 0, 0, 0, memo);
        return count > 0;
    }

    private void inner(String s1, String s2, String s3, int i, int j, int k, boolean[][][] memo){
        if(memo[i][j][k]){
            return;
        }

        memo[i][j][k] = true;

        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();

        if(k == l3) {
            if(i == l1 && j == l2){
                count++;
            }

            return;
        }

        char ch1 = i < l1 ? s1.charAt(i) : '.';
        char ch2 = j < l2 ? s2.charAt(j) : '.';
        char ch3 = s3.charAt(k);

        boolean s1Has = ch1 == ch3;
        boolean s2Has = ch2 == ch3;

        if(s1Has && s2Has){
            inner(s1, s2, s3, i + 1, j, k + 1, memo);
            inner(s1, s2, s3, i, j + 1, k + 1, memo);
            return;
        }

        if (s1Has) {
            inner(s1, s2, s3, i + 1, j, k + 1, memo);
            return;
        }

        if (s2Has) {
            inner(s1, s2, s3, i, j + 1, k + 1, memo);
            return;
        }
    }

    public static void main(String[] args){
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        boolean result = (new InterleavingString()).isInterleave(s1, s2, s3);

        System.out.println(result);
    }
}
