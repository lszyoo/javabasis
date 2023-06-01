package algorithm.sword;

/**
 * 数值的整数次方：
 *    给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
 * 分析：
 *    注意考虑指数的正负、底数是否为零等情况。
 *    快速幂的计算过程是，比如求3的10次方。
 *    当前的次方是偶数次方，底数平方，指数除以2；当前的次方是奇数时，answer先乘底数，底数再做平方，指数除以2，直到指数为0退出循环。
 *    在这个过程中，由于每次指数都变成了原来的一半，减少了循环中相乘的次数，所以时间复杂度变为O（logN）
 *          { x^(n/2) * x^(n/2) , n 为 偶数
 *    x^n = {
 *          { x * (x^(n/2) * x^(n/2)) , n 为 奇数
 * @Writer ArtisanLS
 * @Date 2023/1/10
 */
public class Offer_12_Power {
    public static void main(String[] args) {
        System.out.println(5 >> 1); // 2
        System.out.println(power(2, 5)); // 32.0
        System.out.println(power(3.1, 5)); // 286.2915100000001，29151之后为约数
    }

    public static double power(double base, int exponent) {
        if (base == 0) return 0;
        double result = 1;
        int n = 0;
        if (exponent > 0) {
            n = exponent;
        } else if (exponent < 0) {
            base = 1 / base;
            n = -exponent;
        } else {
            return 1; // 任何数的 0 次幂均为 1
        }

        while (n != 0) {
            if ((n & 1) == 1) { // 判断指数 n 是否为奇数
                result *= base;
            }
            base *= base;
            n >>= 1; // 二进制向右移动一位，即指数整数除以 2
        }
        return result;
    }
}
