package Hackerrank.DataStructures.Stacks;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class BalancedBrackets {
    class Result {

        /*
         * Complete the 'isBalanced' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING s as parameter.
         */

        static char curlyLeft = '{';
        static char curlyRight = '}';
        static char squareLeft = '[';
        static char squareRight = ']';
        static char roundLeft = '(';
        static char roundRight = ')';

        static Set<Character> left = new HashSet<>();
        static Set<Character> right = new HashSet<>();
        static Map<Character, Character> pairs = new HashMap<Character, Character>();

        public static String isBalanced(String s) {
            init();

            Stack<Character> stack = new Stack<Character>();

            for (char ch : s.toCharArray()) {
                // opening a block
                if (isLeft(ch)) {
                    stack.push(ch);
                    continue;
                }

                if(stack.empty()){
                    return "NO";
                }

                char lastBracket = stack.pop();
                if (pairs.get(lastBracket) == ch) {
                    continue;
                }

                return "NO";
            }

            if(stack.empty()){
                return "YES";
            }

            return "NO";
        }

        private static void init() {
            left.add(curlyLeft);
            left.add(squareLeft);
            left.add(roundLeft);

            right.add(curlyRight);
            right.add(squareRight);
            right.add(roundRight);

            pairs.put(curlyLeft, curlyRight);
            pairs.put(squareLeft, squareRight);
            pairs.put(roundLeft, roundRight);
        }

        private static boolean isLeft(char bracket) {
            return left.contains(bracket);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
