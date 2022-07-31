package LeetCode.Medium;

//Start time: 10:10
//End time: 10:22 (TLE)
// TODO: WIP

import java.util.Arrays;
import java.util.Collections;

public class CoinChange2 {
    static int count;
    public int change(int amount, int[] _coins) {
        count = 0;

        Integer[] coins = Arrays.stream(_coins).boxed().toArray(Integer[]::new);
        Arrays.sort(coins, Collections.reverseOrder());
        boolean[][] memo = new boolean[amount + 1][coins.length];

        inner(amount, coins, 0, memo);


        return count;
    }

    private void inner(int amount, Integer[] coins, int coinIndex, boolean[][] memo){
        if(amount < 0){
            return;
        }

        if(amount == 0){
            count++;
            return;
        }

        if(coinIndex == coins.length){
            return;
        }

        if(memo[amount][coinIndex]){
            return;
        }

        memo[amount][coinIndex] = true;
        int coinToBeUsed = coins[coinIndex];
        int div = amount / coinToBeUsed;

        for(int i = div; i >= 0; i--){
            inner(amount - i * coinToBeUsed, coins, coinIndex + 1, memo);
        }
    }

    public static void main(String[] args){
        int[] coins = new int[]{1, 2, 5};
        int amount = 5;

        int result = (new CoinChange2()).change(amount, coins);

        System.out.println(result);
    }
}
