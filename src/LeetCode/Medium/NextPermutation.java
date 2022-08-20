package LeetCode.Medium;

import java.util.Arrays;

/*
Time: 15m
Runtime: 2 ms, faster than 26.85% of Java online submissions for Next Permutation.
Memory Usage: 44.1 MB, less than 23.72% of Java online submissions for Next Permutation.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = -1, j = - 1;
        boolean found = false;

        loop:
        for(i = nums.length - 2; i >= 0; i--){
            for(j = nums.length - 1; j > i; j--){
                if(nums[i] < nums[j]){
                    found = true;
                    break loop;
                }
            }
        }

        if(!found){
            // no next permutation;
            Arrays.sort(nums);
            return;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;


        int[] tail = new int[nums.length  - i - 1];
        for(int l = 0, k = i + 1; k < nums.length; k++){
            tail[l++] = nums[k];
        }

        Arrays.sort(tail);
        for(int l = 0, k = i + 1; k < nums.length; k++){
            nums[k] = tail[l++];
        }
    }

    public static void main(String[] args){
        (new NextPermutation()).nextPermutation(new int[]{1, 3, 2});

        System.out.println("Done");
    }
}
