package algorithm.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 百度：将非负十进制整数 n 转换成 b 进制（其中 b = 2~16）
 * <p>
 *     例子（3进制）：
 *        1 -> a
 *        2 -> b
 *        3 -> c
 *        4 -> aa     1 * 3 + 1
 *        5 -> ab     1 * 3 + 2
 *        6 -> ac     1 * 3 + 3
 *        7 -> ba     2 * 3 + 1
 *
 *        3｜4
 *         ｜————   1   1 * 3^1 + 1 * 3^0
 *         3｜  1
 *          ｜————  1
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/2/14
 */
public class SysConvert {
    public static void main(String[] args) {
        System.out.println(sysConvert1(14, 3)); // 112
        System.out.println(sysConvert2(9, 3));  // 100
        System.out.println(ten2Three(9)); // baa
    }

    /**
     * n 是十进制，b 是需要转换成的进制
     */
    // 递归
    public static String sysConvert1(int n, int b) {
        if (n <= 0) return "n 是非负整数！！！";
        if (b < 2 || b > 16 || b == 10) return "b 的范围是 2~16，且不等于 10 ！！！";
        if (n < b) { // 如果 n < b，返回数据本身
            if (n < 10) return String.valueOf(n);
            else if (10 == n) return "A";
            else if (11 == n) return "B";
            else if (12 == n) return "C";
            else if (13 == n) return "D";
            else if (14 == n) return "E";
            else return "F";
        } else {
            if (n % b < 10) return sysConvert1(n / b, b).concat(String.valueOf(n % b));
            else if (n % b == 10) return sysConvert1(n / b, b).concat("A");
            else if (n % b == 11) return sysConvert1(n / b, b).concat("B");
            else if (n % b == 12) return sysConvert1(n / b, b).concat("C");
            else if (n % b == 13) return sysConvert1(n / b, b).concat("D");
            else if (n % b == 14) return sysConvert1(n / b, b).concat("E");
            else  return sysConvert1(n / b, b).concat("F");
        }
    }

    // 迭代
    public static String sysConvert2(int n, int b) {
        if (n <= 0) return "n 是非负整数！！！";
        if (b < 2 || b > 16 || b == 10) return "b 的范围是 2~16，且不等于 10 ！！！";

        List<String> bs = new ArrayList<>();

        // 当除到最后时 n 为 0，取不到最后的余数，所以分两种情况：(1) >= b (2) < b
        while (n >= b) {
            int a = n % b;
            if (a < 10) bs.add(String.valueOf(a));
            else if (a == 10) bs.add("A");
            else if (a == 11) bs.add("B");
            else if (a == 12) bs.add("C");
            else if (a == 13) bs.add("D");
            else if (a == 14) bs.add("E");
            else bs.add("F");
            n = n / b;
        }
        // 如果 n < b，返回数据本身
        if (n < b) {
            if (n < 10) bs.add(String.valueOf(n));
            else if (10 == n) bs.add("A");
            else if (11 == n) bs.add("B");
            else if (12 == n) bs.add("C");
            else if (13 == n) bs.add("D");
            else if (14 == n) bs.add("E");
            else bs.add("F");
        }
        Collections.reverse(bs);
        return String.join( "", bs);
    }

    /**
     * 十进制转三进制
     */
    public static String ten2Three(int n) {
        if (n <= 0) return "n 是非负整数！！！";

        List<String> list = new ArrayList<>();
        while (n >= 3) {
            int a = n % 3;
            if (a == 0) list.add("a");
            if (a == 1) list.add("b");
            if (a == 2) list.add("c");
            n = n / 3;
        }
        if (n < 3) {
            if (n == 1) list.add("b");
            if (n == 2) list.add("c");
        }
        Collections.reverse(list);
        return String.join("", list);
    }
}
