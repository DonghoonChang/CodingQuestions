package LeetCode.Medium;

import Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
Time: 75m
Runtime: 2 ms, faster than 98.59% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
Memory Usage: 41.8 MB, less than 96.54% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private static Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }

        int length = inorder.length;

        return buildTree(inorder, postorder, 0, length - 1, 0, length - 1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR){
        if(inL > inR){
            return null;
        }

        TreeNode node = new TreeNode(postorder[postR]);
        if(inL == inR){
            return node;
        }

        int inIndex = inorderMap.get(postorder[postR]);
        int leftNodes = inIndex - inL;

        node.left = buildTree(inorder, postorder, inL, inIndex - 1, postL, postL + leftNodes - 1);
        node.right = buildTree(inorder, postorder, inIndex + 1, inR, postL + leftNodes, postR - 1);

        return node;
    }

    public static void main(String[] args){
        int[] inorder = {2, 1};
        int[] postorder = {2, 1};
        TreeNode result = (new ConstructBinaryTreeFromInorderAndPostorderTraversal()).buildTree(inorder, postorder);

        System.out.println("Done");
    }
}
