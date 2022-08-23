package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Runtime: 8 ms, faster than 5.68% of Java online submissions for Permutations.
Memory Usage: 46.1 MB, less than 5.00% of Java online submissions for Permutations.
 */
public class Permutations {
    private static List<List<Integer>> list;

    public List<List<Integer>> permute(int[] nums) {
        list = new ArrayList<>();
        Set<Integer> init = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            init.add(i);
        }

        inner(nums, init, new ArrayList<>());

        return list;
    }

    private void inner(int[] nums, Set<Integer> remaining, List<Integer> work){
        if(remaining.size() == 0){
            list.add(work);
            return;
        }

        for(Integer index: remaining.toArray(new Integer[0])){
            Set<Integer> set = new HashSet<>(remaining);
            List<Integer> newWork = new ArrayList<>(work);
            set.remove(index);

            newWork.add(nums[index]);
            inner(nums, set, newWork);
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = (new Permutations()).permute(nums);

        System.out.println("Done");
    }
}
