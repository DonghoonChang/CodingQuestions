package LeetCode.Hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {
    /*
        Runtime: 24 ms, faster than 35.72% of Java online submissions for Swim in Rising Water.
        Memory Usage: 42.5 MB, less than 85.83% of Java online submissions for Swim in Rising Water.
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] directions = new int[4][2];
        directions[0] = new int[]{1, 0};
        directions[1] = new int[]{-1, 0};
        directions[2] = new int[]{0, 1};
        directions[3] = new int[]{0, -1};

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> heap = new PriorityQueue<>(10, Comparator.comparingInt(node -> node[2]));
        heap.add(new int[]{0, 0, grid[0][0]});

        while(!heap.isEmpty()){
            int[] curr = heap.poll();
            int currRow = curr[0];
            int currColumn = curr[1];
            int currValue = curr[2];

            if(currRow == n - 1 && currColumn == n - 1){
                return currValue;
            }

            visited[currRow][currColumn] = true;

            for(int[] direction: directions){
                int nextR = currRow + direction[0];
                int nextC = currColumn + direction[1];

                if(nextR < 0 || nextR >= n || nextC < 0 || nextC >= n){
                    continue;
                }

                if(visited[nextR][nextC]){
                    continue;
                }

                int nextValue = Math.max(currValue, grid[nextR][nextC]);
                heap.add(new int[]{nextR, nextC, nextValue});
            }
        }

        return -1;
    }

    public static void main(String[] args){
        SwimInRisingWater swim = new SwimInRisingWater();
//        int[][] grid = new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        int[][] grid = new int[][]{{0,2},{1,3}};
        int result = swim.swimInWater(grid);
        System.out.println(result);
    }
}
