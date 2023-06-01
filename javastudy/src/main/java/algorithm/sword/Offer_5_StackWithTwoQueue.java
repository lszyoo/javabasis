package algorithm.sword;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现一个栈
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class Offer_5_StackWithTwoQueue {
    public static void main(String[] args) {
        StackWithTwoQueue<Integer> stack = new StackWithTwoQueue<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop()); // 2
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop()); // 4
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 1
    }

    private static class StackWithTwoQueue<E> {
        // Queue 是接口，故不能直接实例化
        Queue<E> queue1 = new LinkedList<>();
        Queue<E> queue2 = new LinkedList<>();

        // 进栈
        public void push(E data) {
            queue1.offer(data);
        }

        // 出栈
        // 将 queue1 的 n-1 个元素挪到 queue2，将 queue2 的元素再放回 queue1，实现了 n-1 个元素在 queue1 的倒叙插入，
        // 弹出 queue1，如此循环。
        public E pop() {
            while (queue1.size() != 1) {
                queue2.offer(queue1.poll());
            }
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            return queue1.poll();
        }
    }
}
