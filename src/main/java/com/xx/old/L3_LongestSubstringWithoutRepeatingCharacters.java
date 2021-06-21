package com.xx.old;

import java.util.HashSet;
import java.util.Set;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window
public class L3_LongestSubstringWithoutRepeatingCharacters{
  public static void main(String[] args) {
       Solution solution = new L3_LongestSubstringWithoutRepeatingCharacters().new Solution();
      System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
      System.out.println(solution.lengthOfLongestSubstring("dvdf"));
      System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int maxSubStrLen = 0;
        /*Map<Character, Integer> charIndexMapping = new HashMap<>();
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            //"abba"
            Integer index = charIndexMapping.get(s.charAt(j));
            if(index != null && index >= i){
               i = index+1;
           }
           maxSubStrLen = Math.max(maxSubStrLen, j - i + 1);
           charIndexMapping.put(s.charAt(j), j);
        }*/
        Set<Character> set = new HashSet<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if(set.contains(s.charAt(j))){
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            maxSubStrLen = Math.max(maxSubStrLen, set.size());
        }
        return maxSubStrLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}