package ProblemSolving.Algorithms.GameTheory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class AliceAndBobsSillyGame {
    class Result {

        /*
         * Complete the 'sillyGame' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts INTEGER n as parameter.
         */
        static int[] memo = new int[100001];
        static List<Integer> primes = new ArrayList<>();

        static boolean inited = false;


        public static String sillyGame(int n) {
            initMemo();
            int numberOfPrimes = memo[n];

            return numberOfPrimes % 2 == 1 ? "Alice" : "Bob";
        }

        private static void initMemo() {
            if (inited) {
                return;
            }

            inited = true;
            for (int i = 2; i <= 100000; i++) {
                boolean isPrime = true;
                for (int prime : primes) {
                    if (i % prime == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    primes.add(i);
                }

                memo[i] = memo[i - 1] + (isPrime ? 1 : 0);
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, g).forEach(gItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                String result = Result.sillyGame(n);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
