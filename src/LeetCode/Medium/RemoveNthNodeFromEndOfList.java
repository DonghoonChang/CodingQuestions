package LeetCode.Medium;

import Utils.ListNode;

/*
Start time: 11:14
End time: 11:51
Runtime: 1 ms, faster than 72.63% of Java online submissions for Remove Nth Node From End of List.
Memory Usage: 41.9 MB, less than 73.04% of Java online submissions for Remove Nth Node From End of List.
 */

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode ahead = head;
        ListNode prev = dummy;

        while(n != 0){
            ahead = ahead.next;
            n--;
        }

        while(ahead != null){
            ahead = ahead.next;
            prev = prev.next;
        }

        if(prev == dummy){
            return prev.next.next;
        }

        prev.next = prev.next.next;

        return head;
    }
}
