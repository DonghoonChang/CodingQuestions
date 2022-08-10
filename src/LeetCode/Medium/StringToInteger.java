package LeetCode.Medium;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/*
Start time: 8:50
End time: 9:27
Runtime: 13 ms, faster than 12.22% of Java online submissions for String to Integer (atoi).
Memory Usage: 43 MB, less than 56.38% of Java online submissions for String to Integer (atoi).
 */
public class StringToInteger {
    public int myAtoi(String s) {
        Set<Character> signSet = new HashSet<>();
        signSet.add('+');
        signSet.add('-');
        Set<Character> numSet = new HashSet<>();
        numSet.add('0');
        numSet.add('1');
        numSet.add('2');
        numSet.add('3');
        numSet.add('4');
        numSet.add('5');
        numSet.add('6');
        numSet.add('7');
        numSet.add('8');
        numSet.add('9');


        int i = 0, j = 0;
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }

        while(i + j < s.length() && signSet.contains(s.charAt(i + j))){
            j++;
        }

        while(i + j < s.length() && numSet.contains(s.charAt(i + j))){
            j++;
        }

        String number = s.substring(i, i + j);
        if(!number.matches("[+-]{0,1}\\d+")
        ){
            return 0;
        };

        BigInteger l = new BigInteger(number);

        if(BigInteger.valueOf(Integer.MAX_VALUE).compareTo(l) == -1){
            return Integer.MAX_VALUE;
        }

        if(BigInteger.valueOf(Integer.MIN_VALUE).compareTo(l) == 1){
            return Integer.MIN_VALUE;
        }

        return l.intValue();
    }

    public static void main(String[] args){
        (new StringToInteger()).myAtoi("20000000000000000000");
    }
}
