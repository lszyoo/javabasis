package datastruct.linkedlist;

import bean.ListNode;

/**
 * 两数相加：
 *    给定两个非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *    可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 *     7->2->4->3
 *        5->6->4
 *   -------------
 *     7->8->0->7
 * </p>
 * 由于是单向链表，当低位存在进位时，我们没办法返回到之前的节点进行进位操作。
 * 所以这道题，我们需要先反转链表后，在进行加法操作，最后在将加和的结果反转后输出。
 * 参考链接：https://www.jianshu.com/p/6782d5f80832
 *
 * @Writer ArtisanLS
 * @Date 2023/4/25
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode<Integer> node1 = UtilsInstance.create();
        ListNode<Integer> node2 = UtilsInstance.create();
        ListNode.print(node1); // 1->2->3->4->5->6->7->null
        ListNode.print(node2); // 1->2->3->4->5->6->7->null
        ListNode.print(addTwoNumbers(node1, node2)); // 2->4->6->9->1->3->4->null
    }

    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> node1, ListNode<Integer> node2) {
        ListNode<Integer> nodeTemp1 = ReverseList.reverseList2(node1);
        ListNode<Integer> nodeTemp2 = ReverseList.reverseList2(node2);
        int count = 0; // 进位
        ListNode<Integer> result = new ListNode<>(0);
        ListNode<Integer> resultTemp = result;
        while (nodeTemp1 != null || nodeTemp2 != null) {
            int num = 0;
            if (nodeTemp1 != null) {
                num += nodeTemp1.data;
                nodeTemp1 = nodeTemp1.next;
            }
            if (nodeTemp2 != null) {
                num += nodeTemp2.data;
                nodeTemp2 = nodeTemp2.next;
            }
            num += count;
            count = (num >= 10 ? 1 : 0);
            resultTemp.next = new ListNode(num - count * 10);
            resultTemp = resultTemp.next;
        }
        return ReverseList.reverseList2(result.next);
    }
}
