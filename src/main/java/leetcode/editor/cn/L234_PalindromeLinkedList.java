package leetcode.editor.cn;

import leetcode.editor.cn.common.ListNode;

//请判断一个链表是否为回文链表。
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针
public class L234_PalindromeLinkedList{
  public static void main(String[] args) {
       Solution solution = new L234_PalindromeLinkedList().new Solution();
      System.out.println(solution.isPalindrome(ListNode.of(1,1,2,1)));
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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode r = reverse(slow.next);
        slow.next = null;
        while(head != null && r != null){
            if(head.val != r.val){
                return false;
            }
            head = head.next;
            r = r.next;
        }
        return true;

    }

    private ListNode reverse(ListNode node){
        if(node == null || node.next == null){
            return node;
        }
        ListNode next = node.next;
        ListNode r = reverse(next);
        next.next = node;
        node.next = null;
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}