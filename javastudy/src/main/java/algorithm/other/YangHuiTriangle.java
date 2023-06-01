package algorithm.other;

/**
 * 小红书：杨辉三角
 *        1
 *        1 1
 *        1 2 1
 *        1 3 3 1
 *        1 4 6 4 1
 * <p>
 *   规律：
 *      1、第 n 行有 n 个数字
 *      2、每一行的开始和结尾数字都为 1
 *      3、用二维数组表示：
 *        a[i][0] = 1
 *        a[i][j] = 1 (当i==j时)
 *      4、第 n+1 行的第 i 个数字等于第 n 行的 i-1 个数字加上第 n 行的 i 个数字，用二维数组表示就是 a[i+1][j] = a[i][j-1] + a[i][j]
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/2/8
 */
public class YangHuiTriangle {
    public static void main(String[] args) {
        yangHuiTriangle(4);
        /*
            1
            1	1
            1	2	1
            1	3	3	1
         */
    }

    public static void yangHuiTriangle(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
