package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GoodlandElectricity {

    class Result {

        /*
         * Complete the 'pylons' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER k
         *  2. INTEGER_ARRAY arr
         */

        public static int pylons(int k, List<Integer> arr) {
            boolean[] pylonLocations = new boolean[arr.size()];
            int[] pylonCoverage = new int[arr.size()];

            for (int pylon = 0; pylon < arr.size(); pylon++) {
                // can I place pylon here?
                if (arr.get(pylon) == 0) {
                    continue;
                }

                pylonLocations[pylon] = true;
                for (int r = -k + 1; r < k; r++) {
                    int target = pylon + r;

                    if (target >= arr.size() || target < 0) {
                        continue;
                    }

                    pylonCoverage[target]++;
                }
            }

            for (int coverage : pylonCoverage) {
                if (coverage < 1) {
                    return -1;
                }
            }

            for (int pylon = 0; pylon < pylonLocations.length; pylon++) {
                // is a pylon here?
                if (!pylonLocations[pylon]) {
                    continue;
                }

                // reduce pylon coverage within its pylon
                boolean isThisPylonRemovable = true;
                for (int r = -k + 1; r < k; r++) {
                    int target = pylon + r;

                    if (target >= arr.size() || target < 0) {
                        continue;
                    }

                    if (pylonCoverage[target] < 2) {
                        isThisPylonRemovable = false;
                        break;
                    }
                }

                if (isThisPylonRemovable) {
                    pylonLocations[pylon] = false;
                    for (int r = -k + 1; r < k; r++) {
                        int target = pylon + r;
                        if (target >= arr.size() || target < 0) {
                            continue;
                        }

                        pylonCoverage[target]--;
                    }
                }
            }

            int pylonCount = 0;
            for (boolean isPylonHere : pylonLocations) {
                if (isPylonHere) {
                    pylonCount++;
                }
            }

            return pylonCount;
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

        int result = Result.pylons(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
