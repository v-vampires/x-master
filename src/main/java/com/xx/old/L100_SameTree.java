package com.xx.old;

import leetcode.editor.cn.common.TreeNode;

import java.util.Objects;

//给定两个二叉树，编写一个函数来检验它们是否相同。
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 示例 1: 
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true 
//
// 示例 2: 
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
// 
//
// 示例 3: 
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
// 
// Related Topics 树 深度优先搜索
public class L100_SameTree{
  public static void main(String[] args) {
       Solution solution = new L100_SameTree().new Solution();
       
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(Objects.isNull(p) && Objects.isNull(q)){
            return true;
        }
        if(Objects.isNull(p) && !Objects.isNull(q)){
            return false;
        }
        if(!Objects.isNull(p) && Objects.isNull(q)){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isMTree(TreeNode root){
        if(root == null){
            return true;
        }
        return isMTree(root.left, root.right);
    }


    public boolean isMTree(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(Objects.isNull(p) && !Objects.isNull(q)){
            return false;
        }
        if(!Objects.isNull(p) && Objects.isNull(q)){
            return false;
        }
        return isMTree(p.left, p.right) && isMTree(q.left, q.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}