package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/*
    start time: 8:36
    end time: 8:43
    Runtime: 1930 ms, faster than 5.84% of Java online submissions for Container With Most Water.
    Memory Usage: 77.4 MB, less than 49.97% of Java online submissions for Container With Most Water.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxH = 0;
        int maxArea = 0;
        List<int[]> points = new ArrayList<>();
        for(int i = 0; i < height.length; i++){
            int h = height[i];
            for(int[] point: points){
                maxArea = Math.max(maxArea, (i - point[0]) * Math.min(h, point[1]));
            }

            if(h <= maxH){
                continue;
            }

            points.add(new int[]{i, h});
            maxH = h;
        }

        return maxArea;
    }
}
