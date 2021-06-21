package com.xx.old;

import leetcode.editor.cn.common.ListNode;

//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
// 说明: 
//1 ≤ m ≤ n ≤ 链表长度。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL 
// Related Topics 链表
public class L92_ReverseLinkedListIi{
  public static void main(String[] args) {
       Solution solution = new L92_ReverseLinkedListIi().new Solution();
      System.out.println(solution.reverseBetween(ListNode.of(1,2,3,4,5), 2, 4));
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode ret = new ListNode(-1);
        ret.next = head;
        ListNode pre = ret;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        head = pre.next;
        for (int i = 0; i < n-m ; i++) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return ret.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}