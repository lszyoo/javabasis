package algorithm.other;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * @Writer ArtisanLS
 * @Date 2023/2/23
 */
public class RedPackage {
    public static void main(String[] args) {
        battleRedPackage(20, 10);
    }

    public static void battleRedPackage(double moneyTotal, int num) {
        double min = 0.01; // 最小红包金额
        Random random = new Random();
        NumberFormat formatter = new DecimalFormat("#.##");
        for (int i = 1; i < num; i++) {
            double safeTotal = (moneyTotal - (num - i) * min) / (num - i);
            double moneyRan = random.nextDouble() * (safeTotal - min) + min;
            moneyRan = Double.valueOf(formatter.format(moneyRan));
            moneyTotal = Double.valueOf(formatter.format(moneyTotal - moneyRan));

            System.out.println("第 " + i + " 个红包：" + moneyRan + "元，余额：" + moneyTotal + "元");
        }
        System.out.println("最后一个红包：" + moneyTotal + "元");
    }
}
