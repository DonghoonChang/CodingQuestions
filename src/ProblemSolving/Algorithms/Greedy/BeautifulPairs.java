package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BeautifulPairs {

    class Result {

        /*
         * Complete the 'beautifulPairs' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY A
         *  2. INTEGER_ARRAY B
         */

        public static int beautifulPairs(List<Integer> A, List<Integer> B) {
            Collections.sort(A);
            Collections.sort(B);

            int i = 0, j = 0, count = 0;
            while (i < A.size() && j < B.size()) {
                int a = A.get(i);
                int b = B.get(j);

                if (a == b) {
                    count++;
                    i++;
                    j++;
                } else if (a > b) {
                    j++;
                } else if (a < b) {
                    i++;
                }
            }

            if (count < A.size() && count < B.size()) {
                count++;
            } else if (count == B.size()) {
                count--;
            }

            return count;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> B = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.beautifulPairs(A, B);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

}
