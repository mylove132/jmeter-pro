package com.lzh.jdh.qa.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController()
@RequestMapping("/api")
public class WebHookController {

    @PostMapping("/webhook")
    public ResponseData<?> webHookController (@RequestBody JSONObject jsonObject, HttpServletRequest request) {
      log.info(jsonObject.toString());
      log.info(request.toString());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.reverse();
      return new ResponseUtil().success();
    }
    public static String reverse2(String s) {
        int length = s.length();
        String reverse = "";
        for (int i=0; i<length; i++)
            reverse = s.charAt(i) + reverse;
        return reverse;
    }
    public static String reverse3(String s) {
        int length = s.length();
        String reverse = "";
        for (int i=length-1; i>=0; i--)
            reverse = reverse + s.charAt(i);
        return reverse;
    }
    public static String reverse7(String s) {
        char[] array = s.toCharArray();

        int begin = 0;
        int end = s.length() - 1;

        while (begin < end) {
            array[begin] = (char) (array[begin] ^ array[end]);
            System.out.println(array[begin]);
            array[end] = (char) (array[end] ^ array[begin]);
            System.out.println(array[end]);
            array[begin] = (char) (array[end] ^ array[begin]);
            System.out.println(array[begin]);
            begin++;
            end--;
        }

        return new String(array);
    }

    public static int fqnb (int num) {
      if (num == 1 || num == 2) {
          return 1;
      }
      if (num > 2) {
          return fqnb(num - 1) + fqnb(num - 2);
      }
      return  -1;
    }

    public static long fqnb1 (int num) {
       if (num <= 0) {
           return  -1;
       }
       if (num == 1 || num == 2) {
           return 1;
       }
       long a = 1l;
       long b = 1l;
       long c = 0l;
       int result = 0;
       for (int i = 0;i<num-2;i++) {
           c = a + b;
           a = b;
           b = c;
           System.out.println(c);
           result += c;
       }
        System.out.println(result + 2);
       return c;
    }

    public static void mp0 (int[] arr) {
        // 比较的轮数为数组长度-1
        for (int i = 0;i<arr.length - 1;i++) {
            // 每一轮数组比较的次数
            for (int k = 0;k<arr.length - 1 - i;k++){
                int temp = 0;
                if (arr[k] > arr[k+1]) {
                    temp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d,", arr[i]);
        }
        System.out.println();
    }

    public static int fn (int num) {
        if (num < 0) {
            throw new RuntimeException("阶乘数不能小于0");
        }
        if (num == 0 || num == 1) {
            return 1;
        }
        return num * fn(num - 1);
    }

    public static void mp (int[] arr) {
        int temp;
        for (int i = 0; i< arr.length - 1; i++) {
            for (int k = 0; k < arr.length -1 - i; k ++ ){
                if (arr[k] > arr[k+1]) {
                    temp = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = temp;
                }
            }
        }

        for (int z = 0; z<arr.length - 1;z++ ) {
            System.out.println(arr[z]);
        }
    }

    public static void bubble_sort(int[] arr) {
        int i, j, temp, len = arr.length;
        for (i = 0; i < len - 1; i++)
            for (j = 0; j < len - 1 - i; j++)
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void main(String[] args) {
        mp0(new int[]{8,3,2,11,65,2,33,100,29});
        long rs = fqnb1(10);
        System.out.println(rs);
        int result = fn(5);
        System.out.println(result);
        System.out.println(reverse7("abcefgavcy"));
       mp(new int[]{3,1,4,5,2,7,9,2});
        new Thread(new Task(true)).start();
        new Thread(new Task(false)).start();
    }

    private static final Object a = new Object();
    private static final Object b = new Object();

    static class Task implements Runnable {

        private boolean flag = true;

        Task (boolean flag) {
            this.flag = flag;
        }
        @Override
        public void run() {
            if (flag) {
                synchronized (a) {
                    System.out.println("a获取到锁---(a)");
                    synchronized (b) {
                        System.out.println("b获取到锁");
                    }
                }
            } else {
                synchronized (b) {
                    System.out.println("b获取到锁----(b)");
                    synchronized (a) {
                        System.out.println("a获取到锁");
                    }
                }
            }
        }
    }
}
