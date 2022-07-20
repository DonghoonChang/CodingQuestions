package Hackerrank.Algorithms.Greedy;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MarcsCakewalk {
    class Result {

        /*
         * Complete the 'marcsCakewalk' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts INTEGER_ARRAY calorie as parameter.
         */

        public static long marcsCakewalk(List<Integer> calorie) {
            List<Integer> calories = calorie.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
            long miles = 0;
            int power = 0;
            int base = 2;

            for (Integer cal : calories) {
                miles += Math.pow(base, power++) * cal;
            }


            return miles;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> calorie = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.marcsCakewalk(calorie);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
