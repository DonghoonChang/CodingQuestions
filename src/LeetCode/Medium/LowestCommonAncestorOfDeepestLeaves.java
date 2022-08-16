package LeetCode.Medium;

import Utils.TreeNode;

import java.util.*;

/*
Time: 34m
Runtime: 5 ms, faster than 11.61% of Java online submissions for Lowest Common Ancestor of Deepest Leaves.
Memory Usage: 44.5 MB, less than 56.09% of Java online submissions for Lowest Common Ancestor of Deepest Leaves.
 */
public class LowestCommonAncestorOfDeepestLeaves {
    private static int maxDepth = 0;
    private static Map<TreeNode, TreeNode> parent;
    private static Set<TreeNode> deepests;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        maxDepth = 0;
        parent = new HashMap<>();
        deepests = new HashSet<>();
        traverseTree(root, 0);

        while(deepests.size() != 1){
            Set<TreeNode> set = new HashSet<>();
            for(TreeNode node: deepests){
                set.add(parent.get(node));
            }

            deepests = set;
        }

        return (TreeNode) deepests.toArray()[0];
    }

    private void traverseTree(TreeNode root, int depth){
        if(root == null){
            return;
        }

        boolean couldBeDeepest = true;
        if(root.left != null){
            traverseTree(root.left, depth + 1);
            parent.put(root.left, root);
            couldBeDeepest = false;
        }

        if(root.right != null){
            traverseTree(root.right, depth + 1);
            parent.put(root.right, root);
            couldBeDeepest = false;
        }

        if(!couldBeDeepest){
            return;
        }

        if(depth == maxDepth){
            deepests.add(root);
        }

        if(depth > maxDepth){
            maxDepth = depth;
            deepests = new HashSet<>();
            deepests.add(root);
        }
    }

    public static void main(String[] args){
        TreeNode lca = (new LowestCommonAncestorOfDeepestLeaves()).lcaDeepestLeaves(new TreeNode(0, null, null));

        System.out.println(lca);
    }
}
