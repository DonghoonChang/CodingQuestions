package LeetCode.Easy;

import Utils.TreeNode;

public class SameTree {
    /*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
    Memory Usage: 41.9 MB, less than 21.20% of Java online submissions for Same
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null ^ q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        boolean leftSame = isSameTree(p.left, q.left);
        boolean rightSame = isSameTree(p.right, q.right);

        if (leftSame && rightSame) {
            return true;
        }

        return false;
    }
}
