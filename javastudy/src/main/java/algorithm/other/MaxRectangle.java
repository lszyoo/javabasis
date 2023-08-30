package algorithm.other;

import java.util.Arrays;

/**
 * @Writer ArtisanLS
 * @Date 2023/8/29
 */
public class MaxRectangle {
    public static void main(String[] args) {
//        System.out.println(maxRectangle());
    }

    public static int maxRectangle(int[] arr) {
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 2; i--) {
            if (arr[i] < arr[i - 1] + arr[i - 2]) {
                return arr[i] + arr[i - 1] + arr[i - 2];
            }
        }
        return 0;
    }
}
