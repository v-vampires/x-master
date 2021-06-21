package com.xx.old;

import java.util.*;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表
public class L347_TopKFrequentElements{
  public static void main(String[] args) {
       Solution solution = new L347_TopKFrequentElements().new Solution();
      System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++count);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k+1, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if(queue.size() == k+1){
                queue.poll();
            }
        }
        int[] r = new int[k];
        while(!queue.isEmpty()){
            Map.Entry<Integer, Integer> poll = queue.poll();
            r[--k] = poll.getKey();
        }
        return r;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}