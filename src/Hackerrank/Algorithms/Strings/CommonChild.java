package Hackerrank.Algorithms.Strings;

import java.io.*;

import static java.util.stream.Collectors.joining;

// Resource: https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

public class CommonChild {
    class Result {

        /*
         * Complete the 'commonChild' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. STRING s1
         *  2. STRING s2
         */

        public static int commonChild(String s1, String s2) {
            int s1L = s1.length();
            int s2L = s2.length();

            int[][] lookup = new int[s1L + 1][s2L + 1];

            int max = 0;
            for(int i = 1; i <= s1L; i++){
                for(int j = 1; j <= s2L; j++){
                    if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                        lookup[i][j] = lookup[i - 1][j - 1] + 1;
                    } else {
                        lookup[i][j] = Math.max(lookup[i - 1][j], lookup[i][j - 1]);
                    }

                    if (lookup[i][j] > max){
                        max = lookup[i][j];
                    }
                }
            }


            return max;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = Result.commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
