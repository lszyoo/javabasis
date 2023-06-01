package algorithm.sword;

import java.util.Arrays;

/**
 * 斐波那契数列：
 *    输入一个整数 n，请你输出斐波那契数列的第 n 项（n <= 39）
 *         { 0 (n=0)
 *    f(n)={ 1 (n=1)
 *         { f(n-1)+f(n-2) (n>1)
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class Offer_7_Fibonacci {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(fibonacci1(6))); // [0, 1, 1, 2, 3, 5, 8]
        System.out.println(fibonacci2(6)); // 8
        System.out.println(fibonacci3(6)); // 8
    }

    // 数组迭代
    public static int[] fibonacci1(int n) {
        if (n < 0) return new int[1];

        int[] fb = new int[n + 1];
        fb[0] = 0;
        fb[1] = 1;
        for (int i = 2; i <= n; i++) {
            fb[i] = fb[i - 1] + fb[i - 2];
        }
        return fb;
    }

    // 递归
    // 递归代码简洁但每次调用都得消耗时间和空间，容易造成内存调用栈的溢出，尤其是层级很高的时候。效率不是很高。
    public static int fibonacci2(int n) {
        if (n < 0) return -1;

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci2(n - 1) + fibonacci2(n - 2);
        }
    }

    // 变量迭代
    public static int fibonacci3(int n) {
        if (n < 0) return -1;

        int[] fb = {0, 1};
        if (n < 2) {
            return fb[n];
        }
        int f1 = 0;
        int f2 = 1;
        int fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        return fn;
    }
}
