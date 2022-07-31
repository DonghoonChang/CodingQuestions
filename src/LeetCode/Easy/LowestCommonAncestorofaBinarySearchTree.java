package LeetCode.Easy;

import Utils.TreeNode;

// start time: 8:25
// end time: 8:25

/*
    Runtime: 6 ms, faster than 80.24% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
    Memory Usage: 43.6 MB, less than 87.32% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
 */
public class LowestCommonAncestorofaBinarySearchTree {
    private static TreeNode LCA = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        inner(root, p, q);

        return LCA;
    }

    private void inner(TreeNode root, TreeNode p, TreeNode q) {

        // Guaranteed LCA
        if(root.val == p.val){
            if(hasNode(root.left, q) || hasNode(root.right, q)){
                LCA = root;
            }

            return;
        }

        // Guaranteed LCA
        if(root.val == q.val){
            if(hasNode(root.left, p) || hasNode(root.right, p)){
                LCA = root;
            }

            return;
        }

        boolean leftHasP = hasNode(root.left, p);
        boolean leftHasQ = hasNode(root.left, q);
        boolean rightHasP = hasNode(root.right, p);
        boolean rightHasQ = hasNode(root.right, q);

        if(leftHasP && leftHasQ){
            inner(root.left, p, q);
            return;
        }

        if(rightHasP && rightHasQ){
            inner(root.right, p, q);
            return;
        }

        if((leftHasP && rightHasP) || (leftHasQ && rightHasP)){
            LCA = root;
        }
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
