package LeetCode.Easy;

/*
Start time: 10:48
End time: 10:53

Runtime: 1 ms, faster than 89.34% of Java online submissions for Longest Common Prefix.
Memory Usage: 42.1 MB, less than 58.27% of Java online submissions for Longest Common Prefix.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int shortest = 201;
        for(String str: strs){
            shortest = Math.min(shortest, str.length());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < shortest; i++){
            boolean identical = true;
            char ch = strs[0].charAt(i);

            for(int j = 0; j < strs.length; j++){
                char comp = strs[j].charAt(i);

                if(comp != ch){
                    identical = false;
                }
            }

            if(!identical){
                break;
            }

            sb.append(ch);
        }

        return sb.toString();
    }
}
