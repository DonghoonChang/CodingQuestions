package BackJoon;

import java.util.*;

public class Main {
    private static boolean[][] walls;
    private static int m;
    private static int n;
    private static int solution(int row, int column, char[][] board){
        m = row;
        n = column;
        walls = new boolean[m][n];
        Integer[][] balls = init(board);
        Integer[] initRed = balls[0];
        Integer[] initBlue = balls[1];
        Integer[] hole = balls[2];
        Set<Integer> prev = new HashSet<>();

        PriorityQueue<Integer[]> heap = new PriorityQueue<>(Comparator.comparingInt(move -> move[0]));
        heap.add(new Integer[]{0, initRed[0], initRed[1], initBlue[0], initBlue[1]});

        while(!heap.isEmpty()){
            Integer[] current = heap.poll();
            if(current[0] == 10){
                continue;
            }

            List<Integer[]> nextStates = getNextMoves(current, hole);

            for(Integer[] nextState: nextStates){
                if(prev.contains(getCashKey(nextState))){
                    continue;
                }

                if(nextState[3] == -1){
                    continue;
                }

                if(nextState[1] == -1){
                    return nextState[0];
                }

                Integer cacheKey = getCashKey(nextState);
                if(prev.contains(cacheKey)){
                    continue;
                }

                heap.add(nextState);
                prev.add(cacheKey);
            }
        }

        return -1;
    }

    private static int getCashKey(Integer[] state){
        return 1000 * state[1] + 100 * state[2] + 10 * state[3] + state[4];
    }

    private static List<Integer[]> getNextMoves(Integer[] current, Integer[] hole){
        List<Integer[]> nextStates = new ArrayList<>();
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == j || i == -j){
                    continue;
                }

                if(walls[current[1] + i][current[2] + j] && walls[current[3] + i][current[4] + j]){
                    continue;
                }

                Integer[] next = moveGame(current, hole, j, i);
                nextStates.add(next);
            }
        }

        return nextStates;
    }

    private static Integer[] moveGame(Integer[] current, Integer[] hole, int dh, int dv){
        Integer[] next = new Integer[]{current[0], current[1], current[2], current[3], current[4]};
        next[0]++;

        boolean redFirst = true;
        if(dh != 0 && (current[3] == current[1]) && (current[4] - current[2]) / dh > 0){
            redFirst = false;
        }

        if(dv != 0 && (current[4] == current[2]) && (current[3] - current[1]) / dv > 0){
            redFirst = false;
        }

        if(redFirst){
            moveBall(current, 1, 2, 3, 4, hole, dh, dv);
            moveBall(current, 3, 4, 1, 2, hole, dh, dv);
        } else {
            moveBall(current, 3, 4, 1, 2, hole, dh, dv);
            moveBall(current, 1, 2, 3, 4, hole, dh, dv);
        }

        return next;
    }

    private static void moveBall(Integer[] state, int thisR, int thisC, int otherR, int otherC, Integer[] hole, int dh, int dv){
        int nextR = state[thisR];
        int nextC = state[thisC];
        int otherRLoc = state[otherR];
        int otherCLoc = state[otherC];

        while(!walls[nextR][nextC] && (otherRLoc != nextR || otherCLoc != nextC)){
            if(nextR == hole[0] && nextC == hole[1]){
                state[thisR] = -1;
                state[thisC] = -1;
                break;
            }

            state[thisR] = nextR;
            state[thisC] = nextC;

            nextR += dv;
            nextC += dh;
        }
    }

    private static Integer[][] init(char[][] board){
        Integer[][] balls = new Integer[3][2];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                 if(board[i][j] == 'R'){
                    balls[0] = new Integer[]{i, j};
                    continue;
                }

                if(board[i][j] == 'B'){
                    balls[1] = new Integer[]{i, j};
                    continue;
                }

                if(board[i][j] == 'O'){
                    balls[2] = new Integer[]{i, j};
                    continue;
                }

                if(board[i][j] == '#'){
                    walls[i][j] = true;
                    continue;
                }
            }
        }

        return balls;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();

        char[][] board = new char[m][n];
        for(int i = 0; i < m; i++){
            String line = sc.nextLine();
            board[i] = line.toCharArray();
        }

        int result = (new Main()).solution(m, n, board);

        System.out.println(result);
    }
}
