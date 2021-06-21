package com.xx.old;

import leetcode.editor.cn.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
// Related Topics 堆 链表 分治算法
public class L23_MergeKSortedLists{
  public static void main(String[] args) {
       Solution solution = new L23_MergeKSortedLists().new Solution();
       ListNode l1 = ListNode.of(-2,-1,-1);
       ListNode l2 = ListNode.of();
      System.out.println(solution.mergeKLists(new ListNode[]{l1,l2}));
      l1 = ListNode.of(1,4,5);
      l2 = ListNode.of(1,3,4);
      ListNode l3 = ListNode.of(2,6);
      System.out.println(solution.mergeKLists(new ListNode[]{l1,l2,l3}));
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
    public ListNode mergeKLists(ListNode[] lists) {
        /*PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            while(list != null){
                ListNode next = list.next;
                list.next = null;
                queue.offer(list);
                list = next;
            }
        }
        ListNode ret = new ListNode(-1), cur = ret;
        while(!queue.isEmpty()){
            cur.next = queue.poll();
            cur = cur.next;
        }
        return ret.next;*/
        return mergeKListsByQueue(lists);
        /*分治实现*/
        //return mergeKLists(lists, 0 , lists.length - 1);
    }

    private ListNode mergeKListsByQueue(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode r = new ListNode(-1);
        for (ListNode node : lists) {
            if(node != null){
                queue.offer(node);
            }
        }
        ListNode c = r;
        while(!queue.isEmpty()){
            ListNode poll = queue.poll();
            c.next = poll;
            c = c.next;
            if(poll.next != null){
                queue.offer(poll.next);
            }
        }
        return r.next;
    }

    public ListNode mergeKLists(ListNode[] lists, int lo, int hi){
        if(lo == hi){
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        return mergeTwoLists(mergeKLists(lists, lo, mid), mergeKLists(lists, mid+1, hi));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b){
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }

        if(a.val <= b.val){
            a.next = mergeTwoLists(a.next, b);
            return a;
        }else{
            b.next = mergeTwoLists(a, b.next);
            return b;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}