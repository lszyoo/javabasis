import bean.ListNode;

/**
 * @Writer ArtisanLS
 * @Date 2023/7/6
 */
public class LinkedListTest {
    // 反转链表
    public ListNode<Integer> reverse(ListNode<Integer> head) {
        ListNode<Integer> reverseNode = null;
        ListNode<Integer> tempNode;
        while (head != null) {
            tempNode = head.next;
            head.next = reverseNode;
            reverseNode = head;
            head = tempNode;
        }
        return reverseNode;
    }

    // 奇偶链表
    public ListNode<Integer> oddEvenList(ListNode<Integer> head) {
        ListNode<Integer> first = head;
        ListNode<Integer> second = head.next;
        ListNode<Integer> secondTemp = second;

        while (second != null && second.next != null) {
            head.next = head.next.next;
            head = head.next;
            second.next = second.next.next;
            second = second.next;
        }
        head.next = secondTemp;
        return first;
    }
}
