package DataStructures.Stacks;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GamesOfTwoStacks {


    class Result {

        /*
         * Complete the 'twoStacks' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER maxSum
         *  2. INTEGER_ARRAY a
         *  3. INTEGER_ARRAY b
         */

        public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
            int sum = 0;
            int maxCount = 0;
            int i = 0; // a index
            int j = 0;

            while(sum <= maxSum && i < a.size()){
                int nextA = a.get(i);

                if(sum + nextA <= maxSum){
                    sum += nextA;
                    i++;
                    maxCount++;
                    continue;
                }

                break;
            }

            while(sum <= maxSum && j < b.size()){
                int nextB = b.get(j);

                if(sum + nextB <= maxSum){
                    sum += nextB;
                    j++;
                    maxCount++;
                    continue;
                }

                break;
            }

            for(i = i - 1; i >= 0; i--){
                sum -= a.get(i);
                int tempCount = i + j;

                while(sum <= maxSum && j < b.size()){
                    int nextB = b.get(j);

                    if(sum + nextB <= maxSum){
                        sum += nextB;
                        j++;
                        tempCount++;
                        continue;
                    }

                    break;
                }

                maxCount = Math.max(maxCount, tempCount);
            }

            return maxCount;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, g).forEach(gItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int maxSum = Integer.parseInt(firstMultipleInput[2]);

                List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                int result = Result.twoStacks(maxSum, a, b);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
