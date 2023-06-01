package bean;

/**
 * @Writer ArtisanLS
 * @Date 2023/3/16
 */
public class DoubleListNode<T> {
    public T data;
    public DoubleListNode<T> pre;
    public DoubleListNode<T> next;

    public DoubleListNode() {
    }

    public DoubleListNode(T data) {
        this.data = data;
    }
}
