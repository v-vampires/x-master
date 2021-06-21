package leetcode.editor.cn;
//给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。 
//
// 示例 1 : 
//
// 
//输入:nums = [1,1,1], k = 2
//输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
// 
//
// 说明 : 
//
// 
// 数组的长度为 [1, 20,000]。 
// 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

// 
// Related Topics 数组 哈希表 
// 👍 958 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 利用前缀和公式s[i] = s[i-1] + a[i],获得前缀和数组
 * 2. 利用s[r] - s[l-1]=k 获取连续子数组和为k的子段（l，r）
 * 3. 优化：使用map记录s[l-1],类似于两数之和为target的方案
 */
public class L560_SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new L560_SubarraySumEqualsK().new Solution();
        System.out.println(solution.subarraySum(new int[]{1, 2, 1,2}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int[] sum = new int[nums.length + 1];
            sum[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
            int ans = 0;
            Map<Integer,/*sum[i], count*/ Integer> map = new HashMap<>();
            for (int i = 0; i < sum.length; i++) {
                final Integer count = map.getOrDefault(sum[i] - k, 0);
                ans += count;
                map.put(sum[i], map.getOrDefault(sum[i], 0)+1);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}