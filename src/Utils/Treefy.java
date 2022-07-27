package Utils;

import java.util.LinkedList;
import java.util.Queue;

public class Treefy {
    public static TreeNode treefy(Integer[] vals){
        if(vals.length == 0){
            return null;
        }

        TreeNode root = null;
        TreeNode curr = null;
        int childrenProcessed = 0;
        Queue<TreeNode> s = new LinkedList<>();
        for(Integer val: vals){
            if(curr == null){
                curr = new TreeNode(val);
                root = curr;
                continue;
            }

            if(childrenProcessed == 2){
                curr = s.poll();
                childrenProcessed = 0;
            }

            if(childrenProcessed == 0){
                if (val != null){
                    curr.left = new TreeNode(val);
                    s.offer(curr.left);
                }

                childrenProcessed = 1;
                continue;
            }

            if(childrenProcessed == 1){
                if (val != null){
                    curr.right = new TreeNode(val);
                    s.offer(curr.right);
                }

                childrenProcessed = 2;
                continue;
            }
        }

        return root;
    }

    public static void main(String[] args){
        Integer[] trees = new Integer[]{1,0,1,1,2,0,-1,0,1,-1,0,-1,0,1,0};

        TreeNode root = treefy(trees);
    }
}
