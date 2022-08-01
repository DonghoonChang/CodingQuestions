package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// start time: 5:05
// end time: 5:11
/*
Runtime: 21 ms, faster than 30.71% of Java online submissions for Group Anagrams.
Memory Usage: 60.9 MB, less than 17.28% of Java online submissions for Group Anagrams.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str: strs){
            int[] counts = countChars(str);
            String key = convertCountsToKey(counts);

            if(map.containsKey(key)){
                map.get(key).add(str);
            } else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        List<List<String>> rtn = new ArrayList<>();
        for(List<String> group: map.values()){
            rtn.add(group);
        }

        return rtn;
    }

    private int[] countChars(String s){
        int[] counts = new int[26];
        for(char ch: s.toCharArray()){
            counts[ch - 97]++;
        }

        return counts;
    }

    private String convertCountsToKey(int[] counts){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            char ch = (char) (counts[i] + 97);
            sb.append(ch);
            sb.append(i);
        }

        return sb.toString();
    }

    public static void main(String[] args){
        String str = "bbbbbbbbbbc";
        GroupAnagrams sln = new GroupAnagrams();
        int[] counts = sln.countChars(str);
        String key = (new GroupAnagrams()).convertCountsToKey(counts);

        System.out.println(key);
    }
}
