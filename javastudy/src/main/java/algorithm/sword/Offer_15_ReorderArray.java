package algorithm.sword;

import java.util.Arrays;

/**
 * 调整数组顺序使奇数位于偶数前面：
 *    输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 *    使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 *    并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @Writer ArtisanLS
 * @Date 2023/2/7
 */
public class Offer_15_ReorderArray {
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 1, 5, 3, 6, 7};
        System.out.println(Arrays.toString(reorderArray1(arr1))); // [1, 5, 3, 7, 2, 4, 6]
        int[] arr2 = {2, 4, 1, 5, 3, 6, 7};
        System.out.println(Arrays.toString(reorderArray2(arr2))); // [7, 3, 1, 5, 4, 6, 2]
    }

    /**
     * 保证奇数和奇数，偶数和偶数之间的相对位置不变
     * <p>
     *    解题思路：
     *       1.要想保证原有次序，则只能顺次移动或相邻交换。
     *       2.i 从左向右遍历，找到第一个偶数。
     *       3.j 从 i+1 开始向后找，直到找到第一个奇数。
     *       4.将[i,...,j-1]的元素整体后移一位，最后将找到的奇数放入 i 位置，然后 i++。
     *       5.終止條件:j 向後遍歷查找失敗。
     * </p>
     * @param arr
     * @return
     */
    public static int[] reorderArray1(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        int i = 0, j, len = arr.length;
        while (i < len) {
            while (i < len && ((arr[i] & 1) == 1)) i++; // 奇数
            j = i + 1;
            while (j < len && ((arr[j] & 1) == 0)) j++; // 偶数
            if (j < len) {
                int temp = arr[j];
                for (int k = j - 1; k >= i; k--) {
                    arr[k + 1] = arr[k];
                }
                arr[i++] = temp;
            } else {
                break;
            }
        }
        return arr;
    }

    /**
     * 不保证奇数和奇数，偶数和偶数之间的相对位置不变
     * 利用快排思想
     *
     * @param arr
     * @return
     */
    public static int[] reorderArray2(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        int start = 0, end = arr.length - 1;
        while (start < end) {
            while (start < end && judge(arr[start])) {
                start++;
            }
            while (start < end && !judge(arr[end])) {
                end--;
            }
            if (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }

        return arr;
    }

    public static boolean judge(int m) {
        if ((m & 1) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
