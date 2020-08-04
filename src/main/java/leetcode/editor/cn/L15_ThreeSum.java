package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针
public class L15_ThreeSum{
  public static void main(String[] args) {
       Solution solution = new L15_ThreeSum().new Solution();
      System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSumTarget(nums, 3, 0, 0);
       /* List<List<Integer>> res = new ArrayList<>();
        //bad case
        if(nums == null || nums.length == 1){
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            List<List<Integer>> ints = twoSumTarget(nums, i + 1, target);
            if(ints.size() > 0){
                for (List<Integer> anInt : ints) {
                    List<Integer> r = new ArrayList<>();
                    r.add(nums[i]);
                    r.addAll(anInt);
                    res.add(r);
                }
            }
            while (i < nums.length-1 && nums[i] == nums[i+1]) {
                i++;
            }
        }
        return res;*/
    }

    public List<List<Integer>> twoSumTarget(int[] nums, int start, int target){
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while(lo < hi){
            int left = nums[lo];
            int right = nums[hi];
            int sum = left + right;
            if(sum < target){
                while(lo < hi && left == nums[lo]){
                    lo++;
                }
            }else if(sum > target){
                while(lo < hi && right == nums[hi]){
                    hi--;
                }
            }else{
                List<Integer> r = new ArrayList<>();
                r.add(left);
                r.add(right);
                res.add(r);
                while(lo < hi && left == nums[lo]){
                    lo++;
                }
                while(lo < hi && right == nums[hi]){
                    hi--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> nSumTarget(int[] nums, int n, int target, int start){
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < n){
            return res;
        }
        if(n == 2){
            return twoSumTarget(nums, start, target);
        }else{
            for (int i = start; i < nums.length; i++) {
                List<List<Integer>> r = nSumTarget(nums, n-1, target - nums[i], i+1);
                if(r.size() > 0){
                    for (List<Integer> list : r) {
                        list.add(0, nums[i]);
                        res.add(list);
                    }
                }
                while ( i+1 < nums.length && nums[i]==nums[i+1]){
                    i++;
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}