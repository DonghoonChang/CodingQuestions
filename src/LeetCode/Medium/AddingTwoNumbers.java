package LeetCode.Medium;

import Utils.ListNode;

// start time: 9:40
// end time: 10:05
/*
    Runtime: 2 ms, faster than 99.36% of Java online submissions for Add Two Numbers.
    Memory Usage: 42.5 MB, less than 88.35% of Java online submissions for Add Two Numbers.
 */
public class AddingTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carryOver = false;
        ListNode root = null;
        ListNode next = null;
        while(l1 != null || l2 != null){
            int value = 0;
            if(l1 != null){
                value += l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                value += l2.val;
                l2 = l2.next;
            }

            if(carryOver){
                value += 1;
                carryOver = false;
            }

            carryOver = value >= 10;
            value = value % 10;

            if(next == null){ // first iteration
                next = new ListNode(value);
                root = next;
            } else{
                next.next = new ListNode(value);
                next = next.next;
            }
        }

        if(carryOver){
            next.next = new ListNode(1);
        }

        return root;
    }
}
