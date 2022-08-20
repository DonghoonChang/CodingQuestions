package LeetCode.Medium;

import Utils.ListNode;

/*
Time: 27m
Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
Memory Usage: 42.6 MB, less than 5.82% of Java online submissions for Swap Nodes in Pairs.
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;
        ListNode next, beyond;

        while(curr != null && curr.next != null && curr.next.next != null){
            // Move 2 steps a time
            next = curr.next;
            beyond = next.next;

            next.next = beyond.next;
            beyond.next = next;
            curr.next = beyond;

            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1, null);
        head.next = new ListNode(2, new ListNode(3, new ListNode(4 , null)));

        ListNode result = (new SwapNodesInPairs()).swapPairs(head);

        System.out.println("Done");
    }
}
