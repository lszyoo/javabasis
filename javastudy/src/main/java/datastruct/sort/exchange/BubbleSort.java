package datastruct.sort.exchange;

import java.util.Arrays;

/**
 * 冒泡排序是一种交换排序，其基本思想是：
 *    对相邻的元素进行两两比较，顺序相反则进行交换，这样，每一趟会将最小或最大的元素“浮”到顶端，最终达到完全有序。
 * <p>
 *     时间复杂度：O(n^2)
 *     空间复杂度：O(1)
 *     冒泡排序是稳定的
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2022/11/24
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = bubbleSort(new int[]{4, 7, 5, 3, 1});
        System.out.println(Arrays.toString(arr)); // [1, 3, 4, 5, 7]
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null) return null;

        int len = arr.length;
        if (len == 1) return arr;

        // 从小到大排序
        for (int i = 0; i < len; i++) { // n 次交换
            // 数组最右端的 i 个值已经排好序，arr[j + 1] 可取到数组末端
            for (int j = 0; j < len - i - 1; j++) {
                // 两两交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }
}
