package Hackerrank.Algorithms.Recursions;

import java.io.*;

public class ThePowerSum {

    class Result {

        /*
         * Complete the 'powerSum' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER X
         *  2. INTEGER N
         */

        public static int powerSum(int X, int N) {

            return powerSum(X, X, N, 1);
        }

        public static int powerSum(int X, int remaining, int N, int start) {
            if (remaining == 0) {
                return 1;
            }

            if (remaining == -1) {
                return 0;
            }

            int count = 0;
            int max = findNthRootFloor(remaining, N);

            for (int i = start; i <= max; i++) {
                count += powerSum(X, remaining - (int) Math.pow(i, N), N, i + 1);
            }

            return count;
        }

        private static int findNthRootFloor(int X, int N){
            int i = 0;
            while (Math.pow(i + 1, N) <= X){
                i++;
            }

            return i;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(bufferedReader.readLine().trim());

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
