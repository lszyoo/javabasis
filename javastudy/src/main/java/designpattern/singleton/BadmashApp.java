package designpattern.singleton;

/**
 * 单例模式：保证类在内存中只有一个对象。
 *
 * 如何保证类在内存中只有一个对象呢?
 * 		A：把构造方法私有
 * 		B：在成员位置自己创建一个对象
 * 		C：通过一个公共的方法提供访问
 *
 * 分类：
 *      饿汉式：Badmash
 *      懒汉式：Idler
 *
 * @Writer ArtisanLS
 * @Date 2023/8/25
 */
public class BadmashApp {
    public static void main(String[] args) {
        // Student s1 = new Student();
        // Student s2 = new Student();
        // System.out.println(s1 == s2); // false，则创建了两个对象

        // 通过单例如何得到对象呢?

        // Student.stu = null;
        // Student s1 = Student.getStudent();
        // Student s2 = Student.getStudent();
        // System.out.println(s1 == s2); // true

        // System.out.println(s1); // null
        // System.out.println(s2); // null

        Badmash bm1 = Badmash.getBadmash();
        Badmash bm2 = Badmash.getBadmash();
        System.out.println(bm1 == bm2); // true
        System.out.println(bm1); // designpattern.singleton.Badmash@5a39699c
        System.out.println(bm2); // designpattern.singleton.Badmash@5a39699c
    }
}
