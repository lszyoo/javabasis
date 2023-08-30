package watermark;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import utils.KafkaUtil;

import java.util.Locale;

/**
 * 不设置水位线：不触发计算
 * 设置水位线：最终生成的数据不够生成新的水位线，导致最终数据无法触发计算（断流导致不能生成水位线）
 *
 * @Writer ArtisanLS
 * @Date 2023/7/31
 */
public class WaterMarkInOrder {
    public static void main(String[] args) throws Exception {
        // 生产 kafka 数据：模拟弹幕【用户名、消息、时间戳】
        new Thread(() -> {
            // 用户名
            String uname = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT);
            for (int i = 100; i < 200; i++) {
                String msg = uname + " " + i + " " + System.currentTimeMillis();
                KafkaUtil.sendMsg("flink-watermark", msg);
//                System.out.println(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        // 运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // 读取数据源
        DataStreamSource<String> source = env.fromSource(KafkaUtil.getKafkaSource("flink-watermark", "watermark"), WatermarkStrategy.noWatermarks(), "kafka source");
        // 转换数据
        source
                .map(line -> {
                    String[] elems = line.split(" ");
                    return Tuple3.of(elems[0], elems[1], Long.valueOf(elems[2]));
                }, Types.TUPLE(Types.STRING, Types.STRING, Types.LONG))
                .assignTimestampsAndWatermarks(WatermarkStrategy.<Tuple3<String, String, Long>>forMonotonousTimestamps()  // 不添加 watermark 不触发计算
                        .withTimestampAssigner((tuple3, ts) -> tuple3.f2)
                )
                .keyBy(tuple3 -> tuple3.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
//                .reduce((t1, t2) -> {
//                    t1.f1 = t1.f1 + "-" + t2.f1;
//                    return t1;
//                })
                .apply(new WindowFunction<Tuple3<String, String, Long>, String, String, TimeWindow>() {
                    @Override
                    public void apply(String key, TimeWindow window, Iterable<Tuple3<String, String, Long>> input, Collector<String> out) throws Exception {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("key");
                        for (Tuple3<String, String, Long> tuple3 : input) {
                            buffer.append("[" + tuple3.f1 + "_" + tuple3.f2 + "]");
                        }
                        buffer.append("[" + window + "]");
                        out.collect(buffer.toString());
                    }
                })
                .print();
        // 运行环境
        env.execute();
    }


}
