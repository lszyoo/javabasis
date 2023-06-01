package algorithm.sword;

/**
 * 旋转数组的最小数字：
 *    把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * <p>
 *    问题：
 *       输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 *       例如：数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转，该数组的最小值为 1。
 *       NOTE：给出的所有元素都大于 0，若数组大小为 0，请返回 0
 * </p>
 *
 * 二分查找法：时间复杂度 O(logn)
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class Offer_6_MinNumInRotateArray {
    public static void main(String[] args) {
        // 情况1
        int[] arr1 = new int[]{3, 4, 5, 6, 1, 2};
        System.out.println(minNumInRotateArray(arr1)); // 1

        // 情况2
        int[] arr2 = new int[]{6, 7, 1, 2, 3, 4};
        System.out.println(minNumInRotateArray(arr2)); // 1
    }

    public static int minNumInRotateArray(int[] arr) {
        if (arr == null) return 0;

        int len = arr.length;
        if (len == 0) return 0;

        int high = len - 1;
        int low = 0;
        // 跳出循环时，low = high
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            }
            if (arr[mid] < arr[high]) {
                // 不能用 mid+1，可能结果就是下标为 mid 的值
                high = mid;
            }
        }
        return arr[low];
    }
}
