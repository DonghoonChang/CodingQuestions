package LeetCode.Easy;

/*
Time: 7m
Runtime: 1 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
Memory Usage: 48.1 MB, less than 23.35% of Java online submissions for Remove Duplicates from Sorted Array.
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int prev = -101;
        int j = 0;
        for(int num: nums){
            if(prev !=num){
                nums[j++] = num;
                prev = num;
            }
        }

        return j;
    }
}
