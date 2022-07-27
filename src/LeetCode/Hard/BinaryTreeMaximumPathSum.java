package LeetCode.Hard;

import Utils.TreeNode;

import static Utils.Treefy.treefy;

public class BinaryTreeMaximumPathSum {

    /*
        Runtime: 1 ms, faster than 99.67% of Java online submissions for Binary Tree Maximum Path Sum.
        Memory Usage: 47.6 MB, less than 61.70% of Java online submissions for Binary Tree Maximum Path Sum.
     */
    public static int globalMax = 0;
    public static int outOfRange = -2000;
    public int maxPathSum(TreeNode root) {
        globalMax = outOfRange;
        inner(root);
        return globalMax;
    }

    private int inner(TreeNode root){
        if(root == null){
            return outOfRange;
        }

        globalMax = Math.max(globalMax, root.val);
        int left = Math.max(outOfRange, inner(root.left));
        int right = Math.max(outOfRange, inner(root.right));
        int max = Math.max(left, right);
        int min = Math.min(left, right);

        int localMax;
        if(root.val >= 0){
            localMax = root.val + Math.max(0, max);

            globalMax = Math.max(globalMax, localMax + Math.max(min, 0));
            return localMax;
        } else {
            if (max + root.val > 0){
                localMax = max + root.val;

                globalMax = Math.max(globalMax, localMax + Math.max(min, 0)); // If the path did not go up to parent
                return localMax;
            } else {
                localMax = Math.max(max, root.val);
                return localMax;
            }
        }
    }

    public static void main(String[] args){
        Integer[] trees = new Integer[]{1,0,1,1,2,0,-1,0,1,-1,0,-1,0,1,0};

        TreeNode root = treefy(trees);

        int result = (new BinaryTreeMaximumPathSum()).maxPathSum(root);
    }
}
