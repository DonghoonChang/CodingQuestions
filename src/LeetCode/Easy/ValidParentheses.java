package LeetCode.Easy;

import java.util.*;

/*
start: 12:56
end: 1:08
Runtime: 5 ms, faster than 15.38% of Java online submissions for Valid Parentheses.
Memory Usage: 42.2 MB, less than 33.29% of Java online submissions for Valid Parentheses.
 */
public class ValidParentheses {
    public static Map<Character, Character> map = new HashMap<>() {
        {
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }
    };

    public boolean isValid(String s) {
        Set<Character> open = map.keySet();
        Stack<Character> stack = new Stack<>();

        for(char ch: s.toCharArray()){
            // open
            if(open.contains(ch)){
                stack.add(ch);
                continue;
            }

            // close
            char nextOpen = stack.isEmpty() ? 0 : stack.pop();
            char expected = nextOpen == 0 ? 0 : map.get(nextOpen);

            if(ch != expected){
                return false;
            }
        }

        return stack.isEmpty();
    }
}
