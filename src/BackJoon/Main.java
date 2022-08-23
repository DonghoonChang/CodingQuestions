package BackJoon;

import java.util.*;

public class Main {
    private static class State {
        int step;
        Ball red, blue;

        public State(int step, Ball red, Ball blue){
            this.step = step;
            this.red = new Ball(red);
            this.blue = new Ball(blue);
        }

        public State(State state){
            this.step = state.step;
            red = new Ball(state.red.r, state.red.c);
            blue = new Ball(state.blue.r, state.blue.c);
        }
    }

    private static class Ball {
        int r, c;
        public Ball(int r, int c){
            this.r = r;
            this.c = c;
        }

        public Ball(Ball ball){
            this.r = ball.r;
            this.c = ball.c;
        }

        public boolean exited(){
            return r == -1 && c == -1;
        }
    }

    private static boolean[][] walls;
    private static int m;
    private static int n;
    private static int solution(int row, int column, char[][] board){
        m = row;
        n = column;
        walls = new boolean[m][n];
        Ball[] balls = init(board);
        Ball initRed = balls[0];
        Ball initBlue = balls[1];
        Ball hole = balls[2];
        Set<String> prev = new HashSet<>();

        PriorityQueue<State> heap = new PriorityQueue<>(Comparator.comparingInt(move -> move.step));
        heap.add(new State(0, initRed, initBlue));

        while(!heap.isEmpty()){
            State current = heap.poll();
            if(current.step == 10){
                continue;
            }

            List<State> nextStates = getNextMoves(current, hole);

            for(State nextState: nextStates){
                if(prev.contains(getCashKey(nextState))){
                    continue;
                }

                // Print Board
//                printGame(m, n, nextState, hole);

                // End Game
                if(nextState.blue.exited()){
                    continue;
                }

                if(nextState.red.exited()){
                    return nextState.step;
                }

                String cacheKey = getCashKey(nextState);
                if(prev.contains(cacheKey)){
                    continue;
                }

                heap.add(nextState);
                prev.add(cacheKey);
            }
        }

        return -1;
    }

    private static String getCashKey(State state){
        return "" + state.red.r + state.red.c + state.blue.r + state.blue.c;
    }

    private static void printGame(int m, int n, State state, Ball hole){
        System.out.print("Step: ");
        System.out.println (state.step);

        char[][] board = new char[m][n];
        for(char[] row: board){
            Arrays.fill(row, '.');
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(walls[i][j]){
                    board[i][j] = '#';
                    continue;
                }
            }
        }

        if(state.red.r != -1){
            board[state.red.r][state.red.c] = 'R';
        }

        if(state.blue.r != -1){
            board[state.blue.r][state.blue.c] = 'B';
        }

        board[hole.r][hole.c] = 'O';

        for(char[] row: board){
            for(char ch: row){
                System.out.print(ch);
            }
            System.out.println();
        }
    }

    private static List<State> getNextMoves(State current, Ball hole){
        List<State> nextStates = new ArrayList<>();
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == j || i == -j){
                    continue;
                }

                int nextRr = current.red.r + i;
                int nextRc = current.red.c + j;
                int nextBr = current.blue.r + i;
                int nextBc = current.blue.c + j;

                if(walls[nextRr][nextRc] && walls[nextBr][nextBc]){
                    continue;
                }

                State next = moveGame(current, hole, j, i);
                nextStates.add(next);
            }
        }

        return nextStates;
    }

    private static State moveGame(State current, Ball hole, int dh, int dv){
        State next = new State(current);
        next.step++;

        Ball head = next.red;
        Ball tail = next.blue;

        if(dh != 0 && (tail.r == head.r) && (tail.c - head.c) / dh > 0){
            head = next.blue;
            tail = next.red;
        }

        if(dv != 0 && (tail.c == head.c) && (tail.r - head.r) / dv > 0){
            head = next.blue;
            tail = next.red;
        }

        moveBall(head, tail, hole, dh, dv);
        moveBall(tail, head, hole, dh, dv);

        return next;
    }

    private static Ball moveBall(Ball ball, Ball other, Ball hole, int dh, int dv){
        int nextR = ball.r;
        int nextC = ball.c;

        while(nextR >= 0 && nextC >= 0 && !walls[nextR][nextC] && (other.r != nextR || other.c != nextC)){
            if(nextR == hole.r && nextC == hole.c){
                ball.r = -1;
                ball.c = -1;
                break;
            }

            ball.r = nextR;
            ball.c = nextC;

            nextR += dv;
            nextC += dh;
        }

        return ball;
    }

    private static Ball[] init(char[][] board){
        Ball[] balls = new Ball[3];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                 if(board[i][j] == 'R'){
                    balls[0] = new Ball(i, j);
                    continue;
                }

                if(board[i][j] == 'B'){
                    balls[1] = new Ball(i, j);
                    continue;
                }

                if(board[i][j] == 'O'){
                    balls[2] = new Ball(i, j);
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
