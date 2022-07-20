package Hackerrank.Algorithms.Greedy;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class JimAndTheOrders {

    class Result {

        /*
         * Complete the 'jimOrders' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts 2D_INTEGER_ARRAY orders as parameter.
         */

        public static List<Integer> jimOrders(List<List<Integer>> orders) {
            // sort by order # + prep time
            // link customer numbers to the numbers above

            Map<Integer, List<Integer>> customerIdsByTotalTime = new HashMap<>();
            Set<Integer> totalTimes = new HashSet<>();

            for (int customerId = 0; customerId < orders.size(); customerId++) {
                List<Integer> order = orders.get(customerId);
                int orderNumber = order.get(0);
                int prepTime = order.get(1);
                int totalTime = orderNumber + prepTime;

                if (customerIdsByTotalTime.containsKey(totalTime)) {
                    customerIdsByTotalTime.get(totalTime).add(customerId + 1);
                } else {
                    List<Integer> lst = new ArrayList<>();
                    lst.add(customerId + 1);
                    customerIdsByTotalTime.put(totalTime, lst);
                }

                totalTimes.add(totalTime);
            }

            Integer[] sortedTotalTimes = totalTimes.toArray(new Integer[0]);
            Arrays.sort(sortedTotalTimes);

            List<Integer> rtn = new ArrayList<Integer>();
            for (int totalTime : sortedTotalTimes) {
                rtn.addAll(customerIdsByTotalTime.get(totalTime));
            }

            return rtn;

        }

    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> orders = new ArrayList<>();

            IntStream.range(0, n).forEach(i -> {
                try {
                    orders.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<Integer> result = Result.jimOrders(orders);

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

}

