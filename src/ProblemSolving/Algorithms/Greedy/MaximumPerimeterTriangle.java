package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class MaximumPerimeterTriangle {

    class Result {

        /*
         * Complete the 'maximumPerimeterTriangle' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts INTEGER_ARRAY sticks as parameter.
         */

        public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks) {
            List<Integer> sides = new ArrayList<>();
            Collections.sort(sticks, Collections.reverseOrder());

            for (int i = 0; i < sticks.size() - 2; i++) {
                int a = sticks.get(i);
                int b = sticks.get(i + 1);
                int c = sticks.get(i + 2);

                if (a < b + c) {
                    sides.add(c);
                    sides.add(b);
                    sides.add(a);

                    return sides;
                }
            }

            sides.add(-1);
            return sides;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> sticks = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.maximumPerimeterTriangle(sticks);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
