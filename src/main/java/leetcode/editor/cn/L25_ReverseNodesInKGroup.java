package leetcode.editor.cn;
//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 链表 
// 👍 1113 👎 0

import leetcode.editor.cn.common.ListNode;

import java.util.List;
import java.util.Stack;

public class L25_ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new L25_ReverseNodesInKGroup().new Solution();
        System.out.println(solution.reverseKGroup(ListNode.of(1, 2, 3, 4, 5, 6, 7), 1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;//表示前一次的尾节点
            while(head != null){
                ListNode endNode = getEnd(head, k);
                if(endNode == null){//不足k个，直接拼上
                    pre.next = head;
                    break;
                }
                final ListNode next = endNode.next;
                reverseBetween(head, endNode);
                pre.next = endNode;

                pre = head;
                head = next;
            }
            return dummy.next;
        }

        private void reverseBetween(ListNode startNode, ListNode endNode) {
            if(startNode == endNode){
                return;
            }
            ListNode pre = startNode;
            ListNode cur = startNode.next;
            while(cur != endNode){
                final ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            cur.next = pre;
        }

        private ListNode getEnd(ListNode head, int k) {
            ListNode endNode = head;
            while(k-- > 1){
                if(endNode == null){
                    return null;
                }
                endNode = endNode.next;
            }
            return endNode;
        }

        /**
         * 解法2：递归实现
         *
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroup2(ListNode head, int k) {
            ListNode next = head;
            for (int i = 0; i < k; i++) {
                next = next.next;
                if (next == null) {
                    return head;
                }
            }
            ListNode r = reverse(head, k);
            head.next = reverseKGroup2(next, k);
            return r;
        }

        private ListNode reverse(ListNode head, int k) {
            ListNode pre = null, cur = head, next = null;
            while (k-- > 0) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}