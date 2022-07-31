package LeetCode.Medium;

import java.util.*;

public class KClosesPointsToOrigin {
    /*
        Runtime: 30 ms, faster than 87.94% of Java online submissions for K Closest Points to Origin.
        Memory Usage: 50.4 MB, less than 97.37% of Java online submissions for K Closest Points to Origin.
     */
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> q = new PriorityQueue<>(k, Comparator.comparingInt(l -> l[0] * l[0] + l[1] * l[1]));

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
