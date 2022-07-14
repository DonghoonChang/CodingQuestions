package ProblemSolving.Algorithms.Implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CircularArrayRotation {

    class Result {

        /*
         * Complete the 'circularArrayRotation' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY a
         *  2. INTEGER k
         *  3. INTEGER_ARRAY queries
         */

        public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
            Queue<Integer> q = new LinkedList<>(a);
            int leftRotation = a.size() - k % a.size();

            for (int i = 0; i < leftRotation; i++) {
                q.add(q.remove());
            }

            List<Integer> rotated = new ArrayList<Integer>(q);

            List<Integer> rtn = new ArrayList<>();
            for (Integer query : queries) {
                rtn.add(rotated.get(query));
            }

            return rtn;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        int q = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> queries = IntStream.range(0, q).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.circularArrayRotation(a, k, queries);

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
