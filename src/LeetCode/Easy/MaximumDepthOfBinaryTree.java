package LeetCode.Easy;

import Utils.TreeNode;

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return inner(root);
    }

    private int inner(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = 0;
        if (root.left != null){
            left = inner(root.left);
        }

        int right = 0;
        if( root.right != null){
            right = inner(root.right);
        }

        return 1 + Math.max(left, right);
    }
}
