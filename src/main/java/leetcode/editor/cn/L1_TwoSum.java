package leetcode.editor.cn;

import java.util.*;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表
public class L1_TwoSum{
  public static void main(String[] args) {
       Solution solution = new L1_TwoSum().new Solution();
      System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 18)));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        //bad case
        if(nums == null || nums.length == 1){
            return null;
        }
        Map<Integer, Integer> numIndexMapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if(numIndexMapping.containsKey(another)){
                return new int[]{numIndexMapping.get(another), i};
            }else{
                numIndexMapping.put(nums[i], i);
            }
        }
        return null;
    }

    public List<int[]> twoSumTarget(int[] nums, int target){
        //bad case
        if(nums == null || nums.length == 1){
            return null;
        }
        List<int[]> res = new ArrayList<>();
        Arrays.sort(nums);
        int lo = 0, hi = nums.length - 1;
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
                res.add(new int[]{lo, hi});
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

}
//leetcode submit region end(Prohibit modification and deletion)

}