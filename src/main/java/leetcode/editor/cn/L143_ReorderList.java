package leetcode.editor.cn;

import leetcode.editor.cn.common.ListNode;

//给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
// Related Topics 链表
public class L143_ReorderList{
  public static void main(String[] args) {
       Solution solution = new L143_ReorderList().new Solution();
       ListNode l = ListNode.of(1,2,3,4,5,6,7);
       solution.reorderList(l);
      System.out.println(l);
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
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tail = reverseListNode(slow.next);
        slow.next = null;
        while (tail != null){
            ListNode hNext = head.next;
            ListNode tNext = tail.next;
            head.next = tail;
            tail.next = hNext;
            head = hNext;
            tail = tNext;
        }
    }

    private ListNode reverseListNode(ListNode node) {
        if(node == null || node.next == null){
            return node;
        }
        ListNode next = node.next;
        ListNode r = reverseListNode(next);
        next.next = node;
        node.next = null;
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}