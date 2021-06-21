package leetcode.editor.cn;
//给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。 
//
// 
//
// 示例 1: 
//输入: 
//
// "bbbab"
// 
//
// 输出: 
//
// 4
// 
//
// 一个可能的最长回文子序列为 "bbbb"。 
//
// 示例 2: 
//输入: 
//
// "cbbd"
// 
//
// 输出: 
//
// 2
// 
//
// 一个可能的最长回文子序列为 "bb"。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 只包含小写英文字母 
// 
// Related Topics 动态规划 
// 👍 418 👎 0

public class L516_LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new L516_LongestPalindromicSubsequence().new Solution();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindromeSubseq(String s) {
            //dp[i][j] = dp[i+1][j-1] + 2  or max(dp[i][j-1], dp[i+1][j])
            //dp[i][j]表示字符串从i到j之间的最长子序列的长度，因此i一定小于j，即i>j部分一定是0;所以初始化如下
            /**
             * 1     ?
             * 0 1
             * 0 0 1
             * 0 0 0 1
             */
            int[][] dp = new int[s.length()][s.length()];
            //从下往上，从左往右推导
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = 0; j < s.length(); j++) {
                    if (i > j) {
                        dp[i][j] = 0;
                    } else if (i == j) {
                        dp[i][j] = 1;
                    } else {
                        if (s.charAt(i) == s.charAt(j)) {
                            dp[i][j] = dp[i + 1][j - 1] + 2;
                        }else{
                            dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                        }
                    }
                }
            }
            return dp[0][s.length()-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}