package com.xx.sort;

import java.util.Arrays;

/**
 * @author yifanl
 * @Date 2020/4/8 23:55
 */
public class QuickSort {
    public static void sort(int[] num, int left, int right){
        if(left >= right){
            return;
        }
        int i = left, j = right;
        int temp = num[i];
        while(i < j){
            while(num[j] >= temp && i < j){
                j--;
            }
            //num[j] < temp
            num[i] = num[j];
            while(num[i] <= temp && i < j){
                i++;
            }
            //num[i] > temp
            num[j] = num[i];
        }
        //i == j
        num[i] = temp;
        sort(num, left, i-1);
        sort(num, i+1, right);
    }




    public static void swap(int[] a, int i, int j){
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void main(String[] args) {
        int[] num = new int[]{100,5,4,3,2,1};
        sort(num, 0, num.length - 1);
        System.out.println(Arrays.toString(num));
    }
}
