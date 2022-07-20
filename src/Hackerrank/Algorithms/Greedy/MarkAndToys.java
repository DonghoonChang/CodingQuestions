package Hackerrank.Algorithms.Greedy;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MarkAndToys {

    class Result {

        /*
         * Complete the 'maximumToys' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY prices
         *  2. INTEGER k
         */

        public static int maximumToys(List<Integer> prices, int k) {
            Collections.sort(prices);

            int count = 0;
            for (int i = 0; i < prices.size(); i++) {
                if (k < prices.get(i)) {
                    return count;
                }

                count++;
                k -= prices.get(i);

            }

            return count;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
