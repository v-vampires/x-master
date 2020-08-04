package leetcode.editor.cn;

import leetcode.editor.cn.common.ListNode;

//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表
public class L25_ReverseNodesInKGroup{
  public static void main(String[] args) {
       Solution solution = new L25_ReverseNodesInKGroup().new Solution();
      System.out.println(solution.reverseKGroup(ListNode.of(1,2,3,4,5), 2));
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode nxt = head;
        for (int i = 0; i < k; i++) {
            nxt = nxt.next;
            if(nxt == null){
                return head;
            }
        }
        ListNode r = reverse(head, k);
        head.next = reverseKGroup(nxt, k);
        return r;
    }

    private ListNode reverse(ListNode head, int k) {
        ListNode pre = null, cur = head, nxt = null;
        while (k-- > 0){
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}