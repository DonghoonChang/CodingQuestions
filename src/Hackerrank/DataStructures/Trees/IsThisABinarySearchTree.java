package Hackerrank.DataStructures.Trees;

public class IsThisABinarySearchTree {
    /* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {
        int val = root.data;
        int leftMax = -1;
        int rightMin = 10001;

        boolean checkLeft = true;
        if(root.left != null){
            checkLeft = checkBST(root.left);

            if(!checkLeft){
                return false;
            }

            leftMax = getMax(root.left);
        }

        boolean checkRight = true;
        if(root.right != null){
            checkRight = checkBST(root.right);

            if(!checkRight){
                return false;
            }

            rightMin = getMin(root.right);
        }

        return checkLeft && checkRight && (leftMax < val && val < rightMin);
    }

    int getMin(Node node){
        int min = node.data;
        int leftMin = 10001;
        int rightMin = 10001;

        if(node.left != null ){
            leftMin = getMin(node.left);
        }

        if(node.right != null){
            rightMin = getMin(node.right);
        }

        return Math.min(Math.min(leftMin, rightMin), min);
    }

    int getMax(Node node){
        int max = node.data;
        int leftMax = -1;
        int rightMax = -1;

        if(node.left != null ){
            leftMax = getMax(node.left);
        }

        if(node.right != null){
            rightMax = getMax(node.right);
        }

        return Math.max(Math.max(leftMax, rightMax), max);
    }
}
