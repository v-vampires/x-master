package leetcode.editor.cn;

import leetcode.editor.cn.common.ListNode;

//对链表进行插入排序。
//
// 
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。 
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。 
//
// 
//
// 插入排序算法： 
//
// 
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。 
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。 
// 重复直到所有输入数据插入完为止。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2： 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
// 
// Related Topics 排序 链表
public class L147_InsertionSortList{
  public static void main(String[] args) {
       Solution solution = new L147_InsertionSortList().new Solution();
      System.out.println(solution.insertionSortList(ListNode.of(4,2,1,3)));
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
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode t = new ListNode(-1);
        t.next = head;
        ListNode pre = t.next, next = t.next.next;
        while(next != null){
            if(next.val >= pre.val){//asc
                pre = pre.next;
                next = next.next;
            }else{//desc
                pre.next = next.next;
                insert(t, new ListNode(next.val));
                next = next.next;
            }
        }

        return t.next;
    }

    private void insert(ListNode t, ListNode node) {
        ListNode l1 = t, l2 = t.next;
        while(l2 != null && node.val > l2.val){
            l1 = l2;
            l2 = l2.next;
        }
        l1.next = node;
        node.next = l2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}