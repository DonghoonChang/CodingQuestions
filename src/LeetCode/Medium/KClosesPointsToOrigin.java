package LeetCode.Medium;

import java.util.*;

public class KClosesPointsToOrigin {
    /*
        Runtime: 41 ms, faster than 69.76% of Java online submissions for K Closest Points to Origin.
        Memory Usage: 119.1 MB, less than 11.00% of Java online submissions for K Closest Points to Origin.
     */
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(l -> l[0] * l[0] + l[1] * l[1]));

        for(int[] point: points){
            q.add(point);
        }

        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < k; i++){
            result.add(q.poll());
        }

        return result.toArray(int[][]::new);
    }
}
