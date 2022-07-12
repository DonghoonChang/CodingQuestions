package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class LargestPermutation {


    class Result {

        /*
         * Complete the 'largestPermutation' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER k
         *  2. INTEGER_ARRAY arr
         */

        public static List<Integer> largestPermutation(int k, List<Integer> arr) {
            if (k >= arr.size() - 1) {
                Collections.sort(arr, Collections.reverseOrder());
                return arr;
            }

            Map<Integer, Integer> elemToIndex = new HashMap<>();
            for (int i = 0; i < arr.size(); i++) {
                elemToIndex.put(arr.get(i), i);
            }

            int max = arr.size();
            int left = 0;
            for (int i = 0; i < k; i++) {
                int maxIndex = elemToIndex.get(max);
                int temp = arr.get(left);

                if(max == temp){
                    left++;
                    max--;
                    i--;
                    continue;
                } else if (max < temp){
                    break;
                }

                arr.set(left, max);
                arr.set(maxIndex, temp);
                elemToIndex.put(temp, maxIndex);
                elemToIndex.put(max, left);

                left++;
                max--;
            }

            return arr;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.largestPermutation(k, arr);

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
