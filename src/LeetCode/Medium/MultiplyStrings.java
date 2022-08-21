package LeetCode.Medium;

/*
Time: 56m
Runtime: 5 ms, faster than 89.39% of Java online submissions for Multiply Strings.
Memory Usage: 42.4 MB, less than 86.08% of Java online submissions for Multiply Strings.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }

        int[] ans = new int[num1.length() + num2.length() - 1];

        for(int i = 0; i < num1.length(); i++){
            for (int j = 0; j < num2.length(); j++){
                ans[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        for (int i = ans.length - 1; i > 0; i--){
            ans[i - 1] += ans[i] / 10;
            ans[i] = ans[i] % 10;
        }

        StringBuilder sb = new StringBuilder();
        for(int i: ans){
            sb.append(i);
        }

        return sb.toString();
    }
}
