package LeetCode.Easy;

import Utils.ListNode;

/*
Start: 1:10
end: 1:07
Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
Memory Usage: 43.3 MB, less than 35.70% of Java online submissions for Merge Two Sorted Lists.
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0, null);
        ListNode result = dummy;
        while(list1 != null && list2 != null){
            if(list1.val >= list2.val){
                result.next = list2;
                list2 = list2.next;
            } else{
                result.next = list1;
                list1 = list1.next;
            }

            result = result.next;
        }

        if(list1 != null){
            result.next = list1;
        }

        if(list2 != null){
            result.next = list2;
        }

        return dummy.next;
    }
}
