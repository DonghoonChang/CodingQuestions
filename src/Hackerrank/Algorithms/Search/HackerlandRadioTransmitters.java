package Hackerrank.Algorithms.Search;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class HackerlandRadioTransmitters {

    class Result {

        /*
         * Complete the 'hackerlandRadioTransmitters' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY x
         *  2. INTEGER k
         */

        public static int HackerlandRadioTransmitters(List<Integer> x, int k) {
            int count_radio = 0;
            Integer[] sorted = x.stream().toArray(Integer[]::new);
            Arrays.sort(sorted);

            int range_actual_right = -1;
            int radio_actual_loc = -1;
            int radio_max_loc = -1;
            for(int loc: sorted){
                if (loc <= radio_max_loc){
                    radio_actual_loc = loc;
                    range_actual_right = radio_actual_loc + k;
                    continue;
                }

                if (loc <= range_actual_right){
                    continue;
                }

                count_radio++;
                radio_max_loc = loc + k;
                radio_actual_loc = loc;
                range_actual_right = radio_actual_loc + k;
                continue;
            }

            return count_radio;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.HackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

