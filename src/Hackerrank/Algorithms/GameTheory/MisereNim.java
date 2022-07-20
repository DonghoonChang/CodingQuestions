package Hackerrank.Algorithms.GameTheory;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MisereNim {

    class Result {

        /*
         * Complete the 'misereNim' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts INTEGER_ARRAY s as parameter.
         */

        public static String misereNim(List<Integer> s) {
            boolean allIsOne = s.stream().allMatch(p -> p < 2);
            int piles = s.size();

            if (allIsOne) {
                return piles % 2 == 0 ? "First" : "Second";
            }

            int base = 0;
            for (Integer pile : s) {
                base ^= pile;
            }

            return base == 0 ? "Second" : "First";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                String result = Result.misereNim(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
