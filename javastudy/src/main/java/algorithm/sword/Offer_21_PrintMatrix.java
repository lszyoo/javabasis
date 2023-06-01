package algorithm.sword;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺时针打印矩阵
 *    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如：
 *    1     2    3    4
 *    5     6    7    8
 *    9     10   11   12
 *    13    14   15   16
 *    输出：
 *       1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 * <p>
 *    分析：如果以矩阵左上角为（0，0），则每一圈开始的点是（0，0）、（1，1）...
 * </p>
 *
 *
 * @Writer ArtisanLS
 * @Date 2023/2/14
 */
public class Offer_21_PrintMatrix {
    public static void main(String[] args) {
        int[][] arr1 = new int[][]{{1, 2, 3}, {2, 4, 8}, {3, 5, 9}, {1, 2, 3}, {6, 7, 8}};
        System.out.println(printMatrix(arr1));

        int[][] arr2 = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        System.out.println(printMatrix(arr2));
    }

    public static List<Integer> printMatrix(int[][] arr) {
        if (arr == null || arr.length == 0) return null;

        List<Integer> list = new ArrayList<>();
        int m = arr.length; // 行
        int n = arr[0].length; // 列
        int randMax = Math.min(m, n);
        if (randMax % 2 != 0) {
            randMax = randMax + 1;
        }

        // 对角始点: (rand - 1, rand - 1), 对角尾点: (m - 1, n - 1), 每遍历一圈, rand + 1, m - 1, n - 1
        for (int rand = 1; rand <= randMax / 2; rand++) {
            if (m == rand) { // 单行，这是单行条件
                for (int i = rand - 1; i < n; i++) list.add(arr[rand - 1][i]);
            } else if (n == rand) { // 单列，这是单列条件
                for (int i = rand - 1; i < m; i++) list.add(arr[i][rand - 1]);
            } else { // 有一圈数据时，单行/列不是这种情况的特例，否则会重复打印
                for (int i = rand - 1; i < n - 1; i++) list.add(arr[rand - 1][i]);
                for (int i = rand - 1; i < m - 1; i++) list.add(arr[i][n - 1]);
                for (int i = n - 1; i > rand - 1; i--) list.add(arr[m - 1][i]);
                for (int i = m - 1; i > rand - 1; i--) list.add(arr[i][rand - 1]);
            }
            m--;
            n--;
        }
        return list;
    }
}
