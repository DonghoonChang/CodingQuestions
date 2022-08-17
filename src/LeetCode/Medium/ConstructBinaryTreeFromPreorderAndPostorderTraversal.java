package LeetCode.Medium;

import Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
Time: 25m
Runtime: 4 ms, faster than 14.21% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
Memory Usage: 44.8 MB, less than 6.40% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    private static Map<Integer, Integer> postMap;
    private static Map<Integer, Integer> preMap;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        postMap = new HashMap<>();
        preMap = new HashMap<>();
        for(int i = 0; i < preorder.length; i++){
            preMap.put(preorder[i], i);
            postMap.put(postorder[i], i);
        }

        return buildTree(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] postorder, int preL, int preR, int postL, int postR){
        if(preL > preR){
            return null;
        }

        int middle = preorder[preL];
        TreeNode node = new TreeNode(middle);

        if(preL == preR){
            return node;
        }

        int left = preorder[preL + 1];
        int leftPostIndex = postMap.get(left);
        int leftNodes = leftPostIndex - postL + 1;

        node.left = buildTree(preorder, postorder, preL + 1, preL + leftNodes, postL, postL + leftNodes - 1);
        node.right = buildTree(preorder, postorder, preL + leftNodes + 1, preR, postL + leftNodes, postR - 1);

        return node;
    }

    public static void main(String[] args){
        int[] preorder = {1,2,4,5,3,6,7};
        int[] postorder  = {4,5,2,6,7,3,1};
        TreeNode result = (new ConstructBinaryTreeFromPreorderAndPostorderTraversal()).constructFromPrePost(preorder, postorder);

        System.out.println("Done");
    }
}
