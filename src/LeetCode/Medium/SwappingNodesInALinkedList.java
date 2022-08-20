package LeetCode.Medium;

import Utils.ListNode;

/*
Time: 30m
Runtime: 3 ms, faster than 87.98% of Java online submissions for Swapping Nodes in a Linked List.
Memory Usage: 189.7 MB, less than 9.02% of Java online submissions for Swapping Nodes in a Linked List.
 */
public class SwappingNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode bow = dummy;
        ListNode stern = dummy;
        ListNode anchor = dummy;
        ListNode anchorPrev = null;
        ListNode sternPrev = null;

        for(int i = 0; i < k; i++){
            bow = bow.next;
            anchorPrev = anchor;
            anchor = anchor. next;
        }

        // Push the boat to the end
        while(bow != null){
            bow = bow.next;
            sternPrev = stern;
            stern = stern.next;
        }

        if(stern.next == anchor){
            ListNode anchorNext = anchor.next;
            sternPrev.next = anchor;
            anchor.next = stern;
            stern.next = anchorNext;
            return dummy.next;
        }

        if(anchor.next == stern){
            ListNode sternNext = stern.next;
            anchorPrev.next = stern;
            stern.next = anchor;
            anchor.next = sternNext;

            return dummy.next;
        }

        ListNode sternNext = stern.next;
        stern.next = anchor.next;
        anchor.next = sternNext;
        anchorPrev.next = stern;
        sternPrev.next = anchor;

        return dummy.next;
    }

    public static void main(String[] args){

    }
}
