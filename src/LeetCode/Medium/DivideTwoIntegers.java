package LeetCode.Medium;

/*
Time: 5m
Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
Memory Usage: 41.2 MB, less than 67.21% of Java online submissions for Divide Two Integers.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        long result = (long) dividend / (long) divisor;

        if(result > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }

        if(result < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }
}
