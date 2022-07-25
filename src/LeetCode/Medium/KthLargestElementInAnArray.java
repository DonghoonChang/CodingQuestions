package LeetCode.Medium;

public class KthLargestElementInAnArray {
    /*
        Runtime: 2 ms, faster than 96.23% of Java online submissions for Kth Largest Element in an Array.
        Memory Usage: 50.8 MB, less than 6.06% of Java online submissions for Kth Largest Element in an Array.
     */
    public int findKthLargest(int[] nums, int k) {
        int tenThousands = (int) Math.pow(10, 4);
        int[] counts = new int[tenThousands * 2 + 1];

        for(int num: nums){
            counts[num + tenThousands]++;
        }

        int count = 0;
        for(int i = 2 * tenThousands; i >= 0; i --){
            count += counts[i];

            if(count >= k){
                return i - tenThousands;
            }
        }

        return -1;
    }
}
