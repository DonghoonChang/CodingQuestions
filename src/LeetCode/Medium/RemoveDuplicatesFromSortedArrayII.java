package LeetCode.Medium;

/*
Time: 1m
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
Memory Usage: 45.1 MB, less than 24.14% of Java online submissions for Remove Duplicates from Sorted Array II.
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int prev = -101;
        int tolerance = 0;
        int j = 0;
        for(int num: nums){
            if(prev != num){
                nums[j++] = num;
                prev = num;
                tolerance = 0;
                continue;
            }

            if(tolerance == 0){
                nums[j++] = num;
                tolerance++;
            }
        }

        return j;
    }
}
