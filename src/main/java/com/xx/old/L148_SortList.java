package com.xx.old;

import leetcode.editor.cn.common.ListNode;

//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表
public class L148_SortList{
  public static void main(String[] args) {
       Solution solution = new L148_SortList().new Solution();
      System.out.println(solution.sortList(ListNode.of(-1,5,3,4,0)));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode slow = head, fast = head.next.next, l, r;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        r = sortList(slow.next);
        slow.next = null;
        l = sortList(head);
        return merge(l, r);
    }

    private ListNode merge(ListNode l, ListNode r) {
        ListNode t = new ListNode(-1), p = t;
        while(l != null && r != null){
            if(l.val < r.val){
                p.next = l;
                l = l.next;
            }else{
                p.next = r;
                r = r.next;
            }
            p = p.next;
        }
        if(l != null){
            p.next = l;
        }
        if(r != null){
            p.next = r;
        }
        return t.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}