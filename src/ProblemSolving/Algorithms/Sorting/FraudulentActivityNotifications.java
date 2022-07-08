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

            Queue<Integer> trailingExpenditures = new LinkedList<>();

            // O(n): loop X T
            int alerts = 0;
            for(int i = 0; i < expenditure.size(); i++){
                int todayExp = expenditure.get(i);

                // first d days
                if(i < d){
                    trailingExpenditures.add(todayExp);
                    continue;
                }

                double median = getMedian(trailingExpenditures, d);

                if(todayExp >= 2 * median){
                    alerts++;
                }

                // prepare for tomorrow
                trailingExpenditures.remove();
                trailingExpenditures.add(todayExp);
            }

            return alerts;
        }

        private static double getMedian(Queue<Integer> queue, int d){
            int[] expenditures = queue.stream().mapToInt(Integer::intValue).toArray();
            int[] sorted = countSort(expenditures);

            int middleIndex = d / 2;
            int middleValue = sorted[middleIndex];
            int middleValue2 = sorted[middleIndex - 1];

            return (d % 2 == 1) ? middleValue : (middleValue + middleValue2) / 2.0;
        }

        private static int[] countSort(int[] nums){
            int[] counts = new int[maxExpenditure + 1];
            int[] sorted = new int[nums.length];
            for(int num: nums){
                counts[num]++;
            }

            for(int i = 1; i <= maxExpenditure; i++){
                counts[i] = counts[i - 1] + counts[i];
            }

            // usually reverse
            for(int num: nums){
                sorted[--counts[num]] = num;
            }

            return sorted;
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
