package Hackerrank.DataStructures.Arrays;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class ArrayManipulation {

    class Result {

        /*
         * Complete the 'arrayManipulation' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. 2D_INTEGER_ARRAY queries
         */

        // Time = O(n)
        // Space = O(n)
        public static long arrayManipulation(int n, List<List<Integer>> queries) {
            long[] values = new long[n];
            for(List<Integer> query: queries){
                int start = query.get(0) - 1;
                int end = query.get(1) - 1;
                long value = query.get(2);

                values[start] += value;
                if (end + 1 < n){
                    values[end + 1] -= value;
                }
            }

            long max = 0;
            long curr = 0;
            for(long val: values){
                curr += val;
                max = Math.max(max, curr);
            }

            return max;
        }

        // Time = O(n * q)
        // Space = O(n)
        public static long arrayManipulationInefficient(int n, List<List<Integer>> queries) {
            // naive
            long[] values = new long[n];
            for(List<Integer> query: queries){
                int start = query.get(0) - 1;
                int end = query.get(1) - 1;
                long value = query.get(2);

                for(int i = start; i <= end; i++){
                    values[i] += value;
                }
            }

            long max = 0;
            for(long val: values){
                max = Math.max(max, val);
            }

            return max;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
