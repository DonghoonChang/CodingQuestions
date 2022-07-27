package LeetCode.Medium;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInAnArray {
    /*
        Runtime: 53 ms, faster than 5.14% of Java online submissions for Kth Largest Element in an Array.
        Memory Usage: 92 MB, less than 6.06% of Java online submissions for Kth Largest Element in an Array.
     */
    public int findKthLargest(int[] nums, int k) {
        int pick = nums[nums.length - 1];

        int larger = 0;
        for(int i = 0, j = nums.length - 2; i <= j;){
            int next = nums[i];
            if (next >= pick){
                larger++;
                i++;
                continue;
            }

            nums[i] = nums[j];
            nums[j--] = next;
        }

        nums[nums.length - 1] = nums[larger];
        nums[larger] = pick;

        if (larger == k - 1){
            return pick;
        } else if(larger > k - 1){
            return findKthLargest(Arrays.copyOfRange(nums, 0, larger), k);
        } else {
            return findKthLargest(Arrays.copyOfRange(nums, larger, nums.length), k - larger);
        }
    }

    /*
     Runtime: 90 ms, faster than 5.14% of Java online submissions for Kth Largest Element in an Array.
     Memory Usage: 70.6 MB, less than 6.06% of Java online submissions for Kth Largest Element in an Array.
     */
    public int findKthLargestNLogNTime(int[] nums, int k) {
        Queue<Integer> l = new PriorityQueue<>(); // Min

        for(int num : nums){
            if(l.size() < k){
                l.add(num);
                continue;
            }

            if(num > l.peek()){
                l.poll();
                l.add(num);
                continue;
            }
        }

        return l.peek();
    }
    /*
        Runtime: 2 ms, faster than 96.23% of Java online submissions for Kth Largest Element in an Array.
        Memory Usage: 50.8 MB, less than 6.06% of Java online submissions for Kth Largest Element in an Array.
     */

    // O(1) time
    public int findKthLargestConstantTime(int[] nums, int k) {
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

    public static void main(String[] args){
        int result = (new KthLargestElementInAnArray()).findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        System.out.println(result);
    }
}
