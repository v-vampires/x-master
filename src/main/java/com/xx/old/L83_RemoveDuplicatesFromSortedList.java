package com.xx.old;

import leetcode.editor.cn.common.ListNode;

//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表
public class L83_RemoveDuplicatesFromSortedList{
  public static void main(String[] args) {
       Solution solution = new L83_RemoveDuplicatesFromSortedList().new Solution();
      System.out.println(solution.deleteDuplicates(ListNode.of(1, 1, 2, 3, 3)));
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
        ListNode ret = new ListNode(-1);
        ret.next = head;
        while(head != null && head.next != null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return ret.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}