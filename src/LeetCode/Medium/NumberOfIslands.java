package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

// start time : 5: 15
//  end  time:  5: 30
/*
    Runtime: 9 ms, faster than 24.19% of Java online submissions for Number of Islands.
    Memory Usage: 56.5 MB, less than 73.58% of Java online submissions for Number of Islands.
 */
public class NumberOfIslands {
    private static char land = '1';
    private static char water = '0';

    private List<int[]> directions;

    public int numIslands(char[][] grid) {
        init();
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] memo = new boolean[m][n];

        int count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!memo[i][j] && grid[i][j] == land){// undiscovered land
                    count++;
                    mark(grid, memo, i, j, m , n);
                }
            }
        }

        return count;
    }

    private void init(){
        directions = new ArrayList<>();
        directions.add(new int[]{-1, 0});
        directions.add(new int[]{1, 0});
        directions.add(new int[]{0, 1});
        directions.add(new int[]{0, -1});
    }

    private void mark(char[][] grid, boolean[][] memo, int r, int c, int m, int n){
        if(memo[r][c]){
            return;
        }

        memo[r][c] = true;

        if(grid[r][c] == land){
            for(int[] dir: directions){
                int nextR = r + dir[0];
                int nextC = c + dir[1];

                if(nextR < 0 || nextR >= m || nextC < 0 || nextC >= n){
                    continue;
                }

                if(grid[nextR][nextC] == land){
                    mark(grid, memo, nextR, nextC, m, n);
                }
            }
        }
    }

    public static void main(String[] args){
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int result = (new NumberOfIslands()).numIslands(grid);

        System.out.println(result);
    }
}
