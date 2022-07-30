package LeetCode.Easy;

import Utils.TreeNode;
import Utils.Treefy;

/*
    Runtime: 3 ms, faster than 97.60% of Java online submissions for Subtree of Another Tree.
    Memory Usage: 41.8 MB, less than 99.85% of Java online submissions for Subtree of Another Tree.
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return inner(root, subRoot, false);
    }

    private boolean inner(TreeNode root, TreeNode subRoot, boolean isContinuous){
        if(root == null && subRoot == null){
            return true;
        }

        if(root == null || subRoot == null){
            return false;
        }

        boolean found = false;
        if(root.val == subRoot.val){
            found = inner(root.left, subRoot.left, true) && inner(root.right, subRoot.right, true);
        }

        if(found){
            return true;
        }

        if(isContinuous){
            return false;
        }

        found = inner(root.left, subRoot, false);

        if(found){
            return true;
        }

        found = inner(root.right, subRoot, false);

        return found;
    }

    public static void main(String[] args){
        Integer[] _tree = new Integer[]{3,4,5,1,null,2};
        Integer[] _subTree= new Integer[]{3, 1, 2};

        TreeNode tree = Treefy.treefy(_tree);
        TreeNode subTree = Treefy.treefy(_subTree);

        boolean result = (new SubtreeOfAnotherTree()).isSubtree(tree, subTree);

        System.out.println(result);
    }
}
