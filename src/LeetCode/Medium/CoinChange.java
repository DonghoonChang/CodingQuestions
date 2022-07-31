package LeetCode.Medium;

import java.util.Arrays;
import java.util.Collections;

// start: 9: 00
// end: 9:30
/*
Bullshit solution lol
Runtime: 1941 ms, faster than 5.00% of Java online submissions for Coin Change.
Memory Usage: 41.9 MB, less than 94.62% of Java online submissions for Coin Change.
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount % 2 == 1 && Arrays.stream(coins).allMatch(l -> l % 2 == 0)){
            return -1;
        }

        if(coins.length == 1){
            if(amount % coins[0] == 0){
                return amount / coins[0];
            }

            return -1;
        }

        Integer[] _coins = Arrays.stream(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(_coins, Collections.reverseOrder());
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);

        inner(_coins, amount, 0, memo);

        return memo[0] == Integer.MAX_VALUE ? -1 : memo[0];
    }

    private void inner(Integer[] coins, int remaining, int count, int[] memo){
        if(memo[remaining] <= count){
            return;
        }

        memo[remaining] = count;

        for(int i = 0; i < coins.length; i++){
            int coin = coins[i];
            int div = remaining / coin;

            for(int j = div; j > 0; j--){
                inner(coins, remaining - j * coin, count + j, memo);
            }
        }
    }

    public static void main(String[] args){
        int[] coins = new int[]{2,4,6,8,10,12,14,16,18,20,22,24};
        int amount = 9999;
        int result = (new CoinChange()).coinChange(coins, amount);

        System.out.println(result);
    }
}
