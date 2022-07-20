package Hackerrank.Algorithms.Strings;

import java.io.*;
import java.util.stream.IntStream;

public class SherlockAndAnagrams {

    class Result {

        /*
         * Complete the 'sherlockAndAnagrams' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING s as parameter.
         */

        public static int sherlockAndAnagrams(String s) {
            int length = s.length();
            int ord = 97;
            int charSet = 26;
            int[][] counts = new int[length+1][charSet];

            // Count of letters in the substring of length i starting from index 0
            for(int i = 1; i <= length; i++){
                int chIndex = s.charAt(i - 1) - ord;
                for(int j = 0; j < charSet; j++){
                    counts[i][j] = counts[i-1][j] + (chIndex == j ? 1 : 0);
                }
            }

            //Count letters in the substring
            int total = 0;
            for(int l = 1; l < length; l++){
                for(int first = 0; first < length; first++){
                    for(int second = first + 1; second <= length - l; second++){

                        // get an array of letters count int[charset] by diffing based on indices for each substring
                        int[] substringA = new int[charSet];
                        int[] substringB = new int[charSet];

                        // first
                        for(int ch = 0; ch < charSet; ch++){
                            int[] right = counts[first + l];
                            int[] left = counts[first];

                            substringA[ch] = right[ch] - left[ch];
                        }

                        // second
                        for(int ch = 0; ch < charSet; ch++){
                            int[] right = counts[second + l];
                            int[] left = counts[second];

                            substringB[ch] = right[ch] - left[ch];
                        }

                        // compare
                        boolean match = true;
                        for(int ch = 0; ch < charSet; ch++){
                            if(substringA[ch] != substringB[ch]){
                                match = false;
                            }
                        }

                        total += match ? 1 : 0;
                    }
                }
            }

            return total;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

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
