package com.xx;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yifanl
 * @Date 2020/6/4 20:19
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(climbingCount(5));
        System.out.println(atoi("231.0"));

        byte a = 1,b =2;
        byte c = (byte) (a+b);

    }

    public static int atoi(String str) {
        if(str == null) {
            return 0;
        }
        str = str.trim();
        if(str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int r = 0;
        boolean isNegative = false;
        for (int i = 0; i < chars.length; i++) {
            boolean valid = isValid(i, chars[i]);
            if(valid && chars[i] == 43 && i == 0){//正数
                isNegative = false;
            }else if(valid && chars[i] == 45 && i == 0){//负数
                isNegative = true;
            }else if(valid){
                //越界
                if(!isNegative && (r > Integer.MAX_VALUE / 10 || (r == Integer.MAX_VALUE /10 && chars[i] > '7'))){
                    return Integer.MAX_VALUE;
                }
                if(isNegative && (r > Integer.MAX_VALUE / 10 || (r == Integer.MAX_VALUE / 10 && chars[i] > '8'))){
                    return Integer.MIN_VALUE;
                }
                r = r * 10 + (chars[i] - 48);
            }else if(chars[i] == '.'){//后面不要
                return r;
            }else{//无效
                return 0;
            }
        }
        return isNegative ? 0 - r : r;
    }

    private static boolean isValid(int index, char c){
        if(index == 0){
            return (c >=48 && c <=57) || c == 43 || c == 45;
        }else{
            return c >=48 && c <= 57;
        }
    }



    /**
     * f(n) = f(n-1)+f(n-2)
     * @param n
     * @return
     */
    public static int climbingCount(int n){
        if(n < 0){
            return 0;
        }
        if(n <= 2){
            return n;
        }
        int c1 = 1;
        int c2 = 2;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = c1+c2;
            c1 = c2;
            c2 = c;
        }
        return c;
    }

    public static String maxRepeat(String s){
        if(s == null || s.length() == 0){
            return "";
        }

        //s.indexOf()!=s.lastIndexOf()
        return null;
    }
}
