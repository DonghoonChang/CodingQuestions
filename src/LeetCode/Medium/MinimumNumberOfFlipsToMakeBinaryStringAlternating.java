package LeetCode.Medium;

/*
Runtime: 29 ms, faster than 83.24% of Java online submissions for Minimum Number of Flips to Make the Binary String Alternating.
Memory Usage: 43.4 MB, less than 90.75% of Java online submissions for Minimum Number of Flips to Make the Binary String Alternating.
*/
public class MinimumNumberOfFlipsToMakeBinaryStringAlternating {
    public int minFlips(String s) {
        int l = s.length();

        int[] dp0 = new int[s.length()];
        int[] dp1 = new int[s.length()];
        int min = l;
        for (int i = 0; i < 2; i++){
            int[] dp = i == 0 ? dp0 : dp1;
            int count = 0;

            for (int j = 0; j < s.length(); j++) {
                if((j + i + 1) % 2 == s.charAt(j) - 48){
                    count++;
                }

                dp[j] = count;
            }
        }

        min = Math.min(dp0[l - 1], dp1[l - 1]);

        for(int i = 1; i < l; i++){
            if(!((l - i) % 2 == 0 ^ i % 2 != 1)){
                continue;
            }

            int zero = dp0[i - 1] + (dp1[l - 1] - dp1[i - 1]);
            int one = dp1[i - 1] + (dp0[l - 1] - dp0[i - 1]);

            min = Math.min(min, Math.min(zero, one));
        }

        return min;
    }

    public static void main(String[] args){
        // "001000000010" -> 2
        int result = (new MinimumNumberOfFlipsToMakeBinaryStringAlternating()).minFlips("001000000010");

        System.out.println(result);
    }
}
