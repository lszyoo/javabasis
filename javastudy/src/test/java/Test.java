import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/5
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "v");
        list.stream().filter(e -> e.length() == 1).collect(Collectors.toList());

//        Aobing a = new Aobing();
//        a.start();
//        for (;;) {
//            if (a.isFlag()) {
//                System.out.println("有点东西"); // 不会打印
//            }
//        }
    }

    static class Aobing extends Thread {
        private volatile boolean flag = false;
        public boolean isFlag() {
            return flag;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
            System.out.println("flag = " + flag);
        }
    }
}
