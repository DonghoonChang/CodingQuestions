package LeetCode.Easy;

import Utils.TreeNode;

/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
    Memory Usage: 43.3 MB, less than 47.63% of Java online submissions for Diameter of Binary Tree.
 */
public class DiameterOfBinaryTree {
    static int globalMax;

    public int diameterOfBinaryTree(TreeNode root) {
        globalMax = 0;
        inner(root);

        return globalMax;
    }

    public int inner(TreeNode root) {
        int left = 0;
        int right = 0;

        if (root.left != null) {
            left = inner(root.left);
        }

        if (root.right != null) {
            right = inner(root.right);
        }

        globalMax = Math.max(globalMax, left + right);

        return Math.max(left, right) + 1;
    }
}
