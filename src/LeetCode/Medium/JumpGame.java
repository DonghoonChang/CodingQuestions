package LeetCode.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    /*
        Runtime: 2 ms, faster than 92.48% of Java online submissions for Jump Game.
        Memory Usage: 43 MB, less than 86.89% of Java online submissions for Jump Game.
     */
    public static boolean solve(int[] nums) {
        int i = 0;
        for (int reach = 0; i <= reach; ++i) {
            if (i + nums[i] >= nums.length - 1) {
                return true;
            }

            reach = Math.max(reach, i + nums[i]);
        }

        return i == nums.length;
    }


    /*
        Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game.
        Memory Usage: 42.6 MB, less than 95.29% of Java online submissions for Jump Game.
     */
    public static boolean solve2(int[] nums) {
        if (nums.length == 1){
            return true;
        }

        int hops = 0;
        for (int j = (nums.length - 2); j >= 0; j--) {
            hops = hops + 1;

            if (nums[j] >= hops) {
                hops = 0;
            }
        }

        return hops == 0;
    }

    /*
        Runtime: 130 ms, faster than 27.53% of Java online submissions for Jump Game.
        Memory Usage: 42.8 MB, less than 93.52% of Java online submissions for Jump Game.
    */
    public static boolean solve1(int[] nums) {
        int l = nums.length;
        if (l == 0) {
            return true;

        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        do {
            int currIndex = q.remove();
            int currJump = nums[currIndex];

            for (int j = currJump; j >= 1; j--) {
                int nextIndex = currIndex + j;
                if (nextIndex >= l - 1) {
                    return true;
                }

                int nextJump = nums[nextIndex];
                if (nextJump != 0) {
                    q.add(currIndex + j);
                }
            }

            nums[currIndex] = 0;
        } while (!q.isEmpty());


        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = null;
        for (int i = 0; i < 1; i++) {
            String s = sc.nextLine();
            String[] splt = s.split("[\\[\\],\\s]");
            nums = Arrays.stream(splt).filter(l -> !l.isEmpty()).map(l -> Integer.parseInt(l)).mapToInt(l -> l).toArray();
        }

        System.out.println(solve1(nums));
    }
}