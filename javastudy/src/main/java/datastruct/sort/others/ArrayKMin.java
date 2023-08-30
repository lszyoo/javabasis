package datastruct.sort.others;

import datastruct.sort.exchange.QuickSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 寻找数组中最小的 K 个值
 *
 * @Writer ArtisanLS
 * @Date 2023/7/7
 */
public class ArrayKMin {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 7, 5, 3, 1};
        System.out.println(arrayKMin1(arr, 4)); // [1, 3, 4, 5]
        System.out.println(arrayKMin2(arr, 4)); // [1, 3, 4, 5]
    }

    /**
     * 进行K次冒泡
     * 复杂度：
     *      时间：O(kn) = O(n)
     *      空间：O(1)
     * @param arr
     * @param k
     * @return
     */
    public static List<Integer> arrayKMin1(int[] arr, int k) {
        List<Integer> res = new ArrayList<>(k);
        if (arr == null || k > arr.length) {
            return res;
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            res.add(arr[arr.length - i - 1]);
        }

        return res;
    }

    /**
     * 快排的改进
     * 思路： 找到一个pivot，使得恰好有k个数小于pivot（在其左边）。
     * 复杂度：
     *      时间：O(n)
     *      空间：O(1)
     * @param arr
     * @param k
     * @return
     */
    public static List<Integer> arrayKMin2(int[] arr, int k) {
        List<Integer> res = new ArrayList<>(k);
        if (arr == null || k > arr.length) {
            return res;
        }

        int low = 0;
        int high = arr.length - 1;
        int index = QuickSort.partition(arr, low, high);

        while (index != k - 1) {
            if (index > k - 1) {
                high = index - 1;
            } else {
                low = index + 1;
            }
            index = QuickSort.partition(arr, low, high);
        }

        for (int i = 0; i < k; i++) {
            res.add(arr[i]);
        }

        return res;
    }
}
