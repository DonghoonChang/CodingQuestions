package LeetCode.Medium;

import java.util.HashSet;
import java.util.Set;

// start time: 10: 11
// end time: 10:22

/*
    Runtime: 12 ms, faster than 58.86% of Java online submissions for Longest Substring Without Repeating Characters.
    Memory Usage: 46.9 MB, less than 19.15% of Java online submissions for Longest Substring Without Repeating Characters.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }

        int max = 1;
        int i = 0, j = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(i));
         while(j < s.length()){
            char ch = s.charAt(j);

            if(set.contains(ch)){
                set.remove(s.charAt(i++));

            } else{
                set.add(ch);
                j++;
            }

            max = Math.max(max, j - i);
        }

        return max;
    }

    public static void main(String[] args){
        String str = "pwwkew";
        int result = (new LongestSubstringWithoutRepeatingCharacters()).lengthOfLongestSubstring(str);

        System.out.println(result);
    }
}
