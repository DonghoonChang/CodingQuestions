package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LuckBalance {

    class Result {

        /*
         * Complete the 'luckBalance' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER k
         *  2. 2D_INTEGER_ARRAY contests
         */

        public static int luckBalance(int k, List<List<Integer>> contests) {
            int important = 1;
            List<Integer> importantLucks = new ArrayList<Integer>();
            List<Integer> notImportantLucks = new ArrayList<Integer>();

            for (List<Integer> contest : contests) {
                int importance = contest.get(1);
                int luck = contest.get(0);

                if (importance == important) {
                    importantLucks.add(luck);
                } else {
                    notImportantLucks.add(luck);
                }
            }

            Collections.sort(importantLucks, Collections.reverseOrder());

            int max = notImportantLucks.stream().mapToInt(Integer::intValue).sum();
            max += importantLucks.subList(0, Math.min(importantLucks.size(), k)).stream().mapToInt(Integer::intValue).sum();
            max -= importantLucks.subList(Math.min(importantLucks.size(), k), importantLucks.size()).stream().mapToInt(Integer::intValue).sum();

            return max;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> contests = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                contests.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
