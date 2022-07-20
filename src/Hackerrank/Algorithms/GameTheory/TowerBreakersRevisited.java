package Hackerrank.Algorithms.GameTheory;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TowerBreakersRevisited {

    class Result {

        /*
         * Complete the 'towerBreakers' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */
        static int max = (int) Math.pow(10, 6);
        static boolean init = false;

        public static int towerBreakers(List<Integer> arr) {
            List<Integer> converted = arr.stream().map(l -> countPrimeFactors(l)).collect(Collectors.toList());

            int base = 0;
            for (int c : converted) {
                base ^= c;
            }

            return base != 0 ? 1 : 2;

        }

        private static int countPrimeFactors(int n) {
            int next = n;
            int prime = 2;
            int count = 0;
            while (next != 1 && prime <= next) {
                if (next % prime == 0) {
                    do {
                        count++;
                        next = next / prime;
                    } while (next % prime == 0);
                }

                prime++;
            }

            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                int result = Result.towerBreakers(arr);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
