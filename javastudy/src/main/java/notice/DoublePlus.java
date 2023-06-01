package notice;

/**
 * 说明：
 *     前置 ++ 是将变量的值加 1 后，使用增值后的变量进行运算的，
 *     而后置 ++ 是首先将变量赋值给一个临时变量，接下来对变量的值加 1，然后使用那个临时变量进行运算
 *
 * @Writer ArtisanLS
 * @Date 2022/11/23
 */
public class DoublePlus {
    public static void main(String[] args) {
        doublePlus1(); // 0 10 9
        doublePlus2(); // 10 10
    }

    public static void doublePlus1 () {
        int m = 0, n = 0, p = 0, q = 0;
        for (int i = 0; i < 10; i++) {
            m = m++;
            n = ++n;
            p = q++;
        }
        System.out.println(m + " " + n + " " + p);
    }

    public static void doublePlus2 () {
        int m = 0, n = 0;
        for (int i = 0; i < 10; i++) {
            m++;
            ++n;
        }
        System.out.println(m + " " + n );
    }
}
