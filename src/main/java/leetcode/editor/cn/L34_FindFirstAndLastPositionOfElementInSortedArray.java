package leetcode.editor.cn;

import java.util.Arrays;

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 如果数组中不存在目标值，返回 [-1, -1]。 
//
// 示例 1: 
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: [3,4] 
//
// 示例 2: 
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: [-1,-1] 
// Related Topics 数组 二分查找
public class L34_FindFirstAndLastPositionOfElementInSortedArray{
  public static void main(String[] args) {
       Solution solution = new L34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
      System.out.println(Arrays.toString(solution.searchRange(new int[]{5,7,7,8,8,10}, 8)));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1, -1};
        }
        int lo = 0, hi = nums.length - 1;
        int index = -1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] == target){
                index = mid;
                break;
            }else if(target < nums[mid]){
                hi = mid - 1;
            }else if(target > nums[mid]){
                lo = mid + 1;
            }
        }
        int start = index, end = index;
        while(nums[start] == target && start-1 >= 0 && nums[start-1] == target){
            start--;
        }
        while(nums[end] == target && end+1 < nums.length && nums[end+1] == target){
            end++;
        }
        return new int[]{start, end};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}