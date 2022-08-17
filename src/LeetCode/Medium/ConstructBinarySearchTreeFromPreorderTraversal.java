package LeetCode.Medium;

import Utils.TreeNode;

/*
Time: 41m
Runtime: 3 ms, faster than 7.69% of Java online submissions for Construct Binary Search Tree from Preorder Traversal.
Memory Usage: 42.4 MB, less than 38.16% of Java online submissions for Construct Binary Search Tree from Preorder Traversal.
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] sorted = countValues(preorder);
        return bstFromPreorder(preorder, sorted, 0, preorder.length - 1, 0);
    }

    // returns index of a value in a sorted array
    private int[] countValues(int[] preorder){
        int[] counts = new int[1001]; // 1 <= i <= 1000;
        for(int num: preorder){
            counts[num]++;
        }

        int acc = 0;
        for(int i = 1; i <= 1000; i++){
            int temp = counts[i];
            counts[i] = acc;
            acc += temp;
        }

        return counts;
    }

    private TreeNode bstFromPreorder(int[] preorder, int[] sorted, int preL, int preR, int sortedL){
        if(preL > preR){
            return null;
        }

        TreeNode root = new TreeNode(preorder[preL]);
        if(preL == preR){
            return root;
        }

        int sortedIndex = sorted[preorder[preL]];
        int leftNodes = sortedIndex - sortedL;

        root.left = bstFromPreorder(preorder, sorted, preL + 1, preL + leftNodes, sortedL);
        root.right = bstFromPreorder(preorder, sorted, preL + leftNodes + 1, preR, sortedIndex + 1);

        return root;
    }

    public static void main(String[] args){
        int[] preorder = {8,5,1,7,10,12};
        TreeNode result = (new ConstructBinarySearchTreeFromPreorderTraversal()).bstFromPreorder(preorder);

        System.out.println("Done");
    }
}
