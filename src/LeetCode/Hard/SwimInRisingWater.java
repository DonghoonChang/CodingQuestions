package LeetCode.Hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {
    public class Node{
        public int r;
        public int c;
        public int v;

        public Node(int r, int c, int v){
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] directions = new int[4][2];
        directions[0] = new int[]{1, 0};
        directions[1] = new int[]{-1, 0};
        directions[2] = new int[]{0, 1};
        directions[3] = new int[]{0, -1};

        int[][] record = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(record[i], Integer.MAX_VALUE);
        }
        record[0][0] = grid[0][0];

        Queue<Node> heap = new PriorityQueue<>(10, Comparator.comparingInt(node -> node.v));
        heap.add(new Node(0, 0, grid[0][0]));

        while(!heap.isEmpty()){
            Node curr = heap.poll();

            for(int[] direction: directions){
                int nextR = curr.r + direction[0];
                int nextC = curr.c + direction[1];

                if(nextR < 0 || nextR >= n || nextC < 0 || nextC >= n){
                    continue;
                }

                int nextValue = Math.max(curr.v, grid[nextR][nextC]);
                if(nextValue < record[nextR][nextC]){
                    record[nextR][nextC] = nextValue;
                    heap.add(new Node(nextR, nextC, nextValue));
                }
            }
        }


        return -1;
    }

    private int extendedGet(int[][] arr, int r, int c){
        if (r < 0 || c < 0 || r >= arr.length || c >= arr.length){
            return Integer.MAX_VALUE;
        }
        return arr[r][c];
    }

    public static void main(String[] args){
        SwimInRisingWater swim = new SwimInRisingWater();
        int[][] grid = new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        int result = swim.swimInWater(grid);
        System.out.println(result);
    }
}
