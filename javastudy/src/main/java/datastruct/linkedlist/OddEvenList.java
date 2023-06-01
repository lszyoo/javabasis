package datastruct.linkedlist;

import bean.ListNode;

/**
 * 奇偶链表：
 *    LeetCode 328
 *    给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *    请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *    示例 1:
 *          输入: 1->2->3->4->5->NULL
 *          输出: 1->3->5->2->4->NULL
 *    示例 2:
 *          输入: 2->1->3->5->6->4->7->NULL
 *          输出: 2->3->6->7->1->5->4->NULL
 *    说明:
 *          应当保持奇数节点和偶数节点的相对顺序。
 *          链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 *    解题思路：
 *          首先我们将第一个奇数节点（也就是头节点）的地址存起来，然后将所有奇数节点按照原有次序链接起来。
 *          其次把第一个偶数节点（第二个节点）的地址存起来，然后将所有的偶数节点按照原有次序链接起来。
 *          最后将偶数的第一个节点（此时应是被保存起来的偶数节点），链接在奇数的最后一个节点后面。然后返回被保存的头节点。
 * </p>
 * @Writer ArtisanLS
 * @Date 2022/12/16
 */
public class OddEvenList {
    public static void main(String[] args) {
        ListNode<Integer> head = UtilsInstance.create();

        head.print(head);              // 1->2->3->4->5->6->7->null
        head.print(oddEvenList(head)); // 1->3->5->7->2->4->6->null
    }

    public static ListNode<Integer> oddEvenList(ListNode<Integer> head) {
        if (head == null) return null;

        ListNode<Integer> first = head; // 保存头节点，间接引用，指针
        ListNode<Integer> second = head.next;
        ListNode<Integer> secondTemp = second; // 保存第一个偶数节点
        while (second != null && second.next != null) {
            // 依次将奇数节点链接起来
            head.next = head.next.next;
            head = head.next;
            // 依次将偶数节点链接起来
            second.next = second.next.next;
            second = second.next;
        }
        head.next = secondTemp; // 将第一个偶数节点链接到最后一个奇数节点的后面

        return first;
    }
}
