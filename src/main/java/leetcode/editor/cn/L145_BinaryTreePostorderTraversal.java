package leetcode.editor.cn;

import leetcode.editor.cn.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//给定一个二叉树，返回它的 后序 遍历。
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
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树
public class L145_BinaryTreePostorderTraversal{
  public static void main(String[] args) {
       Solution solution = new L145_BinaryTreePostorderTraversal().new Solution();
      System.out.println(solution.postorderTraversal(TreeNode.of(Arrays.asList(1, null, 2, null, null, 3, null))));
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        postorderTraversal(root, ret);
        return ret;
    }

    private void postorderTraversal(TreeNode root, List<Integer> ret) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            ret.add(0, node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}