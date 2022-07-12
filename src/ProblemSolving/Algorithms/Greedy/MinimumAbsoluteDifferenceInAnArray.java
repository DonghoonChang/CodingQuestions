package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class MinimumAbsoluteDifferenceInAnArray {

    class Result {

        /*
         * Complete the 'minimumAbsoluteDifference' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */



        public static int minimumAbsoluteDifference(List<Integer> arr) {
            int max = 1 << 31;
            int min = 1 << 30;

            arr = arr.stream().sorted().collect(Collectors.toList());

            boolean first = true;
            int prev = arr.get(0);
            for(int i: arr){
                if (first){
                    first = false;
                    continue;
                }

                max = Math.max(Math.abs(prev - i), max);
                min = Math.min(Math.abs(prev - i), min);
                prev = i;
            }

            return min;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.minimumAbsoluteDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
