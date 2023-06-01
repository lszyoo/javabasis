package datastruct.sort.select;

import java.util.Arrays;

/**
 * 堆排序是一种选择排序
 *
 * <p>
 *     堆排序的基本思想是：
 *        将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，
 *        此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 *        如此反复执行，便能得到一个有序序列了。
 *     步骤一：构造初始堆。将给定无序序列构造成一个大顶堆（一般升序采用大顶堆，降序采用小顶堆)；
 *     步骤二：将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端；
 *     步骤三：重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整交换步骤，直到整个序列有序。
 * </p>
 *
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 堆排序是不稳定的。
 * 不适合待排序序列个数较少的情况。
 *
 * @Writer ArtisanLS
 * @Date 2022/11/25
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = heapSort(new int[]{23, 2, 5, 6, 3, 1});
        System.out.println(Arrays.toString(arr));
    }

    public static int[] heapSort(int[] arr) {
        if (arr == null) return null;

        // 堆的大小
        int heapSize = arr.length - 1;

        // 初始化大顶堆，堆的下标即数组下标，将数组初始化为大顶堆
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeap(arr, heapSize, i);
        }

        // 堆的最后一位与第一位作比较，并互换位置
        for (int i = heapSize; i > 0; i--) {
            if (arr[i] < arr[0]) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                // 重构大顶堆
                maxHeap(arr, i, 0);
            }
        }
        return arr;
    }


    /**
     * 确保最大堆中父节点的值比子节点的值都大，即大顶堆
     * @param arr           初始数组，存储需要排序的元素
     * @param heapSize      堆大小，即数组长度
     * @param index         父节点在数组中的位置
     */
    public static void maxHeap(int[] arr, int heapSize, int index) {
        // 左子节点与父节点在堆中的下标关系
        int left = 2 * index + 1;
        // 右子节点与父节点在堆中的下标关系
        int right = 2 * index + 2;
        // 最大的数值下标
        int largeSize = index;

        // 防止数组越界，如果左子节点大于父节点，那么互换在堆中的位置
        if (left < heapSize && arr[largeSize] < arr[left]) {
            largeSize = left;
        }
        // 防止数组越界，如果右子节点大于父节点，那么互换在堆中的位置
        if (right < heapSize && arr[largeSize] < arr[right]) {
            largeSize = right;
        }
        if (largeSize != index) {
            int temp = arr[index];
            arr[index] = arr[largeSize];
            arr[largeSize] = temp;

            /*
              因为将最大值和父节点交换了位置，新的子节点并不能保证一定是比它的子节点大，
              所以需要递归，确定交换的子节点比它的子节点都大，
              而没有动的子节点是不需要进行递归的，因为它的数值没有变，如果之前满足最大堆条件，现在就还是满足的
             */
            maxHeap(arr, heapSize, largeSize); // 如：第二次构建大顶堆的根节点大于第一次构建大顶堆的根节点
        }
    }
}

