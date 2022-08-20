package LeetCode.Easy;

/*
Time: 1m
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
Memory Usage: 42.7 MB, less than 46.28% of Java online submissions for Remove Element.
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for(int num: nums){
            if(num != val){
                nums[j++] = num;
            }
        }

        return j;
    }
}
