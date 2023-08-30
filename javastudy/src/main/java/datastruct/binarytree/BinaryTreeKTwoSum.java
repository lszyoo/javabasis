package datastruct.binarytree;

import bean.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 二叉搜索树中两个节点之和
 *    给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
 * <p>
 *    eg1:
 *      输入: root = [8,6,10,5,7,9,11], k = 12
 *      输出: true
 *      解释: 节点 5 和节点 7 之和等于 12
 *    eg2:
 *      输入: root = [8,6,10,5,7,9,11], k = 22
 *      输出: false
 *      解释: 不存在两个节点值之和为 22 的节点
 *    注意：
 *      1.二叉树的节点个数的范围是  [1, 10^4].
 *      2.-10^4 <= Node.val <= 10^4
 *      3.root 为二叉搜索树
 *      4.-10^5 <= k <= 10^5
 *
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/7/14
 */
public class BinaryTreeKTwoSum {
    Set<Integer> set = new HashSet<>();
    public static void main(String[] args) {

    }

    /**
     * 思路和算法：深度优先搜索 + 哈希表
     *      我们可以使用深度优先搜索的方式遍历整棵树，用哈希表记录遍历过的节点的值。
     *      对于一个值为 x 的节点，我们检查哈希表中是否存在 k−x 即可。如果存在对应的元素，那么我们就可以在该树上找到两个节点的和为 k；
     *      否则，我们将 x 放入到哈希表中。
     *      如果遍历完整棵树都不存在对应的元素，那么该树上不存在两个和为 k 的节点。
     * 复杂度分析
     *      时间复杂度：O(n)，其中 n 为二叉搜索树的大小。我们需要遍历整棵树一次。
     *      空间复杂度：O(n)，其中 n 为二叉搜索树的大小。主要为哈希表的开销，最坏情况下我们需要将每个节点加入哈希表一次。
     * @param root
     * @param k
     * @return
     */
    public boolean binaryTreeKTwoSum(TreeNode<Integer> root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.data)) {
            return true;
        }
        set.add(root.data);
        return binaryTreeKTwoSum(root.left, k) || binaryTreeKTwoSum(root.right, k);
    }
}
