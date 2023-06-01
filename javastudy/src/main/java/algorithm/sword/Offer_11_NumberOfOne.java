package algorithm.sword;

/**
 * 二进制中 1 的个数：
 *    输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
 * <p>
 *    思路：
 *        如果一个数 -1 ，二进制中的表示为最右边的一个1变为0，后面的所有0变为1。
 *        例如：12（1100）减去1，则变为11（1011），转变过程为：
 *             1100 -> 1000（先将最右边的1变为0） -> 1011（再将后面的0变为1）
 *        此时将当前的值，与减去1后的值按位与（1100&1011）就得到了1000（相当于已经计算了一个1，让count++），重复上述步骤，直到最后的值为0即可。
 * </p>
 * @Writer ArtisanLS
 * @Date 2023/1/10
 */
public class Offer_11_NumberOfOne {
    public static void main(String[] args) {
        System.out.println(numberOfOne(127)); // 7
        System.out.println(numberOfOne(128)); // 1
    }

    public static int numberOfOne(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
