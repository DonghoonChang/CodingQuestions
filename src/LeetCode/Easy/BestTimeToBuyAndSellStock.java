package LeetCode.Easy;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int currMin = 1 << 30;
        int maxProfit = -1;

        for(int price: prices){
            currMin = Math.min(currMin, price);
            maxProfit = Math.max(maxProfit, price - currMin);
        }

        return maxProfit;
    }
}
