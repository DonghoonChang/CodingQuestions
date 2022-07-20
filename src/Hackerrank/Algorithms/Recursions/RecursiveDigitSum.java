package Hackerrank.Algorithms.Recursions;


import java.io.*;
import java.math.BigInteger;

public class RecursiveDigitSum {

    class Result {

        /*
         * Complete the 'superDigit' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. STRING n
         *  2. INTEGER k
         */

        public static int superDigit(String n, int k) {
            int sum = 0;
            for(char ch: n.toCharArray()){
                sum += ch - 48;
            }

            String nextString = String.format("%d", sum < 10 ? sum * k : sum);
            int nextK = sum < 10 ? 1 : k;

            if (sum < 10 & k == 1){
                return sum;
            }

            return superDigit(nextString, nextK);
        }

        public static int superDigitMathematical(String n, int k) {
            BigInteger sum = BigInteger.ZERO;

            for (char ch : n.toCharArray()) {
                sum = sum.add(BigInteger.valueOf(ch - 48));
            }

            int result = sum.mod(BigInteger.valueOf(9)).intValue();

            return result == 0 ? 9 : result;
        }

        public static int superDigitNonRecursive(String n, int k) {
            int length = n.length();
            long sum = 0;

            for (int i = 0; i < length; i++) {
                char ch = n.charAt(i);
                sum += ch - 48;
            }

            sum *= k;

            do {
                long temp = sum;
                sum = 0;

                while (temp != 0) {
                    long nextDigit = temp % 10;
                    sum += nextDigit;
                    temp = temp / 10;
                }

            } while (sum > 10);

            return (int) sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String n = firstMultipleInput[0];

        int k = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
