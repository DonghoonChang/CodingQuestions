package ProblemSolving.Algorithms.GameTheory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IntroductionToNimGame {

    class Result {

        /*
         * Complete the 'nimGame' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts INTEGER_ARRAY pile as parameter.
         */

        public static String nimGame(List<Integer> pile) {
            int outcome = 0;
            for (Integer p : pile) {
                outcome ^= p;
            }

            if (outcome == 0) {
                return "Second";
            }

            return "First";

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            String[] pileTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> pile = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int pileItem = Integer.parseInt(pileTemp[i]);
                pile.add(pileItem);
            }

            String result = Result.nimGame(pile);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
