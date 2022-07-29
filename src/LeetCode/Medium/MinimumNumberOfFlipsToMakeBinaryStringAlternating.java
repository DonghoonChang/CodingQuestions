package LeetCode.Medium;


public class MinimumNumberOfFlipsToMakeBinaryStringAlternating {
    /*
Runtime: 29 ms, faster than 83.24% of Java online submissions for Minimum Number of Flips to Make the Binary String Alternating.
Memory Usage: 43.4 MB, less than 90.75% of Java online submissions for Minimum Number of Flips to Make the Binary String Alternating.
*/
    public int minFlips(String s) {
        int l = s.length();

        int[] dp0 = new int[s.length()];
        int[] dp1 = new int[s.length()];
        int min = l;
        for (int i = 0; i < 2; i++) {
            int[] dp = i == 0 ? dp0 : dp1;
            int count = 0;

            for (int j = 0; j < s.length(); j++) {
                if ((j + i + 1) % 2 == s.charAt(j) - 48) {
                    count++;
                }

                dp[j] = count;
            }
        }

        min = Math.min(dp0[l - 1], dp1[l - 1]);

        for (int i = 1; i < l; i++) {
            if (!((l - i) % 2 == 0 ^ i % 2 != 1)) {
                continue;
            }

            int zero = dp0[i - 1] + (dp1[l - 1] - dp1[i - 1]);
            int one = dp1[i - 1] + (dp0[l - 1] - dp0[i - 1]);

            min = Math.min(min, Math.min(zero, one));
        }

        return min;
    }

    /*
Runtime: 31 ms, faster than 79.77% of Java online submissions for Minimum Number of Flips to Make the Binary String Alternating.
Memory Usage: 44.3 MB, less than 84.97% of Java online submissions for Minimum Number of Flips to Make the Binary String Alternating.
*/
    public int minFlipsSecond(String s) {
        String ss = s + s;
        int l = s.length();
        int ll = ss.length();

        int[] dp0 = new int[ll];
        int[] dp1 = new int[ll];
        int min = l;
        int count0 = 0;
        int count1 = 0;

        for (int i = 0; i < ll; i++) {
            if (i % 2 == 0) {
                if (ss.charAt(i) == '0') {
                    count1++;
                }

                if (ss.charAt(i) == '1') {
                    count0++;
                }
            }

            if (i % 2 == 1) {
                if (ss.charAt(i) == '0') {
                    count0++;
                }

                if (ss.charAt(i) == '1') {
                    count1++;
                }
            }

            dp0[i] = count0;
            dp1[i] = count1;
        }

        for (int i = 1; i <= l; i++) {
            int ahead = l - 1;
            int zero = dp0[i + ahead] - dp0[i - 1];
            int one = dp1[i + ahead] - dp1[i - 1];

            min = Math.min(min, Math.min(zero, one));
        }

        return min;
    }

    public static void main(String[] args) {
        // "01001001101" -> 2
        int result = (new MinimumNumberOfFlipsToMakeBinaryStringAlternating()).minFlips("10001100101000000");

        System.out.println(result);
    }
}
