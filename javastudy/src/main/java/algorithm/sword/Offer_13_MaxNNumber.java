package algorithm.sword;

/**
 * 大数问题：
 *    打印从 1 到最大 n 位数
 *    输入数字 n，按顺序打印出从 1 最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
 * 选择方法一：排列方式，代码简单
 *
 * @Writer ArtisanLS
 * @Date 2023/1/10
 */
public class Offer_13_MaxNNumber {
    public static void main(String[] args) {
        System.out.println((char)(1 + '0')); // 1
        maxNNumber1(3); // 1 2 3 ... 999
        System.out.println();
        maxNNumber2(2); // 1 2 3 ... 99
        System.out.println();
        maxNNumber3(2); // 1 2 3 ... 99
    }

    /**
     * 把问题转成数字排列的解法：
     *    如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
     *    也就是说，我们把数字的每一位都从 0 到 9 排列一遍，就得到了所有的十进制数。只是在打印的时候，排在前面的0不打印出来罢了。
     * @param n
     */
    public static void maxNNumber1(int n) {
        if (n <= 0) {
            System.out.println("非法输入");
            return;
        }
        // 将number的每个数字为 '0'
        char[] number = new char[n];
        for (int i = 0; i < n; i++) {
            number[i] = '0';
        }

        numberArray(number, n, 0);
    }

    /*
      递归：得到数组每个值，一个个打印，再赋值，如此反复
      @param length 数组长度
      @param index  数组的下标
     */
    public static void numberArray(char[] number, int length, int index) {
        if (index > length - 1) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index] = (char) ('0' + i);
            numberArray(number, length, index + 1);
        }
    }

    // 打印 char[] 数组中 非0 字符开头的数字
    public static void printNumber(char[] number) {
        boolean isBeginZero = true;
        int nLength = number.length;

        for (int i = 0; i < nLength; i++) {
            if (isBeginZero && number[i] != '0') { // 遇到第一个非0的字符，改变isBeginZero值，打印非0字符后的字符
                isBeginZero = false;
            }
            if (!isBeginZero) {
                System.out.print(number[i]);
            }
        }
        System.out.print(" ");
    }

    /**
     * 在字符串上模拟数字加法的解法：
     *    我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
     *    因此我们需要做两件事：一是在字符串表示的数字上模拟加法；二是把字符串表示的数字打印出来。
     *
     * @param n
     */
    public static void maxNNumber2(int n) {
        if (n <= 0) return;

        // 将number的每个数字为 '0'
        char[] number = new char[n];
        for (int i = 0; i < n; i++) {
            number[i] = '0';
        }

        while (!increment(number)) {
            printNumber(number);
        }
    }

    // 从 1 开始增加，一个个填充数组 number
    public static boolean increment(char[] number) {
        boolean isOverflow = false; // 是否超过最大数
        int nTakeOver = 0; // 进位数
        int nLength = number.length;

        for (int i = nLength - 1; i >= 0; i--) {
            int nSum = number[i] - '0' + nTakeOver;

            if (i == nLength - 1) nSum++;

            if (nSum >= 10) {
                if (i == 0) {
                    isOverflow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) ('0' + nSum);
                break;
            }
        }
        return isOverflow;
    }

    /**
     * 不好的解法：
     *    先求出最大的n位数，然后用一个循环从 1 开始逐个打印。缺点是当输入的n很大的时候，我们求最大的n位数的值时会溢出。
     * 
     * @param n
     */
    public static void maxNNumber3(int n) {
        int num = 1;
        for (int i = 0; i < n; i++) {
            num *= 10;
        }
        for (int i = 1; i < num; i++) {
            System.out.print(i + " ");
        }
    }
}
