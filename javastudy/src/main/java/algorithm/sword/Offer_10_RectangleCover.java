package algorithm.sword;

/**
 * 矩形覆盖：
 *    我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
 *    请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法?
 * <p>
 *    解题思路：类比跳台阶第8题
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/1/6
 */
public class Offer_10_RectangleCover {
    public static void main(String[] args) {
        System.out.println(rectangleCover(4)); // 5
        System.out.println(rectangleCover(6)); // 13
    }

    public static int rectangleCover(int n) {
        return Offer_8_JumpFloor.jumpFloor1(n);
    }
}
