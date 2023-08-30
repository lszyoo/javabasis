package designpattern.singleton;

/**
 * @Writer ArtisanLS
 * @Date 2023/8/25
 */
public class Idler {
    private Idler() {

    }

    private static Idler idler = null;

    public synchronized static Idler getIdler() {
        // t1,t2,t3三个线程同时进来可能会造成多次创建对象
        if (idler == null) {
            // t1,t2,t3
            idler = new Idler();
        }
        return idler;
    }
}
