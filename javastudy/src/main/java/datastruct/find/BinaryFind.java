package datastruct.find;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/26
 */
public class BinaryFind {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 7};
        System.out.println(binaryFind(arr, 4)); // 2
        System.out.println(binaryFind(arr, 9)); // -2
    }

    /**
     * 折半查找（二分查找）
     * @param arr
     * @param key
     * @return
     */
    public static int binaryFind(int[] arr, int key) {
        if (arr == null) return -1;

        int len = arr.length;
        if (len == 0) return -1;

        int low = 0;
        int high = len - 1; // 防止数组越界
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        // 如果未找到则返回状态码：-2
        return -2;
    }
}
