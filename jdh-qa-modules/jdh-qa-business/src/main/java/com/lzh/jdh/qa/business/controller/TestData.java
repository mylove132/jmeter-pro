package com.lzh.jdh.qa.business.controller;

public class TestData {

    /**
     *求斐波那契index处的值
     */
    public static long fbnq (int index) {
        if (index <= 0) {
            throw new RuntimeException("数值不能小于等于0");
        }
        if (index <= 2) {
            return 1;
        }
        long a = 1l;
        long b = 1l;
        long c = 0;
        System.out.printf("0,1,");
        for (int i = 0; i < index - 2; i++) {
            c = a + b;
            System.out.printf("%d,", c);
            a = b;
            b = c;
        }
        System.out.println();
        return c;
    }

    public static int maxLength (String str) {
        int max = 0;
        int temp = 1;
        for (int i = 1;i<str.length();i++) {
            char pre = str.charAt(i-1);
            char now = str.charAt(i);
            if (now - pre == 1) {
                ++temp;
            } else {
                max = max > temp ? max : temp;
                temp = 1;
            }
        }
        max = max > temp ? max : temp;
        return max;
    }

    public static void mp (int[] arr) {
        for (int i = 0;i < arr.length - 1; i++) {
            for (int k = 0; k < arr.length - 1 - i; k++ ) {
                if (arr[k] > arr[k+1]) {
                    int temp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = temp;
                }
            }
        }

        for (int i = 0; i< arr.length; i++) {
            System.out.printf("%d,", arr[i]);
        }
        System.out.println();
    }

    public static String dx (String str) {
        System.out.println(maxLength("abcdefgabcabcdabcdef"));
        System.out.println("********************"+"abc".compareTo("aa"));
        char[] chs = str.toCharArray();
        char[] newChs = new char[chs.length];
        int k = 0;
        for (int i = chs.length -1; i >= 0; i--) {
            newChs[k] = chs[i];
            k++;
        }
        return new String(newChs);
    }

    public static boolean isDc (String str) {
        if (null == str) {
            throw new RuntimeException("字符串不能为空");
        }
        char[] chs = str.toCharArray();
        int fist = 0;
        int last = chs.length - 1;
        while (fist <= last) {
            if (chs[fist] != chs[last]) {
                return false;
            }
            fist++;
            last--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(dx("abcdefg"));
        System.out.println(fbnq(10));
        mp(new int[]{3,5,1,11,2,19});
        System.out.println(isDc("中国国中"));
    }
}