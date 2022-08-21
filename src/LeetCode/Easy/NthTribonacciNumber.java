package LeetCode.Easy;


/*
Time: 5m
Runtime: 0 ms, faster than 100.00% of Java online submissions for N-th Tribonacci Number.
Memory Usage: 40.7 MB, less than 67.00% of Java online submissions for N-th Tribonacci Number.
*/
public class NthTribonacciNumber {
    public int tribonacci(int n) {
        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        return inner(n, 2, 0, 1, 1);
    }

    private int inner(int n, int curr, int n1, int n2, int n3){
        if(curr == n){
            return n3;
        }

        return inner(n, curr + 1, n2, n3, n1 + n2 + n3);
    }
}
