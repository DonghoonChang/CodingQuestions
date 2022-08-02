package LeetCode.Medium;

import java.util.Arrays;
import java.util.List;

/*
    Start time: 12:46
    end time: 1:02
    TODO: Needs to be improved
    Runtime: 373 ms, faster than 5.26% of Java online submissions for Triangle.
    Memory Usage: 44 MB, less than 56.45% of Java online submissions for Triangle.
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int levels = triangle.size();
        int width = triangle.get(levels - 1).size();
        int[][] dp = new int[levels][width];
        for(int[] level: dp){
            Arrays.fill(level, Integer.MAX_VALUE);
        }

        inner(triangle, dp, 0, 0, triangle.get(0).get(0), levels - 1);

        int min = Integer.MAX_VALUE;
        for(int bottom: dp[levels - 1]){
            min = Math.min(min, bottom);
        }

        return min;
    }

    private void inner(List<List<Integer>> triangle, int[][] dp, int level, int index, int sum, int maxLevel){
        if(dp[level][index] <= sum){
            return;
        } else{
            dp[level][index] = sum;
        }

        if(level == maxLevel){
            return;
        }


        inner(triangle, dp, level + 1, index, sum + triangle.get(level + 1).get(index), maxLevel);
        inner(triangle, dp, level + 1, index + 1, sum + triangle.get(level + 1).get(index + 1), maxLevel);
    }
}
