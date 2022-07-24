package LeetCode.Easy;

public class MaximumSubarray {
    /*
        Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
        Memory Usage: 51.9 MB, less than 87.35% of Java online submissions for Maximum Subarray.
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int localSum = 0;
        for(int i = 0; i < nums.length; i ++){
            localSum += nums[i];
            maxSum = Math.max(maxSum, localSum);

            if (localSum < 0){
                localSum = 0;
                continue;
            }
        }

        return maxSum;
    }
}
