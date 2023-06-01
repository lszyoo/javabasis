package algorithm.sword;

import bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的镜像
 *    输入：
 *             1
 *          2     3
 *        4   5 6   7
 *    输出：
 *             1
 *          3     2
 *        7   6 5   4
 * 参考链接：https://blog.csdn.net/qq_36929361/article/details/104375451
 *
 * @Writer ArtisanLS
 * @Date 2023/2/18
 */
public class Offer_20_BinaryTreeMirror {
    // 递归
    public static TreeNode<Integer> binaryTreeMirror1(TreeNode<Integer> root) {
        if (root == null) return null;

        TreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;

        binaryTreeMirror1(root.left);
        binaryTreeMirror1(root.right);

        return root;
    }

    // 循环
    public static TreeNode<Integer> binaryTreeMirror2(TreeNode<Integer> root) {
        if (root == null) return null;

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.poll(); // current = root 容器里的元素指向原对象？
            TreeNode<Integer> temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return root;
    }
}
