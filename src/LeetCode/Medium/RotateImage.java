package LeetCode.Medium;

/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
    Memory Usage: 43 MB, less than 37.73% of Java online submissions for Rotate
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        upDownReflection(matrix);
        diagonalReflection(matrix);
        // 90 degrees rotation = - reflection + \ reflection

    }

    // around r = c axis
    private void diagonalReflection(int[][] matrix){
        int m = matrix.length;
        for(int r = 0; r < m; r++){
            for(int c = r + 1; c < m; c++){
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }
    }

    private void upDownReflection(int[][] matrix){
        int m = matrix.length;
        int middle = m / 2;
        for(int r = 0; r < middle; r++){
            int otherR = (m - 1 - r);
            for(int c = 0; c < m; c++){
                int temp = matrix[otherR][c];
                matrix[otherR][c] = matrix[r][c];
                matrix[r][c] = temp;
            }
        }
    }

    public static void main(String[] args){
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        (new RotateImage()).rotate(matrix);

        System.out.println(matrix);
    }
}
