package Hackerrank.Algorithms.Search;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class KnightLOnAChessboard {

    class Result {

        private static List<int[]> moves = new ArrayList<>();

        public static List<List<Integer>> knightlOnAChessboard(int n) {
            List<List<Integer>> results = new ArrayList<>();
            moves.add(new int[]{+1, +1});
            moves.add(new int[]{+1, -1});
            moves.add(new int[]{-1, +1});
            moves.add(new int[]{-1, -1});

            for(int i = 1; i < n; i++){
                List<Integer> rowResults = new ArrayList<>();
                for(int j = 1; j < n; j++){
                    int[][] board = new int[n][n];
                    BFS(board, n, 0, 0, i, j, 0);

                    int result = board[n -1][n - 1];
                    rowResults.add(result == 0? -1: result);
                }

                results.add(rowResults);
            }

            return results;
        }

        private static void BFS(int[][] board, int n, int x, int y, int a, int b, int count) {
            List<int[]> nextNodes = new ArrayList<>();

            for (int[] move : moves) {
                int v = a;
                int h = b;
                for(int t = 0; t <= 1; t++){
                    if (t == 1) {
                        v = b;
                        h = a;
                    }

                    int i = move[0];
                    int j = move[1];

                    // next loc
                    int nextX = x + i * v;
                    int nextY = y + j * h;

                    if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
                        continue;
                    }

                    // this loc was not found before
                    // or it can be reached cheaper
                    int currMinMovesToNextNode = board[nextX][nextY];
                    if (currMinMovesToNextNode == 0 || currMinMovesToNextNode > count + 1) {
                        board[nextX][nextY] = count + 1;
                        nextNodes.add(new int[]{nextX, nextY});
                    }

                for (int[] node : nextNodes) {
                    BFS(board, n, node[0], node[1], a, b, count + 1);
                }
            }
        }
    }
}

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> result = Result.knightlOnAChessboard(n);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
