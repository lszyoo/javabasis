package thread.dielock;

/**
 * 死锁：
 *    是指两个或者两个以上的线程在执行的过程中，因争夺资源产生的一种互相等待现象
 * 同步弊端：
 *    （1）效率低
 *    （2）如果出现了同步嵌套，就容易产生死锁问题
 *
 * @Writer ArtisanLS
 * @Date 2023/3/28
 */
public class DieLockApp {
    public static void main(String[] args) {
        DieLock dl1 = new DieLock(true);
        DieLock dl2 = new DieLock(false);

        dl1.start();
        dl2.start();
    }
}
