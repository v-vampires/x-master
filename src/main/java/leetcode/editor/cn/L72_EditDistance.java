package leetcode.editor.cn;
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1497 👎 0

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class L72_EditDistance {
    public static void main(String[] args) {
        Solution solution = new L72_EditDistance().new Solution();
        System.out.println(solution.minDistance("rad", "apple"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        private Map<String, Integer> mem = new HashMap<>();

        public int minDistance(String word1, String word2) {
            //return minDistance(word1, word2, word1.length() - 1, word2.length() - 1);
            return minDistance2(word1, word2);
        }

        /**
         * 返回字符串word1[0...i]转换成word2[0...j]需要的最小步骤
         *
         * @param s1
         * @param s2
         * @param i
         * @param j
         * @return
         */
        private int minDistance(String s1, String s2, int i, int j) {
            if (i == -1) {//如果word1是空，则需要执行插入j+1步，因为word2的length=j+1，不是j！j是index
                return j + 1;
            }

            if (j == -1) {//如果word2是空，则需要对word1执行删除i+1步。
                return i + 1;
            }
            if (mem.containsKey(i + "-" + j)) {
                return mem.get(i + "-" + j);
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                return minDistance(s1, s2, i - 1, j - 1);
            } else {
                int a = minDistance(s1, s2, i, j - 1) + 1;//插入：直接在s1[i]后面，插入一个和s2[j]一样的字符，那么s2[j]，就匹配了，前移j，继续对比
                int b = minDistance(s1, s2, i - 1, j) + 1;//删除：直接删除s1[i],前移i继续和s2[j]对比
                int c = minDistance(s1, s2, i - 1, j - 1) + 1;//替换：直接替换s1[i]的字符为s2[j],然后i,j分别前移继续对比
                int res = Math.min(Math.min(a, b), c);
                mem.put(i + "-" + j, res);
                return res;
            }
        }

        private int minDistance2(String s1, String s2) {
            final int m = s1.length();
            final int n = s2.length();
            Node[][] dp = new Node[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                dp[i][0] = new Node(i,1);
            }
            for (int j = 0; j <= n; j++) {
                dp[0][j] = new Node(j, 2);
            }
            dp[0][0] = new Node(0, 0);
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = new Node(dp[i-1][j-1].distance, 0);
                    } else {
                        int a = dp[i - 1][j].distance;
                        int b = dp[i][j - 1].distance;
                        int c = dp[i - 1][j - 1].distance;
                        int min = Math.min(Math.min(a,b) ,c);
                        int choice = 0;
                        if(min == c){
                            choice = 3;
                        }else if(min == a){
                            choice = 2;
                        }else if(min == b){
                            choice = 1;
                        }
                        dp[i][j] = new Node(min+1, choice);
                    }
                }
            }
            return dp[m][n].distance;
        }
    }

    class Node {
        int distance;
        int choice; //0代表啥都不做，1代表插入；2代表删除；3代表替换

        public Node(int distance, int choice) {
            this.distance = distance;
            this.choice = choice;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}