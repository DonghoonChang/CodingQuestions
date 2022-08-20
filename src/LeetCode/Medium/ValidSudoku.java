package LeetCode.Medium;

/*
Time: 8m
Runtime: 4 ms, faster than 77.95% of Java online submissions for Valid Sudoku.
Memory Usage: 46.4 MB, less than 65.68% of Java online submissions for Valid Sudoku.
 */
public class ValidSudoku {
    private static int nineFac = 362880;
    public boolean isValidSudoku(char[][] board) {
        // row
        for(int i = 0; i < 9; i++){
            int[] counts = new int[9];
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    continue;
                }

                int ordinal = board[i][j] - 31;
                counts[ordinal]++;
            }

            for(int count: counts){
                if(count > 1){
                    return false;
                }
            }
        }

        // column
        for(int i = 0; i < 9; i++){
            int[] counts = new int[9];
            for(int j = 0; j < 9; j++){
                if(board[j][i] == '.'){
                    continue;
                }

                int ordinal = board[j][i] - 31;
                counts[ordinal]++;
            }

            for(int count: counts){
                if(count > 1){
                    return false;
                }
            }
        }

        //Sub-square
        for(int i = 0; i < 9; i += 3){
            for(int j = 0; j < 9; j += 3){
                int[] counts = new int[9];
                for(int row = i; row < i + 3; row++){
                    for(int col = j; col < j + 3; col++){
                        if(board[row][col] == '.'){
                            continue;
                        }

                        int ordinal = board[row][col] - 31;
                        counts[ordinal]++;
                    }
                }

                for(int count: counts){
                    if(count > 1){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
