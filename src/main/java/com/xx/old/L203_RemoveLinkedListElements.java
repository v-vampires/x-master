package com.xx.old;

import leetcode.editor.cn.common.ListNode;

//删除链表中等于给定值 val 的所有节点。
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表
public class L203_RemoveLinkedListElements{
  public static void main(String[] args) {
       Solution solution = new L203_RemoveLinkedListElements().new Solution();
      System.out.println(solution.removeElements(ListNode.of(1,2,3,4,5,6), 6));
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
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode cur = node;
        while(cur != null && cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }

        }
        return node.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}