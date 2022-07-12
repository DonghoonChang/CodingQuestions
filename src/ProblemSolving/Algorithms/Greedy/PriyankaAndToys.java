package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PriyankaAndToys {


    class Result {

        /*
         * Complete the 'toys' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY w as parameter.
         */

        public static int toys(List<Integer> w) {
            Collections.sort(w);

            int minThisContainer = -5;
            int containerCount = 0;
            for (Integer weight : w) {
                if (weight <= minThisContainer + 4) {
                    continue;
                } else {
                    containerCount++;
                    minThisContainer = weight;
                }
            }

            return containerCount;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> w = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(toList());

        int result = Result.toys(w);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
