package datastruct.binarytree;

import algorithm.sword.Offer_20_BinaryTreeMirror;
import bean.TreeNode;

/**
 * 重建 二叉树 :
 *      输入某 二叉树 的前序遍历和中序遍历的结果，请重建出该 二叉树 （假设输入的前序遍历和中序遍历的结果中都不含重复的数字）。
 * <p>
 *      例如：
 *        前序遍历序列：{1,2,4,7,3,5,6,8} (根节点（当前节点）-左子树-右子树)
 *        中序遍历序列：{4,7,2,1,5,3,8,6} (左子树-根节点（当前节点）-右子树)
 *        则重建二叉树并返回。
 *      所求二叉树：
 *                  1
 *                /   \
 *               2     3
 *              /    /  \
 *             4    5    6
 *              \       /
 *               7     8
 *      解题思路：
 *          1.从前序遍历找到根节点
 *          2.再从中序遍历寻找该根节点索引，分左右两个区间找左右两个子树
 *
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class BinaryTreeReBuild {
    public static void main(String[] args) {
        int[] pre = {1 ,2, 4, 7, 3, 5, 6, 8};
        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode<Integer> node = rebuildBinaryTree(pre, mid);
        System.out.println(node);
        System.out.println(Offer_20_BinaryTreeMirror.binaryTreeMirror2(node));
        /*
            TreeNode{
                data=1,
                left=TreeNode{
                    data=2,
                    left=TreeNode{
                        data=4,
                        left=null,
                        right=TreeNode{
                            data=7,
                            left=null,
                            right=null
                        }
                    },
                    right=null
                },
                right=TreeNode{
                    data=3,
                    left=TreeNode{
                        data=5,
                        left=null,
                        right=null
                    },
                    right=TreeNode{
                        data=6,
                        left=TreeNode{
                            data=8,
                            left=null,
                            right=null
                        },
                        right=null
                    }
                }
            }
         */
    }

    public static TreeNode<Integer> rebuildBinaryTree(int[] pre, int[] mid) {
        if (pre == null || mid == null) return null;
        return rebuildBinaryTree(pre, 0, pre.length - 1, mid, 0, mid.length - 1);
    }

    public static TreeNode<Integer> rebuildBinaryTree(
            int[] pre, int startPre, int endPre,
            int[] mid, int startMid, int endMid) {

        if (startPre > endPre || startMid > endMid) return null;

        // 前序遍历的首个元素即为 二叉树 的根节点
        TreeNode<Integer> node = new TreeNode<>(pre[startPre]);
        // 左子树长度
        int len = 0;
        int i = startMid;
        for (; i < endMid; i++) {
            if (mid[i] == pre[startPre]) {
                break;
            }
            len++;
        }

        /*
            递归调用求解左右子树：
                左子树：前序遍历为根后一个到根加左子树长度，中序遍历为第一个到根节点位置前一个
                右子树：前序遍历为左子树后一个到最后一个，中序为根节点后一个到最后一个
         */
        node.left = rebuildBinaryTree(pre, startPre + 1, startPre + len, mid, startMid, i - 1);
        node.right = rebuildBinaryTree(pre, startPre + 1 + len, endPre, mid, i + 1, endMid);

        return node;
    }
}

