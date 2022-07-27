package LeetCode.Medium;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/*
    Runtime: 2 ms, faster than 35.46% of Java online submissions for Kth Smallest Element in a BST.
    Memory Usage: 45.5 MB, less than 47.56% of Java online submissions for Kth Smallest Element in a BST.
 */

public class KthSmallestElementInABst {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
        Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
        Memory Usage: 42.3 MB, less than 90.07% of Java online submissions for Kth Smallest Element in a BST.
     */
    static int count = 0;
    static int val = -1;

    public int kthSmallestDFS(TreeNode root, int k){
        count = 0;
        count(root, k);

        return val;
    }

    private void count(TreeNode root, int k){
        if(root.left != null){
            count(root.left, k);
        }

        count++;
        if(count == k){
            val = root.val;
        }

        if(root.right != null){
            count(root.right, k);
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // Lower K elements
        traverse(root, maxHeap, k);

        return maxHeap.peek();
    }

    private void traverse(TreeNode root, Queue<Integer> maxHeap, int k){
        if(maxHeap.size() < k){
            maxHeap.add(root.val);
        } else if(maxHeap.peek() > root.val) {
            maxHeap.poll();
            maxHeap.add(root.val);
        }

        if (root.left != null){
            traverse(root.left, maxHeap, k);
        }

        if (root.right != null){
            traverse(root.right, maxHeap, k);
        }
    }
}
