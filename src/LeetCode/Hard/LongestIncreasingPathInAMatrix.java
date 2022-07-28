package LeetCode.Hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    Runtime: 86 ms, faster than 7.80% of Java online submissions for Longest Increasing Path in a Matrix.
    Memory Usage: 72.1 MB, less than 5.10% of Java online submissions for Longest Increasing Path in a Matrix.
*/
public class LongestIncreasingPathInAMatrix {
    private static final int[][] directions = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1} // up, down, left, right
    };

    private static int maxCount = 0;
    private static int[][] visited;

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        maxCount = 0;
        visited = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                inner(matrix, i, j);
            }
        }

        return maxCount;
    }

    private void inner(int[][] matrix, int src_r, int src_c) {
        int m = matrix.length;
        int n = matrix[0].length;

        // [nextX, nextY, currCount, nextVal]
        Queue<int[]> edges = new PriorityQueue<>(Comparator.comparingInt(e -> -e[2] + e[3]));
        edges.add(new int[]{src_r, src_c, 1, matrix[src_r][src_c]});

        while (!edges.isEmpty()) {
            int[] curr = edges.poll();
            int currR = curr[0];
            int currC = curr[1];
            int currCount = curr[2];
            int currVal = curr[3];

            visited[currR][currC] = currCount;
            maxCount = Math.max(maxCount, currCount);

            for (int[] dir : directions) {
                int nextR = currR + dir[0];
                int nextC = currC + dir[1];
                int nextCount = currCount + 1;

                if(nextR < 0 || nextR > m || nextC < 0 || nextC > n){
                    continue;
                }

                if (matrix[nextR][nextC] > currVal) {
                    if (visited[nextR][nextC] < nextCount) {
                        edges.add(new int[]{nextR, nextC, nextCount, matrix[nextR][nextC]});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[][] mtrx = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
//        int[][] mtrx = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
//        int[][] mtrx = new int[][]{{1}};
        int[][] mtrx = new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {19, 18, 17, 16, 15, 14, 13, 12, 11, 10}, {20, 21, 22, 23, 24, 25, 26, 27, 28, 29}, {39, 38, 37, 36, 35, 34, 33, 32, 31, 30}, {40, 41, 42, 43, 44, 45, 46, 47, 48, 49}, {59, 58, 57, 56, 55, 54, 53, 52, 51, 50}, {60, 61, 62, 63, 64, 65, 66, 67, 68, 69}, {79, 78, 77, 76, 75, 74, 73, 72, 71, 70}, {80, 81, 82, 83, 84, 85, 86, 87, 88, 89}, {99, 98, 97, 96, 95, 94, 93, 92, 91, 90}, {100, 101, 102, 103, 104, 105, 106, 107, 108, 109}, {119, 118, 117, 116, 115, 114, 113, 112, 111, 110}, {120, 121, 122, 123, 124, 125, 126, 127, 128, 129}, {139, 138, 137, 136, 135, 134, 133, 132, 131, 130}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        int result = (new LongestIncreasingPathInAMatrix()).longestIncreasingPath(mtrx);

        System.out.println(result);
    }
}
