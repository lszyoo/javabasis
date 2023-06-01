/**
 * @Writer ArtisanLS
 * @Date 2022/12/26
 */
public class Find {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 8, 1, 4, 6};
        System.out.println(binaryFind(arr, 8));
    }

    public static int binaryFind (int[] arr, int key) {
        if (arr == null) return -1;
        if (arr.length == 0) return -1;

        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -2;
    }
}
