package datastruct.binarytree;

import bean.TreeNode;

/**
 * @Writer ArtisanLS
 * @Date 2023/2/9
 */
public class BinaryTreeCharacter {
    /**
     * 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * @param root
     * @return
     */
    public <T> int getDepth(TreeNode<T> root) {
        if (root == null) return 0;

        int left = getDepth(root.left);
        int right = getDepth(root.right);

        return (left > right ? left : right) + 1;
    }

    /**
     * 判读是否是平衡二叉树（AVL）
     *
     * @param root
     * @return
     * @param <T>
     */
    public <T> boolean isBalanced(TreeNode<T> root) {
        if (root == null) {
            return true;
        }

        if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }
}
