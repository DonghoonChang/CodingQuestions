package LeetCode.Medium;

import java.util.Stack;

/*
Time: 10m
Runtime: 10 ms, faster than 45.29% of Java online submissions for Evaluate Reverse Polish Notation.
Memory Usage: 44.6 MB, less than 29.04% of Java online submissions for Evaluate Reverse Polish Notation.
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (!"*".equals(token) && !"/".equals(token) && !"+".equals(token) && !"-".equals(token)) {
                stack.push(token);
                continue;
            }

            int num2 = Integer.parseInt(stack.pop());
            int num1 = Integer.parseInt(stack.pop());
            switch (token) {
                case "*":
                    stack.push("" + (num1 * num2));
                    continue;
                case "/":
                    stack.push("" + (num1 / num2));
                    continue;
                case "+":
                    stack.push("" + (num1 + num2));
                    continue;
                case "-":
                    stack.push("" + (num1 - num2));
                    continue;
            }
        }

        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        String[] rpns = new String[]{"4", "13", "5", "/", "+"};
        int result = (new EvaluateReversePolishNotation()).evalRPN(rpns);

        System.out.println(result);
    }
}
