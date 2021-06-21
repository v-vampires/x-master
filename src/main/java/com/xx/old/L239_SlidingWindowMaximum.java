package com.xx.old;

import java.util.Arrays;
import java.util.PriorityQueue;

//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window
public class L239_SlidingWindowMaximum{
  public static void main(String[] args) {
       Solution solution = new L239_SlidingWindowMaximum().new Solution();
      System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {


        int[] r = new int[nums.length-k+1];

        PriorityQueue<Integer> q = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i = 0; i < k-1; i++) {
            q.offer(nums[i]);
        }

        for (int i = k-1; i < nums.length ; i++) {
            q.offer(nums[i]);
            r[i-(k-1)] = q.peek();
            q.remove(nums[i-(k-1)]);
        }
        /*for (int i = 0; i < nums.length - k+1; i++) {
            int m = findMax(nums, i, k);
            r[i] = m;
        }*/
        return r;
    }

    private int findMax(int[] nums, int i, int k) {
        int max = nums[i];
        for (int j = i+1; j < i+ k; j++) {
            if(nums[j] > max){
                max = nums[j];
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}