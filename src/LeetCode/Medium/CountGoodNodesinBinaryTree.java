package LeetCode.Medium;

import Utils.TreeNode;

/*
    Runtime: 4 ms, faster than 38.96% of Java online submissions for Count Good Nodes in Binary Tree.
    Memory Usage: 59.9 MB, less than 35.57% of Java online submissions for Count Good Nodes in Binary Tree.
 */

public class CountGoodNodesinBinaryTree {
    private static int count;
    public int goodNodes(TreeNode root) {
        count = 0;
        inner(root, Integer.MIN_VALUE);

        return count;
    }

    private void inner(TreeNode root, int max){
        if(root == null){
            return;
        }

        int val = root.val;

        if(max <= val){
            count++;
        }

        inner(root.left, Math.max(max, val));
        inner(root.right, Math.max(max, val));
    }
}

