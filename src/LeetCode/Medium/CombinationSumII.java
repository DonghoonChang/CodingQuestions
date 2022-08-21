package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/*
Time: 17m
Runtime: 4 ms, faster than 89.42% of Java online submissions for Combination Sum II.
Memory Usage: 44.2 MB, less than 30.61% of Java online submissions for Combination Sum II.
 */
public class CombinationSumII {
    private static List<List<Integer>> result;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        int[] counts = getCounts(candidates);
        inner(counts, 1, 0, target, new ArrayList<>());

        return result;
    }

    private int[] getCounts(int[] candidates) {
        int[] counts = new int[51];
        for (int num : candidates) {
            counts[num]++;
        }

        return counts;
    }

    private void inner(int[] counts, int num, int numCount, int remainder, List<Integer> combo) {
        if (remainder == 0) {
            result.add(combo);
            return;
        }

        if (num > 50 || num > remainder) {
            return;
        }

        List<Integer> newList = new ArrayList<>(combo);

        // take current and stay if you can
        if (numCount < counts[num]) {
            combo.add(num);
            inner(counts, num, numCount + 1, remainder - num, combo);
        }

        // ignore current and move on
        inner(counts, num + 1, 0, remainder, newList);
    }

    public static void main(String[] args) {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> list = (new CombinationSumII()).combinationSum(candidates, target);

        System.out.println("Done");
    }
}
