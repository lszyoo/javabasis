package algorithm.sword;

/**
 * 二维数组中的查找：
 *      在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *      请完成一个函数，输入这样的一个 二维数组 和 一个整数，判断数组中是否含有该整数。
 * <p>
 *     注意：用 while 循环来进行数组的行列增删，而不是新建数组，拉低效率且浪费空间
 *     思路：从右上角开始进行判断，等于则返回，小于最右则删除该列，大于最右则删除该行
 *          1 2 3 9
 *          2 4 8 12
 *          3 5 9 19
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/1/4
 */
public class Offer_1_TwoDimensionArraySearch {
    public static void main(String[] args) {
        // 定义二维数组
        int[][] arr = new int[][]{{1, 2, 3, 9}, {2, 4, 8, 12}, {3, 5, 9, 19}};

        System.out.println(twoDimensionArraySearch(arr, 5)); // true
        System.out.println(arr.length); // 3行
        System.out.println(arr[0].length); // 4列
    }

    public static boolean twoDimensionArraySearch(int[][] arr, int target) {
        if (arr == null) {
            return false;
        }

        int row = 0;
        int col = arr[0].length - 1;
        while (row < arr.length && col >= 0) {
            if (arr[row][col] == target) {
                System.out.println("坐标：(" + row + ", " + col + ")"); // 坐标：(2, 1)
                return true;
            } else if (arr[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
