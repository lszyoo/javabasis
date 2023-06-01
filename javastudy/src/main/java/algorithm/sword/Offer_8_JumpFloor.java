package algorithm.sword;

/**
 * 跳台阶：
 * <p>
 *     跳上 n 级台阶总共多少种跳法？
 *     青蛙跳台阶：可跳 1、2 级
 *     解题思路：
 *        第 n 级的跳法数 = 第 n-1 级的跳法数 + 第 n-2 级的跳法数，可见为斐波那契数列
 *        注意初始值的数值是 0、1、2
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class Offer_8_JumpFloor {
    public static void main(String[] args) {
        System.out.println(jumpFloor1(6)); // 13
        System.out.println(jumpFloor2(6)); // 13
        System.out.println(jumpFloor3(6)); // 13
    }

    // 数组迭代
    public static int jumpFloor1(int n) {
        if (n < 1) return 0;

        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        if (n <= 2) {
            return arr[n];
        }

        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    // 变量迭代
    public static int jumpFloor2(int n) {
        if (n < 1) return 0;

        int[] arr = {0, 1, 2};
        if (n <= 2)
            return arr[n];
        int f1 = 1;
        int f2 = 2;
        int fn = 0;
        for (int i = 3; i <= n; i++) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        return fn;
    }

    // 递归
    public static int jumpFloor3(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return jumpFloor3(n - 1) + jumpFloor3(n - 2);
        }
    }
}
