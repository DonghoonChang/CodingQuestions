package LeetCode.Medium;

public class MaximalSquare {
    private static int globalMax = 0;
    private static int[][] memo;
    public int maximalSquare(char[][] matrix) {
        globalMax = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] mtrx = new int[m][n];
        memo = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                memo[i][j] = -1;

                mtrx[i][j] = matrix[i][j] - 48;
            }
        }

        inner(mtrx, 0, 0);

        return globalMax;

    }

    private int inner(int[][] matrix, int r, int c){
        if(memo[r][c] != -1){
            return memo[r][c];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int right = c == n - 1 ? 0 : inner(matrix, r, c + 1);
        int bttm = r == m - 1 ? 0 : inner(matrix, r + 1, c);
        int bttmR = r == m - 1 || c == n - 1 ? 0 : inner(matrix, r + 1, c + 1);
        int surrounds = Math.min(Math.min(right, bttm), bttmR);
        int origin = matrix[r][c];

        int localMax = -1;
        if(origin == 0){
            localMax = 0;
        } else if(surrounds == 0){
            localMax = 1;
        } else {
            localMax = origin + surrounds;
        }

        memo[r][c] = localMax;
        globalMax = Math.max(globalMax, (int) Math.pow(localMax, 2));

        return localMax;
    }

    public static void main(String[] args){
//        char[][] chars = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] chars = new char[][]{{'0','1'},{'1','0'}};
        int result = (new MaximalSquare()).maximalSquare(chars);

        System.out.println(result);
    }
}
