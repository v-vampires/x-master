package com.xx.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//给出一个区间的集合，请合并所有重叠的区间。
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组
public class L56_MergeIntervals{
  public static void main(String[] args) {
       Solution solution = new L56_MergeIntervals().new Solution();
      int[][] merge = solution.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
      for (int[] ints : merge) {
          System.out.println(Arrays.toString(ints));
      }
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return new int[][]{};
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] pre = intervals[0];
        List<int[]> r = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if(cur[0] > pre[1]){
                r.add(pre);
                pre = cur;
            }else{
                pre[1] = Math.max(pre[1], cur[1]);
            }
        }
        r.add(pre);
        return r.toArray(new int[r.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}