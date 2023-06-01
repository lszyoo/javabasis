package datastruct.find;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/26
 */
public class InterpolationFind {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 7};
        System.out.println(interpolationFind(arr, 4)); // 2
    }

    /**
     * 插值查找
     * @param arr
     * @param key
     * @return
     */
    public static int interpolationFind(int[] arr, int key) {
        if (arr == null) return -1;
        if (arr.length == 0) return -1;

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -2;
    }
}
