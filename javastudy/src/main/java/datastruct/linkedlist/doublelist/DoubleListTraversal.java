package datastruct.linkedlist.doublelist;

import bean.DoubleListNode;

/**
 * 双向链表遍历
 *
 * @Writer ArtisanLS
 * @Date 2023/6/12
 */
public class DoubleListTraversal {
    public void doubleListTraversal(DoubleListNode<Integer> node) {
        DoubleListNode<Integer> temp = node;
        while (node != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
