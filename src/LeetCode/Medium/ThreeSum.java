package LeetCode.Medium;

import java.util.*;

/*
Runtime: 44 ms, faster than 36.74% of Java online submissions for 3Sum.
Memory Usage: 62 MB, less than 25.75% of Java online submissions for 3Sum.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> s1 = new HashSet<>();
        for (int i = 0; i + 2 < nums.length; i++) {
            if (s1.contains(nums[i])) {
                continue;
            }

            s1.add(nums[i]);
            int target = -nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            Set<Integer> s2 = new HashSet<>();

            while (j < k) {
                int sum = nums[j] + nums[k];

                if (sum == target) {
                    if (!s2.contains(nums[j])) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        s2.add(nums[j]);
                        result.add(list);
                    }

                    j++;
                    k--;
                }

                if (sum < target) {
                    j++;
                }

                if (sum > target) {
                    k--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = (new ThreeSum()).threeSum(nums);

        System.out.println(result);
    }
}
