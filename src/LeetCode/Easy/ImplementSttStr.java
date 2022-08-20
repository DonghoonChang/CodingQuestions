package LeetCode.Easy;

/*
Time: 1m
Runtime: 0 ms, faster than 100.00% of Java online submissions for Implement strStr().
Memory Usage: 41.5 MB, less than 82.61% of Java online submissions for Implement strStr().
 */
public class ImplementSttStr {
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()){
            return 0;
        }

        return haystack.indexOf(needle);
    }
}
