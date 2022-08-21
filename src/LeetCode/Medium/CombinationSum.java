package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Time: 7m
Runtime: 7 ms, faster than 50.76% of Java online submissions for Combination Sum.
Memory Usage: 47.4 MB, less than 18.57% of Java online submissions for Combination Sum.
 */
public class CombinationSum {
    private static List<List<Integer>> result;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        Arrays.sort(candidates);
        inner(candidates, 0, target, new ArrayList<>());

        return result;
    }

    private void inner(int[] candidates, int index, int remainder, List<Integer> combo){
        if(remainder == 0){
            result.add(combo);
            return;
        }

        if(index >= candidates.length || candidates[index] > remainder){
            return;
        }
        List<Integer> newList = new ArrayList<>(combo);

        // take current and stay
        combo.add(candidates[index]);
        inner(candidates, index, remainder - candidates[index], combo);

        // ignore current and move away
        inner(candidates, index + 1, remainder, newList);
    }

    public static void main(String[] args){
        int[] candidates = {2};
        int target = 1;
        List<List<Integer>> list = (new CombinationSum()).combinationSum(candidates, target);

        System.out.println("Done");
    }
}
