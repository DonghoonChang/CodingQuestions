package LeetCode.Medium;

/*
    Start time: 8:23
    End time: 8:35
    Runtime: 2 ms, faster than 78.73% of Java online submissions for Reverse Integer.
    Memory Usage: 39.9 MB, less than 86.51% of Java online submissions for Reverse Integer.
 */
public class ReverseInteger {
    public int reverse(int x) {
        StringBuilder sb = new StringBuilder();

        if(x < 0){
            sb.append("-");

            if(x == Integer.MIN_VALUE){
                x = Integer.MAX_VALUE;
            } else {
                x *= -1;
            }
        }

        do {
            int lastDigit = x % 10;
            x /= 10;

            sb.append(lastDigit);
        } while(x != 0);



        long rtn = Long.parseLong(sb.toString());

        if(rtn > Integer.MAX_VALUE || rtn < Integer.MIN_VALUE){
            return 0;
        }

        return (int) rtn;
    }

    public static void main(String[] args){
        (new ReverseInteger()).reverse(-2147483648);
    }
}
