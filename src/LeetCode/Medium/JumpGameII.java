package LeetCode.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Brute Force
Time: 45m
Runtime: 127 ms, faster than 19.13% of Java online submissions for Jump Game II.
Memory Usage: 53.4 MB, less than 5.12% of Java online submissions for Jump Game II.
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if(nums.length == 1){
            return 0;
        }

        PriorityQueue<Integer[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(l -> l[0]));
        boolean[] visited = new boolean[nums.length];

        minHeap.add(new Integer[]{0, 0});
        while(!minHeap.isEmpty()){
            Integer[] next = minHeap.poll();
            for(int i = next[1] + nums[next[1]]; i > next[1]; i--){
                if(i >= nums.length - 1){
                    return next[0] + 1;
                }

                if(visited[i]){
                    continue;
                }

                minHeap.add(new Integer[]{next[0] + 1, i});
                visited[i] = true;
            }
        }

        return -1;
    }

    public int jumpLinear(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }


    public static void main(String[] args){
        int[] nums = new int[]{2,3,1,1,4};
        int result = (new JumpGameII()).jump(nums);

        System.out.println(result);
    }
}
