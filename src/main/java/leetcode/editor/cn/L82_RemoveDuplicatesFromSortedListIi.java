package leetcode.editor.cn;

import leetcode.editor.cn.common.ListNode;

//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
// Related Topics 链表
public class L82_RemoveDuplicatesFromSortedListIi{
  public static void main(String[] args) {
       Solution solution = new L82_RemoveDuplicatesFromSortedListIi().new Solution();
      System.out.println(solution.deleteDuplicates(ListNode.of(1, 2, 3, 3, 4, 4, 5,5)));
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        //如果当前head节点于next节点不相等，则保留head节点并且对next节点进行递归
        if(next.val != head.val){
            head.next = deleteDuplicates(next);
            return head;
        }else{
            //如果相等，那么直到找到不相等的next，然后对next进行去重并返回
            while(next != null && head.val == next.val){
                next = next.next;
            }
            return deleteDuplicates(next);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}