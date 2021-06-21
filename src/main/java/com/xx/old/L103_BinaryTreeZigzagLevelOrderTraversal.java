package com.xx.old;

import leetcode.editor.cn.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层次遍历如下： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 栈 树 广度优先搜索
public class L103_BinaryTreeZigzagLevelOrderTraversal{
  public static void main(String[] args) {
       Solution solution = new L103_BinaryTreeZigzagLevelOrderTraversal().new Solution();
      System.out.println(solution.zigzagLevelOrder(TreeNode.of(Arrays.asList(3, 9, 20, null, null, 15, 7))));
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        zigzagLevelOrder(root, ret, 0);
        return ret;
    }

    private void zigzagLevelOrder(TreeNode root, List<List<Integer>> ret, int level) {
        if(root == null){
            return;
        }
        if(ret.size() <= level){
            ret.add(new ArrayList<>());
        }
        List<Integer> list = ret.get(level);
        if(level % 2 == 1){
            list.add(0, root.val);
        }else {
            list.add(root.val);
        }
        zigzagLevelOrder(root.left, ret, level+1);
        zigzagLevelOrder(root.right, ret, level+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}