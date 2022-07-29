package LeetCode.Medium;

import java.util.Arrays;

/*
Runtime: 135 ms, faster than 29.60% of Java online submissions for Perfect Squares.
Memory Usage: 43.2 MB, less than 65.64% of Java online submissions for Perfect Squares
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        inner(dp, n, 0);
        return dp[0];
    }

    private void inner(int[] dp, int current, int count) {
        if (dp[current] <= count) {
            return;
        }

        dp[current] = Math.min(dp[current], count);

        int max = (int) Math.sqrt(current);

        for (int i = max; i > 0; i--) {
            int square = i * i;

            inner(dp, current - square, count + 1);
        }
    }

    public static void main(String[] args) {
        int result = (new PerfectSquares()).numSquares(13);
        System.out.println(result);
    }
}
