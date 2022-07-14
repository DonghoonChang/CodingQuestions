package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Candies {

    class Result {

        /*
         * Complete the 'candies' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER_ARRAY arr
         */

        public static long candies(int n, List<Integer> arr) {
            List<Long> candiesPerKid = arr.stream().map(x -> 0l).collect(Collectors.toList());
            candiesPerKid.set(0, 1l);

            long currentConsecutive = 1;

            // ascending: left to right
            for (int i = 1; i < arr.size(); i++) {
                int prev = arr.get(i - 1);
                int curr = arr.get(i);

                // ascending
                if (prev < curr) {
                    candiesPerKid.set(i, ++currentConsecutive);
                } else {
                    currentConsecutive = 1;
                    candiesPerKid.set(i, 1l);
                }
            }

            currentConsecutive = 1;

            // ascending: right to left
            for (int i = arr.size() - 2; i >= 0; i--) {
                int prev = arr.get(i + 1);
                int curr = arr.get(i);
                long existingCandiesForCurrKid = candiesPerKid.get(i);

                // ascending
                if (prev < curr) {
                    currentConsecutive++;
                    candiesPerKid.set(i, Math.max(existingCandiesForCurrKid, currentConsecutive));
                } else {
                    currentConsecutive = 1;
                }
            }

            long rtn = candiesPerKid.stream().mapToLong(Long::longValue).sum();

            return rtn;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
