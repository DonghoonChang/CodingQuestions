package LeetCode.Easy;

/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
Memory Usage: 41 MB, less than 32.82% of Java online submissions for Fibonacci Number.
 */
public class FibonacciNumber {
    public int fib(int n) {
        if(n == 0){
            return 0;
        }

        return inner(n, 1, 0, 1);
    }

    private int inner(int n, int curr, int n1, int n2){
        if(curr == n){
            return n2;
        }

        return inner(n, curr + 1, n2, n1 + n2);
    }
}
