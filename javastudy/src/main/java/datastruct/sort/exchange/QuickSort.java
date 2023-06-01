package datastruct.sort.exchange;

import java.util.Arrays;

/**
 * 快速排序是冒泡排序的改进版，也是最好的一种内排序，利用了分治思想。
 *
 * <p>
 *     思想:
 *         1、在待排序的元素任取一个元素作为基准，称为基准元素；
 *         2、将待排序的元素进行分区，比基准元素大的元素放在它的右边，比其小的放在它的左边；
 *         3、对左右两个分区重复以上步骤直到所有元素都是有序的。
 * </p>
 *
 * 平均时间复杂度：O(nlogn)
 * 空间复杂度：O(nlogn)
 * 快速排序是不稳定的
 *
 * @Writer ArtisanLS
 * @Date 2022/11/24
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = quickSort(new int[]{5, 9, 4, 6, 2, 7, 3, 10}, 0, 7);
        System.out.println(Arrays.toString(arr)); // [2, 3, 4, 5, 6, 7, 9, 10]
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        if (arr == null) return null;

        if (high > low) {
            // 以第一个元素为基准分出两部分
            int index = partition(arr, low, high);
            // 递归前半部分，去除基准
            quickSort(arr, low, index - 1);
            // 递归后半部分，去除基准
            quickSort(arr, index + 1, high);
        }
        return arr;
    }

    /**
     * 每次循环必有一个 temp 值，起始 temp = key，temp 会进行传递，比如：arr = {5, 9, 4, 6, 2}
     * 起始：tempIdx = 0，temp = 5，key = 5，high = 4，low = 0
     * 第一次循环后：
     *      while1：tempIdx = 4，temp = 2，arr = {2, 9, 4, 6, 2}，high = 4
     *      while2：tempIdx = 1，temp = 9，arr = {2, 9, 4, 6, 9}，low = 1
     * 第一次循环后：
     *      while1：tempIdx = 3，temp = 4，arr = {2, 4, 4, 6, 9}，high = 2
     *      while2：tempIdx = 1，temp = 9，arr = {2, 4, 4, 6, 9}，low = 2
     * 跳出循环
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int partition(int[] arr, int low, int high) {
        // 固定切分方式，以第一个元素为基准
        int key = arr[low];
        while (high > low) {
            // 从后半部分向前扫描，如果大于等于基准，则指针向左移动
            while (arr[high] > key && high > low) {
                high--;
            }
            // high 值赋给 low 下标处，那么 low 值一定小于 key，下边 while low 必定 ++
            arr[low] = arr[high];
            // 从前半部分向后扫描，如果小于等于基准，则指针向右移动
            while (arr[low] < key && low < high) {
                low++;
            }
            // low 值赋给 high 下标处，那么 high 值一定大于 key，上边 while high 必定 ++
            arr[high] = arr[low];
        }

        /*
         将基准放回数组，以下两种均可
         arr[low] = key;
         return low;
        */
        arr[high] = key;
        return high;
    }
}

