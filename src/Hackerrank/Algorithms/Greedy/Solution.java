package Hackerrank.Algorithms.Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Solution {

    class Result {

        /*
         * Complete the 'gridChallenge' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING_ARRAY grid as parameter.
         */

        public static String gridChallenge(List<String> grid) {
            int r = grid.size();
            int c = grid.get(0).length();

            for (int i = 0; i < r; i++) {
                char[] sorted = grid.get(i).toCharArray();
                Arrays.sort(sorted);

                String str = new String(sorted);
                grid.set(i, str);
            }


            for (int i = 0; i < r - 1; i++) {
                for (int j = 1; j < c; j++) {
                    char first = grid.get(i).charAt(j);
                    char second = grid.get(i + 1).charAt(j);

                    if (first > second) {
                        return "NO";
                    }
                }
            }

            return "YES";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                String result = Result.gridChallenge(grid);

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
