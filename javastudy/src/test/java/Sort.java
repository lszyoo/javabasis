import java.util.Arrays;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/26
 */
public class Sort {
    public static void main(String[] args) {

    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) return arr;
        if (low < high) {
            int index = partition(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
        return arr;
    }

    public static int partition(int[] arr, int low, int high) {
        int key = arr[low];
        while (low < high) {
            while (low < high && key < arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && key > arr[low]) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = key;
        return high;
    }

    public static int[] insertSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    }
                }
            }
        }
        return arr;
    }

    public static int[] selectSort (int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int min;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    min = arr[j];
                    arr[j] = arr[i];
                    arr[i] = min;
                }
            }
        }
        return arr;
    }

    public static int[] heapSort(int[] arr) {
        if (arr == null) return arr;

        int heapSize = arr.length - 1;
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeap(arr, heapSize, i);
        }
        for (int i = heapSize; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeap(arr, i, 0);
        }
        return arr;
    }

    public static void maxHeap(int[] arr, int heapSize, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largeSize = index;
        if (left < heapSize && arr[largeSize] < arr[left]) {
            largeSize = left;
        }
        if (right < heapSize && arr[largeSize] < arr[right]) {
            largeSize = right;
        }
        if (largeSize != index) {
            int temp = arr[largeSize];
            arr[largeSize] = arr[index];
            arr[index] = temp;
            maxHeap(arr, heapSize, largeSize);
        }
    }

    public static int[] mergeSort(int[] arr) {
        if (arr == null) return arr;

        int len = arr.length;
        if (len == 1) return arr;

        int[] left = Arrays.copyOfRange(arr, 0, len / 2);
        int[] right = Arrays.copyOfRange(arr, len / 2, len);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int llen = left.length;
        int rlen = right.length;
        int[] res = new int[llen + rlen];

        int li = 0, ri = 0, si = 0;
        while (llen > li && rlen > ri) {
            if (left[li] > right[ri]) {
                res[ri++] = right[ri++];
            } else {
                res[ri++] = left[li++];
            }
        }
        while (llen > li) {
            res[ri++] = left[li++];
        }
        while (rlen > ri) {
            res[ri++] = right[ri++];
        }
        return res;
    }
}
