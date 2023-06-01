package algorithm.sword;

/**
 * 变态跳台阶：
 *    跳上 n 级台阶总共多少种跳法？
 *    青蛙跳台阶：可跳 1、2、3、...、n 级
 * <p>
 *    经过推算：
 *       1 n=1
 *       2 n=2
 *       4 n=3
 *       8 n=4
 *       f(n) = 2^(n-1)
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/1/6
 */
public class Offer_9_JumpFloorExtension {
    public static void main(String[] args) {
        System.out.println(jumpFloorExtension(4)); // 8
        System.out.println(jumpFloorExtension(6)); // 32
    }

    // 变量迭代
    public static int jumpFloorExtension(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;

        int f1 = 1;
        int fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = f1 * 2;
            f1 = fn;
        }
        return fn;
    }
}
