package LeetCode.Medium;

import java.util.Arrays;


public class HouseRobber {
    public int rob(int[] nums){
        int l = nums.length;
        if(l == 1){
            return nums[0];
        }

        if(l == 2){
            return Math.max(nums[0], nums[1]);
        }

        int[] memo = new int[l];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < l; i++){
            memo[i] = Math.max(memo[i-1], memo[i - 2] + nums[i]);
        }

        return memo[l - 1];
    }


    /*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
    Memory Usage: 41.5 MB, less than 54.46% of Java online submissions for House Robber.
    */
    public int robFirstAttempt(int[] nums) {
        int l = nums.length;
        int[] dp = new int[l];

        Arrays.fill(dp, -1);
        inner(nums, dp, 0, 0);

        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }

        return max;
    }

    private void inner(int[] nums, int[] dp, int total, int index) {
        if (index >= nums.length) {
            return;
        }

        if (dp[index] < total + nums[index]) {
            dp[index] = total + nums[index];
            inner(nums, dp, total + nums[index], index + 2);
        }


        if (index + 1 < nums.length) {
            if (dp[index + 1] < total + nums[index + 1]) {
                dp[index + 1] = total + nums[index + 1];
                inner(nums, dp, total + nums[index + 1], index + 3);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        int result = (new HouseRobber()).rob(nums);

        System.out.println(result);
    }
}
