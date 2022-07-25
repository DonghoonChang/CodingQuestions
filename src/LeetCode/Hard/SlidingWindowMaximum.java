package LeetCode.Hard;

import java.util.*;

public class SlidingWindowMaximum {

    /*
        Runtime: 34 ms, faster than 89.96% of Java online submissions for Sliding Window Maximum.
        Memory Usage: 57.4 MB, less than 97.94% of Java online submissions for Sliding Window Maximum.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>(k);
        int[] max = new int[nums.length - (k - 1)];
        for(int i = 0; i < k; i++){
            insert(dq, nums[i]);
        }

        max[0] = dq.peekFirst();

        for(int i = k; i < nums.length; i++){
            int removed = nums[i - k];
            int currMax = dq.peekFirst();
            int added = nums[i];


            if(removed == currMax){
                dq.pollFirst();
            }

            insert(dq, added);
            max[i - k + 1] = dq.peekFirst();

        }

        return max;
    }

    private void insert(Deque<Integer> dq, int num){
        while(!dq.isEmpty() && dq.peekLast() < num){
            dq.pollLast();
        }

        dq.offerLast(num);
    }


    /*
        Zone of influence: O(nk) -> Time limit exceeded
     */
    public int[] maxSlidingWindowNK(int[] nums, int k) {
        int[] max = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            max[i] = nums[i];
        }

        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j <= k - 1; j++){
                if(i + j >= nums.length){
                    continue;
                }

                max[i + j] = Math.max(max[i + j], nums[i]);
            }
        }

        return Arrays.copyOfRange(max, k - 1, nums.length);
    }

    /*
        Maxheap: O(nlogk) -> Time limit exceeded
     */
    public int[] maxSlidingWindowHeap(int[] nums, int k) {
        PriorityQueue<Integer> h = new PriorityQueue<>(Comparator.reverseOrder());
        int[] max = new int[nums.length - (k - 1)];
        for(int i = 0; i < k; i++){
            h.add(nums[i]);
        }
        max[0] = h.peek();

        for(int i = k; i < nums.length; i++){
            h.remove(nums[i - k]);
            h.add(nums[i]);

            max[i - k + 1] = h.peek();
        }

        return max;
    }

    /*
        Other implementations: Descending array -> O(n2)
        Brute force comparison -> O(k2n)
     */

    public static void main(String[] args){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] result = (new SlidingWindowMaximum()).maxSlidingWindow(nums, k);
        for(int i: result){
            System.out.println(i);
        }
    }
}
