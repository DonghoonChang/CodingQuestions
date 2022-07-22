package LeetCode.Easy;

import java.util.*;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /*

Runtime: 2 ms, faster than 99.03% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
Memory Usage: 42.3 MB, less than 86.68% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.

     */

    private static Map<Integer, Integer> _inOrderInverseMap = new HashMap<>();

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0 ; i < preorder.length ; i ++){
            _inOrderInverseMap.put(inorder[i], i);
        }

        return buildSubtree(preorder, inorder, 0, 0, preorder.length - 1);
    }

    private static TreeNode buildSubtree(int[] _preorder, int[] _inorder, int midPreOrder, int minInOrder, int maxInOrder){
        int midVal = _preorder[midPreOrder];
        int midInOrder = _inOrderInverseMap.get(midVal);
        int leftSize = 0;

        TreeNode subRoot = new TreeNode(midVal);

        if(midInOrder != minInOrder){
            leftSize = midInOrder - minInOrder;
            subRoot.left = buildSubtree(_preorder, _inorder, midPreOrder + 1, minInOrder, midInOrder - 1);
        }

        if(midInOrder != maxInOrder){
            subRoot.right = buildSubtree(_preorder, _inorder, midPreOrder + leftSize + 1, midInOrder + 1, maxInOrder);
        }

        return subRoot;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        List<List<Integer>> input = new ArrayList<>();
        for(int i = 0; i < 2 ; i ++){
            String s = sc.nextLine();
            String[] splt = s.split("[\\[\\],\\s]");
            input.add(Arrays.stream(splt).filter(l -> !l.isEmpty()).map(l -> Integer.parseInt(l)).toList());
        }

        TreeNode result = buildTree(input.get(0).stream().mapToInt(Integer::intValue).toArray(), input.get(1).stream().mapToInt(Integer::intValue).toArray());
    }
}
