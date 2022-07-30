package LeetCode.Medium;

/*
    Runtime: 2 ms, faster than 78.32% of Java online submissions for Two Sum II - Input Array Is Sorted.
    Memory Usage: 50.8 MB, less than 6.63% of Java online submissions for Two Sum II - Input Array Is Sorted.
 */
public class TwoSumII {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length == 2){
            return new int[]{1, 2};
        }

        int i = 0;
        int j = 1;
        while(j < nums.length - 1 && nums[i] + nums[j] < target){
            j++;
        }

        while(i < j){
            int sum = nums[i] + nums[j];

            if(sum == target){
                break;
            }

            if(sum < target){
                j++;
            }

            if(sum > target){
                j--;
            }
        }

        return new int[]{i + 1, j + 1};
    }

    public static void main(String[] args){
        int[] nums = new int[]{5, 25, 75};
        int target = 100;
        int[] result = (new TwoSumII()).twoSum(nums, target);

        System.out.println(result);
    }
}
