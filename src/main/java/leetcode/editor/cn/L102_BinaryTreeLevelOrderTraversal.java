package leetcode.editor.cn;

import leetcode.editor.cn.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索
public class L102_BinaryTreeLevelOrderTraversal{
  public static void main(String[] args) {
       Solution solution = new L102_BinaryTreeLevelOrderTraversal().new Solution();
      System.out.println(solution.levelOrder(TreeNode.of(Arrays.asList(3, 9, 20, null, null, 15, 7))));
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        levelOrder(root, ret, 0);
        return ret;
    }

    private void levelOrder(TreeNode root, List<List<Integer>> ret, int level) {
        if(root == null){
            return;
        }
        if(ret.size() <= level){
            ret.add(new ArrayList<>());
        }
        List<Integer> list = ret.get(level);
        list.add(root.val);
        levelOrder(root.left, ret, level+1);
        levelOrder(root.right, ret, level+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}