package ProblemSolving.Algorithms.Sorting;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class FraudulentActivityNotifications {

    class Result {

        private static int maxExpenditure = 200;

        // 1. Queue
        // 2. Counting sort
        public static int activityNotifications(List<Integer> expenditure, int d) {

            int[] trailingExpenditures = new int[maxExpenditure + 1];

            // O(n): loop X T
            int alerts = 0;
            for (int i = 0; i < expenditure.size(); i++) {
                int todayExp = expenditure.get(i);

                // first d days
                if (i < d) {
                    trailingExpenditures[todayExp]++;
                    continue;
                }

                int median = getTwiceMedian(trailingExpenditures, d);

                if (todayExp >= median) {
                    alerts++;
                }

                // prepare for tomorrow
                int oldestExp = expenditure.get(i - d);
                trailingExpenditures[oldestExp]--;
                trailingExpenditures[todayExp]++;
            }

            return alerts;
        }

        private static int getTwiceMedian(int[] trailingExpenditures, int d) {
            int[] cumulativeCount = new int[maxExpenditure + 1];

            for (int i = 0; i <= maxExpenditure; i++) {
                if (i == 0) {
                    cumulativeCount[i] = trailingExpenditures[0];
                    continue;
                }

                cumulativeCount[i] = cumulativeCount[i - 1] + trailingExpenditures[i];
            }


            if (d % 2 == 1) {
                int middleCount = d / 2 + 1;
                for (int i = 1; i <= maxExpenditure; i++) {
                    int currCount = cumulativeCount[i];

                    if (currCount >= middleCount) {
                        return 2 * i;
                    }
                }
            } else {
                int middleACount = d / 2;
                int middleBCount = d / 2 + 1;
                int medianA = -1;
                int medianB = -1;

                for (int i = 1; i <= maxExpenditure; i++) {
                    int currCount = cumulativeCount[i];

                    if (medianA == -1 && currCount >= middleACount) {
                        medianA = i;
                    }

                    if (medianA != -1 && currCount >= middleBCount) {
                        medianB = i;
                        break;
                    }
                }

                return medianA + medianB;
            }

            // should not be reachable
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
