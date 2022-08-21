package LeetCode.Easy;

/*
Time: 4m
Runtime: 2 ms, faster than 92.45% of Java online submissions for Monotonic Array.
Memory Usage: 52 MB, less than 99.88% of Java online submissions for Monotonic Array.
 */
public class MonotonicArray {
    public boolean isMonotonic(int[] nums) {

        int prev = nums[0];
        boolean increasing = true;
        for(int num: nums){
            if(prev <= num){
                prev = num;
                continue;
            }

            increasing = false;
            break;
        }

        if(increasing){
            return true;
        }

        prev = nums[0];
        boolean decreasing = true;
        for(int num: nums){
            if(prev >= num){
                prev = num;
                continue;
            }

            decreasing = false;
            break;
        }

        return decreasing;
    }
}
