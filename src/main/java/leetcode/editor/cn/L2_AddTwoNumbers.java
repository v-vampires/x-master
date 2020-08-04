package leetcode.editor.cn;

import leetcode.editor.cn.common.ListNode;

//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学
public class L2_AddTwoNumbers{
  public static void main(String[] args) {
       Solution solution = new L2_AddTwoNumbers().new Solution();
      System.out.println(solution.addTwoNumbers(ListNode.of(2,4,3), ListNode.of(5,6,4)));
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(-1), cur = ret;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int v = (v1 + v2 + carry) % 10;
            carry = (v1 + v2 + carry) / 10;
            cur.next = new ListNode(v);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return ret.next;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}