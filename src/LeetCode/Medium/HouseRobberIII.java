package LeetCode.Medium;

import Utils.TreeNode;

import java.util.HashMap;

/*
    Runtime: 2 ms, faster than 56.75% of Java online submissions for House Robber III.
    Memory Usage: 42 MB, less than 88.63% of Java online submissions for House Robber III.
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        return inner(root, 0, new HashMap<>());
    }

    // Top down;
    private int inner(TreeNode root, int sum, HashMap<TreeNode, Integer> map) { // prev1: grandparent prev2: parent of root
        if (map.containsKey(root)) {
            return map.get(root);
        }

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        // Take this node and move to grand-children
        int ll = 0, lr = 0, rl = 0, rr = 0;
        if (root.left != null) {
            ll = inner(root.left.left, sum + root.val, map);
            lr = inner(root.left.right, sum + root.val, map);
        }

        if (root.right != null) {
            rl = inner(root.right.left, sum + root.val, map);
            rr = inner(root.right.right, sum + root.val, map);
        }

        int with = root.val + ll + lr + rl + rr;

        // Skip this node and move to children
        int left = inner(root.left, sum, map);
        int right = inner(root.right, sum, map);
        int without = left + right;

        int max = Math.max(with, without);
        map.put(root, max);

        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootRight = new TreeNode(3);

        root.left = rootLeft;
        root.right = rootRight;

        TreeNode rootLeftRight = new TreeNode(3);
        root.left.right = rootLeftRight;

        TreeNode rootRightRight = new TreeNode(1);
        root.right.right = rootRightRight;

        int result = (new HouseRobberIII()).rob(root);

        System.out.println(result);
    }
}
