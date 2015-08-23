package com.gavin.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Gavin
 * Mail: gavinchangcn@163.com
 * Date: 2015/8/22 0022
 * Time: 9:54
 */
public class testQuickshort {
    public static void quickshort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            quickshort(n, left, dp - 1);
            quickshort(n, dp + 1, right);
        }
    }

    public static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot) {
                --right;
            }
            if (left < right) {
                n[left++] = n[right];
            }
            while (left < right && n[left] <= pivot) {
                ++left;
            }
            if (left < right) {
                n[right--] = n[left];
            }
        }
        n[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] list = {34, 3, 53, 2, 23, 7, 14, 10};
        quickshort( list, 0, list.length-1) ;
        for(int i=0;i<list.length;i++){
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }
}
