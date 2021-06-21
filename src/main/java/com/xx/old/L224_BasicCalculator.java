package com.xx.old;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//实现一个基本的计算器来计算一个简单的字符串表达式的值。
//
// 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格 。 
//
// 示例 1: 
//
// 输入: "1 + 1"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: " 2-1 + 2 "
//输出: 3 
//
// 示例 3: 
//
// 输入: "(1+(4+5+2)-3)+(6+8)"
//输出: 23 
//
// 说明： 
//
// 
// 你可以假设所给定的表达式都是有效的。 
// 请不要使用内置的库函数 eval。 
// 
// Related Topics 栈 数学
public class L224_BasicCalculator{
  public static void main(String[] args) {
       Solution solution = new L224_BasicCalculator().new Solution();
      System.out.println(solution.calculate("1+(2-3)*2"));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if(c != ' '){
                queue.offer(c);
            }
        }
        queue.offer('+');
        return calculateQ(queue);
    }
}

    private int calculateQ(Queue<Character> queue) {
        int sum = 0;
        int num = 0;
        Character sign = '+';
        //1+2*3;
        Stack<Integer> stack = new Stack<>();
        while(!queue.isEmpty()){
            Character p = queue.poll();
            if(Character.isDigit(p)){
                num = num * 10 + (p - '0');
            }else if(p == '('){
                num = calculateQ(queue);
            }else{
                if(sign == '+'){
                    stack.push(num);
                }else if(sign == '-'){
                    stack.push(0 - num);
                }else if(sign == '*'){
                    stack.push(stack.pop() * num);
                }else if(sign == '/'){
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = p;
                if(p == ')'){
                    break;
                }
            }
        }
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
//leetcode submit region end(Prohibit modification and deletion)

}