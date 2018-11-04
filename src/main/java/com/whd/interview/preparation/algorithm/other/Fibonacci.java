package com.whd.interview.preparation.algorithm.other;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/4 15:46
 * @apiNote Fibonacci
 **/
public class Fibonacci {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("startTime = " + startTime);
        System.out.println("第n项数字为：" + fun1(45));
        long time = System.currentTimeMillis() - startTime;
        System.out.println("计算时间为：" + time + "毫秒");
    }

    /***
     * 递归算法
     * @param n 第n个数
     * @return int
     */
    private static int fun(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fun(n - 1) + fun(n - 2);
        }
    }

    /***
     * 非递归算法
     * @param n 第n个数
     * @return int
     */
    private static int fun1(int n) {
        int num1 = 1, num2 = 1, num3 = 0;
        for (int i = 3; i <= n; i++) {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return num3;
    }

}
