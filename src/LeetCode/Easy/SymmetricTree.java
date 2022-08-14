package LeetCode.Easy;

import Utils.TreeNode;

/*
Time: 2m
Runtime: 0 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
Memory Usage: 42.5 MB, less than 39.70% of Java online submissions for Symmetric Tree.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetry(root.left, root.right);
    }

    public boolean checkSymmetry(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left != null && right == null) {
            return false;
        }

        if (left == null && right != null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return checkSymmetry(left.left, right.right) && checkSymmetry(left.right, right.left);
    }
}
