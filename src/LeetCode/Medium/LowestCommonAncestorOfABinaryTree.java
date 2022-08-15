package LeetCode.Medium;

import Utils.TreeNode;

/*
Attempt : 1st
Time: 7m
Runtime: 1712 ms, faster than 5.18% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
Memory Usage: 48.1 MB, less than 17.39% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        TreeNode fromLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode fromRight = lowestCommonAncestor(root.right, p, q);

        return fromLeft != null ? fromLeft : fromRight != null ? fromRight: null;
    }

    private boolean hasNode(TreeNode root, TreeNode target){
        if(root == null){
            return false;
        }

        if(root.val == target.val){
            return true;
        }

        return hasNode(root.left, target) || hasNode(root.right, target);
    }
}
