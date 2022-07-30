package LeetCode.Easy;

import Utils.TreeNode;
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
    Memory Usage: 42.1 MB, less than 96.21% of Java online submissions for Convert Sorted Array to Binary Search
 */
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        int l = nums.length;
        return inner(nums, 0, l - 1);
    }

    private TreeNode inner(int[] nums, int start, int end){

        int middle = (end + start) / 2;

        TreeNode root = new TreeNode(nums[middle]);
        if(middle > start){
            root.left = inner(nums, start, middle - 1);
        }

        if(middle < end){
            root.right = inner(nums, middle + 1, end);
        }

        return root;
    }

    public static void main(String[] args){
        int[] nums = {0, 1};
        TreeNode result = (new ConvertSortedArrayToBinarySearchTree()).sortedArrayToBST(nums);
    }
}
