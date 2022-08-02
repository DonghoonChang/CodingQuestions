package LeetCode.Medium;

import java.util.Arrays;
import java.util.List;


public class Triangle {

    /*
        Start time: 1:10
        end time: 1:12
        Runtime: 1 ms, faster than 99.86% of Java online submissions for Triangle.
        Memory Usage: 42 MB, less than 92.66% of Java online submissions for Triangle.
     */

    public int minimumTotal(List<List<Integer>> triangle) {
        int levels = triangle.size();
        int width = triangle.get(levels - 1).size();

        int[][] memo = new int[levels][width];
        for (int[] level : memo) {
            Arrays.fill(level, Integer.MAX_VALUE);
        }

        return inner(triangle, memo, 0, 0, levels - 1);
    }

    private int inner(List<List<Integer>> triangle, int[][] memo, int level, int index, int maxLevel) {
        if(memo[level][index] != Integer.MAX_VALUE){
            return memo[level][index];
        }

        int val = triangle.get(level).get(index);

        if(level == maxLevel){
            return val;
        }

        int left = inner(triangle, memo, level + 1, index, maxLevel);
        int right = inner(triangle, memo, level + 1, index + 1, maxLevel);

        int minIndexLevel = val + Math.min(left, right);
        memo[level][index] = minIndexLevel;
        return minIndexLevel;
    }

    /*
    Start time: 12:46
    end time: 1:02

    TODO: DP solution - Needs to be improved
    Runtime: 373 ms, faster than 5.26% of Java online submissions for Triangle.
    Memory Usage: 44 MB, less than 56.45% of Java online submissions for Triangle.
    */
    public int minimumTotalII(List<List<Integer>> triangle) {
        int levels = triangle.size();
        int width = triangle.get(levels - 1).size();
        int[][] dp = new int[levels][width];
        for (int[] level : dp) {
            Arrays.fill(level, Integer.MAX_VALUE);
        }

        inner(triangle, dp, 0, 0, triangle.get(0).get(0), levels - 1);

        int min = Integer.MAX_VALUE;
        for (int bottom : dp[levels - 1]) {
            min = Math.min(min, bottom);
        }

        return min;
    }

    private void inner(List<List<Integer>> triangle, int[][] dp, int level, int index, int sum, int maxLevel) {
        if (dp[level][index] <= sum) {
            return;
        } else {
            dp[level][index] = sum;
        }

        if (level == maxLevel) {
            return;
        }


        inner(triangle, dp, level + 1, index, sum + triangle.get(level + 1).get(index), maxLevel);
        inner(triangle, dp, level + 1, index + 1, sum + triangle.get(level + 1).get(index + 1), maxLevel);
    }
}
