package LeetCode.Medium;

import java.util.Arrays;
/*
    Start time: 10: 55
    End time: 11: 07
    Runtime: 79 ms, faster than 75.95% of Java online submissions for 3Sum Closest.
Memory Usage: 49.7 MB, less than 56.63% of Java online submissions for 3Sum Closest.
 */

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int minDiff = Integer.MAX_VALUE;
        int closest = -1;

        Arrays.sort(nums);
        for(int i = nums.length - 1; i >= 0; i--){
            int j = 0, k = i - 1;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(target - sum);

                if(minDiff > diff){
                    minDiff = diff;
                    closest = sum;
                }

                if(sum > target){
                    k--;
                    continue;
                }

                if(sum < target){
                    j++;
                    continue;
                }

                return target;
            }
        }

        return closest;
    }

    public static void main(String[] args){
        int[] nums = new int[]{ -1, 2, 1, -4};
        int target = 1;
        int result = (new ThreeSumClosest()).threeSumClosest(nums, target);

        System.out.println(result);
    }
}
