package LeetCode.Easy;

import java.util.Arrays;

class TwoSum {
    /*
        Runtime: 3 ms, faster than 91.08% of Java online submissions for Two Sum.
        Memory Usage: 42.4 MB, less than 93.29% of Java online submissions for Two Sum.
     */
    public int[] twoSum(int[] nums, int target) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int i = 0;
        int j = nums.length - 1;
        int iVal = 0;
        int jVal = 0;

        while(i < j){
            int sum = sorted[i] + sorted[j];
            if (sum == target){
                iVal = sorted[i];
                jVal = sorted[j];
                break;
            } else if(sum > target){
                j--;
            } else if(sum < target){
                i++;
            }
        }

        int i1 = Integer.MAX_VALUE;
        int j1 = Integer.MAX_VALUE;
        for(int k = 0; k < nums.length; k++){
            if (i1 == Integer.MAX_VALUE && nums[k] == iVal){
                i1 = k;
                continue;
            }

            if (j1 == Integer.MAX_VALUE && nums[k] == jVal){
                j1 = k;
                continue;
            }
        }

        return new int[]{i1, j1};
    }
}