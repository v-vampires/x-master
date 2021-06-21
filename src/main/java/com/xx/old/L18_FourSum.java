package com.xx.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针
public class L18_FourSum{
  public static void main(String[] args) {
       Solution solution = new L18_FourSum().new Solution();
      System.out.println(solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nNumsTarget(nums, 4, target, 0);
    }

    private List<List<Integer>> twoNumsTarget(int[] nums, int target, int startIndex){
        int lo = startIndex, hi = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        while(lo < hi){
            int left = nums[lo];
            int right = nums[hi];
            int sum = left + right;
            if(sum == target){
                ArrayList<Integer> r = new ArrayList<>();
                r.add(left);
                r.add(right);
                result.add(r);
                while(lo < hi && left == nums[lo]){
                    lo++;
                }
                while(lo < hi && right == nums[hi]){
                    hi--;
                }
            }else if(sum < target){
                while(lo < hi && left == nums[lo]){
                    lo++;
                }
            }else if(sum > target){
                while(lo < hi && right == nums[hi]){
                    hi--;
                }
            }
        }
        return result;
    }

    private List<List<Integer>> nNumsTarget(int[] nums, int n,  int target, int startIndex){
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < n){
            return result;
        }
        if(n==2){
            return twoNumsTarget(nums, target, startIndex);
        }else{
            for (int i = startIndex; i < nums.length; i++) {
                List<List<Integer>> lists = nNumsTarget(nums, n-1, target - nums[i], i + 1);
                if(lists.size() > 0){
                    for (List<Integer> list : lists) {
                        list.add(0, nums[i]);
                        result.add(list);
                    }
                }
                while(i+1 < nums.length && nums[i] == nums[i+1]){
                    i++;
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}