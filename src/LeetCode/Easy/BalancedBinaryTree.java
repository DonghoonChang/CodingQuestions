package LeetCode.Easy;

import Utils.TreeNode;

/*
Time: 10m
Runtime: 2 ms, faster than 35.25% of Java online submissions for Balanced Binary Tree.
Memory Usage: 44.5 MB, less than 36.60% of Java online submissions for Balanced Binary Tree.
 */
public class BalancedBinaryTree  {
    public boolean isBalanced(TreeNode root) {
        return isNodeBalanced(root);
    }

    private boolean isNodeBalanced(TreeNode root){
        if(root == null){
            return true;
        }

        if(!isNodeBalanced(root.left)){
            return false;
        }

        if(!isNodeBalanced(root.right)){
            return false;
        }

        return Math.abs(getNodeDepth(root.left) - getNodeDepth(root.right)) <= 1;
    }

    private int getNodeDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        return 1 + Math.max(getNodeDepth(root.left), getNodeDepth(root.right));
    }
}
