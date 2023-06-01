package notice;

/**
 * 对象赋值：
 *    操作的是对对象的引用。所以倘若“将一个对象赋值给另一个对象“的时候，实际上是将“引用”从一个地方复制到另一个地方。
 *    这意味着假若对对象使用c=d，那么c和d都指向原本d指向的那个对象。
 *
 * @Writer ArtisanLS
 * @Date 2023/2/6
 */
public class ObjectEqual {
    public static void main(String[] args) {
        Equals equal1 = new Equals(1);
        Equals equal2 = new Equals(2);
        Equals equal3 = new Equals(5);
        System.out.println ("equal1 = " + equal1.level + ", equal2 = " + equal2.level); // equal1 = 1, equal2 = 2

        equal1 = equal2;
        System.out.println ("equal1 = " + equal1.level + ", equal2 = " + equal2.level); // equal1 = 2, equal2 = 2

        equal1.level = 3;
        System.out.println ("equal1 = " + equal1.level + ", equal2 = " + equal2.level); // equal1 = 3, equal2 = 3

        equal2.level = 4;
        System.out.println ("equal1 = " + equal1.level + ", equal2 = " + equal2.level); // equal1 = 4, equal2 = 4

        equal1 = equal3;
        System.out.println ("equal1 = " + equal1.level + ", equal2 = " + equal2.level); // equal1 = 5, equal2 = 4
    }

    static Equals test(Equals eq) {
        if (eq.level == 3) return new Equals(8);
        else return eq;
    }

    static class Equals {
        Integer level;

        public Equals(Integer level) {
            this.level = level;
        }
    }
}
