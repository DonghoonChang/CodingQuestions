package ProblemSolving.Algorithms.Sorting;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class LilysHomework {
    class Result {

        /*
         * Complete the 'lilysHomework' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static int lilysHomework(List<Integer> arr) {
            int l = arr.size();
            int[] original = arr.stream().mapToInt(Integer::intValue).toArray();
            int[] sorted = arr.stream().mapToInt(Integer::intValue).toArray();
            Arrays.sort(sorted);

            int[] reverse = new int[l];
            for(int i = 0; i < l; i++){
                reverse[i] = sorted[l - 1 - i];
            }

            int count = countSwaps(original, sorted);
            int countReverse = countSwaps(original, reverse);

            return Math.min(count, countReverse);
        }

        private static int countSwaps(int[] arr1, int[] arr2){
            int swaps = 0;

            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < arr1.length; i++){
                int num1 = arr1[i];
                int num2 = arr2[i];

                if(num1 == num2){
                    continue;
                }

                map.put(num1, num2);
            }

            Set<Integer> traversed = new HashSet();
            int[] to_traverse = map.keySet().stream().mapToInt(Integer::intValue).toArray();
            for(int num: to_traverse){
                int next = num;

                if(traversed.contains(next)){
                    continue;
                }

                while(!traversed.contains(next)){
                    swaps++;
                    traversed.add(next);
                    next = map.get(next);
                }

                swaps--;
            }

            return swaps;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                LilysHomework.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                        LilysHomework.class.getPackageName().replace(".", "/") +
                        "\\Input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
