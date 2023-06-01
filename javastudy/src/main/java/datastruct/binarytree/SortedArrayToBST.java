package datastruct.binarytree;

import bean.TreeNode;

/**
 * 有序数组生成二叉搜索树
 *
 * @Writer ArtisanLS
 * @Date 2023/5/24
 */
public class SortedArrayToBST {
    public TreeNode<Integer> sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode<Integer> sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;
        TreeNode<Integer> root = new TreeNode<>(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);

        return root;
    }
}
