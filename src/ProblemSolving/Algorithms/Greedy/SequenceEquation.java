package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SequenceEquation {

    class Result {

        /*
         * Complete the 'permutationEquation' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts INTEGER_ARRAY p as parameter.
         */

        public static List<Integer> permutationEquation(List<Integer> p) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < p.size(); i++) {
                map.put(p.get(i), i + 1);
            }

            List<Integer> rtn = new ArrayList<>();
            for (int i = 1; i <= p.size(); i++) {
                int first = map.get(i);
                int second = map.get(first);

                rtn.add(second);
            }

            return rtn;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.permutationEquation(p);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

}
