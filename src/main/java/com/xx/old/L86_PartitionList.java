package com.xx.old;

import leetcode.editor.cn.common.ListNode;

//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
//
// 你应当保留两个分区中每个节点的初始相对位置。 
//
// 示例: 
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
// 
// Related Topics 链表 双指针
public class L86_PartitionList{
  public static void main(String[] args) {
       Solution solution = new L86_PartitionList().new Solution();
      System.out.println(solution.partition(ListNode.of(1,4,3,2,5,2), 3));
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
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode l1 = new ListNode(-1), c1 = l1, l2 = new ListNode(-1), c2 = l2;
        while (head!= null){
            ListNode next = head.next;
            head.next = null;
            if(head.val < x){
                c1.next = head;
                c1 = c1.next;
            }else{
                c2.next = head;
                c2 = c2.next;
            }
            head = next;
        }
        c1.next = l2.next;
        return l1.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}