package com.xx.old;

import leetcode.editor.cn.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//给定一个二叉树，返回它的 前序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树
public class L144_BinaryTreePreorderTraversal{
  public static void main(String[] args) {
       Solution solution = new L144_BinaryTreePreorderTraversal().new Solution();
      System.out.println(solution.preorderTraversal(TreeNode.of(Arrays.asList(1, null, 2, null, null, 3, null))));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        preorderTraversal(root, ret);
        return ret;
    }

    private void preorderTraversal(TreeNode root, List<Integer> ret) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();

        while(!stack.isEmpty() || root != null){
            while(root != null){
                ret.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                TreeNode pop = stack.pop();
                root = pop.right;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}