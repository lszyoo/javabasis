package datastruct.binarytree;

import bean.TreeNode;

import java.util.*;

/**
 * 遍历：
 *     前序：根左右
 *     中序：左根右
 *     后序：左右根
 *     层序：一层一层去遍历
 * 参考链接：
 *     https://blog.csdn.net/qswdaw/article/details/124078136
 *     https://blog.csdn.net/abc123mma/article/details/128192898
 *
 * @Writer ArtisanLS
 * @Date 2022/12/27
 */
public class BinaryTreeTraversal {
    /**
     * 递归
     */
    // 前序遍历
    public <T> void preOrder1(TreeNode<T> root) {
        if (root == null) return;

        System.out.print(root.data + " "); // 打印根节点的值
        preOrder1(root.left); // 递归去访问左子树
        preOrder1(root.right); // 递归去访问右子树
    }

    // 中序遍历
    public <T> void midOrder1(TreeNode<T> root) {
        if (root == null) return;

        midOrder1(root.left); // 递归去访问左子树
        System.out.print(root.data + " "); // 打印根节点的值
        midOrder1(root.right); // 递归去访问右子树
    }

    // 后序遍历
    public <T> void AfterOrder1(TreeNode<T> root) {
        if (root == null) return;

        AfterOrder1(root.left); // 递归去访问左子树
        AfterOrder1(root.right); // 递归去访问右子树
        System.out.print(root.data + " "); // 打印根节点的值
    }

    /**
     * 迭代
     */
    // 前序遍历
    public <T> List<T> preOrder2(TreeNode<T> root) {
        if (root == null) return null;

        List<T> list = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        // 迭代访问节点的左孩子，并入栈
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.data);
                stack.push(root);
                root = root.left;
            }
            // 如果节点没有左孩子，则弹出栈顶节点，访问节点的右孩子
            root = stack.pop();
            root = root.right;
        }
        return list;
    }

    // 中序遍历
    public <T> List<T> midOrder2(TreeNode<T> root) {
        if (root == null) return null;

        List<T> list = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 如果节点没有左孩子，则弹出栈顶节点，并将弹出的的节点加入到list中,访问节点的右孩子
            root = stack.pop();
            list.add(root.data);
            root = root.right;
        }
        return list;
    }

    // 后序遍历
    public <T> List<T> AfterOrder2(TreeNode<T> root) {
        if (root == null) return null;

        TreeNode<T> preNode = null;
        List<T> list = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == preNode) {
                list.add(root.data);
                preNode = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * 层序遍历：
     *          1
     *        2    3
     *      4  5  6  7
     * 我们需要思考假如我们有节点1，可以通过其左右子节点获得节点2和节点3，但是我们要保证节点2要比节点3先遍历。因此我们需要借助队列：
     * 1.首先，我们使用队列，将节点1装入其中。此时队列长度为1。
     * 2.然后循环遍历一次，将其左右子节点装入队列，此时队列长度为2。
     * 3.因此我们可以循环遍历2次，将左节点2的子节点装入队列，然后将右节点3的子节点装入队列，此时队列长度为4。
     * 4.直到队列为空为止。
     */
    public <T> List<List<T>> levelOrder(TreeNode<T> root) {
        if (root == null) return null;

        List<List<T>> result = new ArrayList<>();
        Deque<TreeNode<T>> deque = new ArrayDeque<>();
        deque.push(root); // 队列的前面插入指定的元素
        while (!deque.isEmpty()) {
            int size = deque.size(); // 不能放到for循环条件里，循环中会在deque里添加元素，导致size变化
            List<T> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = deque.pop();
                level.add(node.data);
                if (node.left != null) deque.add(node.left); // 队列的末尾插入指定的元素
                if (node.right != null) deque.add(node.right);
            }
            result.add(level);
        }
        return result;
    }
}
