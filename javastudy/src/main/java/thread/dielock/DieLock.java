package thread.dielock;

/**
 * @Writer ArtisanLS
 * @Date 2023/3/27
 */
public class DieLock extends Thread {
    private boolean flag;

    public DieLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (MyLock.obj1) {
                System.out.println("if obj1");
                synchronized (MyLock.obj2) {
                    System.out.println("if obj2");
                }
            }
        } else {
            synchronized (MyLock.obj2) {
                System.out.println("else obj2");
                synchronized (MyLock.obj1) {
                    System.out.println("else obj1");
                }
            }
        }
    }
}

class MyLock {
    // 创建两把锁对象
    public static final Object obj1 = new Object();
    public static final Object obj2 = new Object();
}
