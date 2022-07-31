package LeetCode.Medium;

import java.util.Arrays;


/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
    Memory Usage: 41.9 MB, less than 26.62% of Java online submissions for House Robber II.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int l = nums.length;
        int[] with = new int[l - 1];
        int[] without = new int[l - 1];
        for(int i = 0; i < l -1; i++){
            with[i] = nums[i];
            without[i] = nums[i + 1];
        }

        int a = inner(with);
        int b = inner(without);

        return Math.max(a, b);
    }


    private int inner(int[] nums){
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

    public static void main(String[] args){
        int[] nums = {1, 2, 1, 1};
        int result = (new HouseRobberII()).rob(nums);

        System.out.println(result);
    }
}
