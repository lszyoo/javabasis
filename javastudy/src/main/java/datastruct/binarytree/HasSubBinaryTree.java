package datastruct.binarytree;

import bean.TreeNode;

/**
 * 树的子结构：
 *    输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *    B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * 参考链接：https://blog.csdn.net/qq_40840749/article/details/120176016
 *
 * @Writer ArtisanLS
 * @Date 2023/2/22
 */
public class HasSubBinaryTree {
    /**
     * 先序遍历二叉树 root1，即体现在 hasSubBinaryTree 的 return 写法上，
     * 先用二叉树 root1 的根节点和二叉树 root2 作对比，调用 subBinaryTree 方法，
     * 再用 root1.left 和 root2 对比，
     * 最后用 root1.right 和 root2 对比，即是一个"根-左-右"的先序遍历顺序。
     */
    public static <T> boolean hasSubBinaryTree(TreeNode<T> root1, TreeNode<T> root2) {
        if (root1 == null || root2 == null) return false;
        return subBinaryTree(root1, root2) || hasSubBinaryTree(root1.left, root2) || hasSubBinaryTree(root1.right, root2);
    }

    /**
     * 对比是否是子树的方法 subBinaryTree 方法实现思路：
     *     ① 递归结束条件：
     *           如果 root2==null，说明如果 root2 已经完成匹配，返回 true
     *           如果 root1==null，说明 root1 越过叶子结点还没匹配 root2，返回 false
     *           如果当前 root1.val != root2.val，匹配失败，返回 false
     *     ② 当前值匹配成功的话，就递归看他们左子树和右子树是否匹配
     */
    public static <T> boolean subBinaryTree(TreeNode<T> root1, TreeNode<T> root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.data != root2.data) return false;

        return subBinaryTree(root1.left, root2.left) && subBinaryTree(root1.right, root2.right);
    }
}
