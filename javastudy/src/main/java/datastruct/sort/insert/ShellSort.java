package datastruct.sort.insert;

import java.util.Arrays;

/**
 * 希尔排序严格来说是基于插入排序的思想，又被称为缩小增量排序。
 * <p>
 *     具体流程如下：
 *          1、将包含 n 个元素的数组，分成 n/2 个数组序列，第 1 个数据和第 n/2+1 个数据为一对...
 *          2、对每对数据进行比较和交换，排好顺序；
 *          3、然后分成 n/4 个数组序列，再次排序；
 *          4、不断重复以上过程，随着序列减少并直至为 1，排序完成。
 *     举例：
 *          现在我们有一个数组，该数组有6个元素，int[] arrays = {2, 5, 1, 3, 4, 6};
 *          排序前：
 *              将该数组看成三个（arrays.length/2)数组，分别是 {2, 3}, {5, 4}, {1, 6}
 *          第一趟排序：
 *              对三个数组分别进行插入排序，因此我们三个数组得到的结果为 {2, 3}, {4, 5}, {1, 6}，此时数组是这样子的：{2, 4, 1, 3, 5, 6}
 *          第二趟排序：
 *              增量减少了，上面增量是 3，此时增量应该为 1 了，因此把 {2, 4, 1, 3, 5, 6} 看成一个数组(从宏观上是有序的了)，对其进行插入排序，直至有序
 * </p>
 *
 * 平均时间复杂度：O(n^1.3)
 * 空间复杂度：O(1)
 * 希尔排序是不稳定的。
 *
 * @Writer ArtisanLS
 * @Date 2022/12/5
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = shellSort1(new int[]{3, 5, 9, 2, 1, 10, 4});
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 9, 10]

        int[] arr2 = shellSort2(new int[]{3, 5, 9, 2, 1, 10, 4, 11, 6, 2});
        System.out.println(Arrays.toString(arr2)); // [1, 2, 2, 3, 4, 5, 6, 9, 10, 11]
    }

    /**
     * 从小到大排序
     * 以下运行过程：
     *          /2  -- [3, 5, 9, 2, 1, 10, 4]
     *                 [2, 5, 9, 3, 1, 10, 4]
     *                 [2, 1, 9, 3, 5, 10, 4]
     *          /4  -- [1, 2, 9, 3, 5, 10, 4]
     *                 [1, 2, 3, 9, 5, 10, 4]
     *                 [1, 2, 3, 5, 9, 10, 4]
     *                 [1, 2, 3, 4, 5, 9, 10]
     * 说明：希尔排序只是分组，第一次并不直接改变元素在arr中的位置
     */
    public static int[] shellSort1 (int[] arr) {
        int j;
        // 将数组中的元素分组，增量每次都 /2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从增量那组开始进行插入排序（保证 j - gap >= 0），直至完毕
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                // j - gap 就是代表与它同组隔壁的元素，此处融合了插入排序的 if 判断句
                for (j = i; j >= gap && temp < arr[j - gap]; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
        return arr;
    }

    /**
     * 这个方法类比直接排序，相对好理解。
     * 第1轮排序后的数据：[3, 4, 9, 2, 1, 10, 5, 11, 6, 2]
     * 第2轮排序后的数据：[1, 2, 3, 2, 5, 4, 6, 10, 9, 11]
     * 第3轮排序后的数据：[1, 2, 2, 3, 4, 5, 6, 9, 10, 11]
     *
     * @param arr
     * @return
     */
    public static int[] shellSort2 (int[] arr) {
        if (arr == null) return null;

        int len = arr.length;
        if (len == 1) return arr;
        // int count = 0;
        // 将数组中的元素分组，增量每次都 /2
        for (int gap = len / 2; gap >= 1; gap = gap / 2) {
            // count++;
            for (int i = gap; i < len; i++) {
                // 分组，直接插入排序
                // 类比：for (int j = i; j >= 1; j--) {
                // 从增量那组开始进行插入排序（保证 j - gap >= 0），直至完毕
                for (int j = i; j >= gap; j = j - gap) {
                    if (arr[j] < arr[j - gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    }
                }
            }
            // System.out.println("第" + count + "轮排序后的数据：" + Arrays.toString(arr));
        }
        return arr;
    }
}
