package window;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;

/**
 * @Writer ArtisanLS
 * @Date 2023/2/1
 */
public class Window2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 已废弃，默认使用事件时间
        // env.setStreamTimeCharacteristic();

        // 设置周期性发送 watermark 时间间隔，单位 ms
        // 设置为 0，将禁用周期性发送 watermark
        env.getConfig().setAutoWatermarkInterval(100);

        DataStreamSource<String> inputStream = env.socketTextStream("localhost", 10001);

        SerializableTimestampAssigner<String> timestampAssigner = new SerializableTimestampAssigner<>() {
            @Override
            public long extractTimestamp(String element, long recordTimestamp) {
                String[] fields = element.split(" ");
                Long timestamp = Long.valueOf(fields[0]);
                return timestamp * 1000L;
            }
        };

        inputStream
                .assignTimestampsAndWatermarks(
//                        new BoundedOutOfOrdernessTimestampExtractor<String>(Time.seconds(3)) {
//                                                   @Override
//                                                   public long extractTimestamp(String element) {
//                                                       return 0;
//                                                   }
//                                               }
                        WatermarkStrategy
                                .<String>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                                .withTimestampAssigner(timestampAssigner)
                )
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String value) throws Exception {
                        return Tuple2.of(value.split(" ")[1], 1L);
                    }
                })
                .keyBy(new KeySelector<Tuple2<String, Long>, String>() {
                    @Override
                    public String getKey(Tuple2<String, Long> value) throws Exception {
                        return value.f0;
                    }
                })
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
//                .window(TumblingEventTimeWindows.of(Time.seconds(10), Time.seconds(2)))
                .process(new ProcessWindowFunction<Tuple2<String, Long>, Tuple4<String, Long, Long, Long>, String, TimeWindow>() {
                    @Override
                    public void process(String key, ProcessWindowFunction<Tuple2<String, Long>, Tuple4<String, Long, Long, Long>, String, TimeWindow>.Context context, Iterable<Tuple2<String, Long>> elements, Collector<Tuple4<String, Long, Long, Long>> out) throws Exception {
                        Long ct = 0L;
                        for (Tuple2<String, Long> element : elements) {
                            ct += element.f1;
                        }
                        System.out.println(context.currentWatermark());
                        out.collect(Tuple4.of(key, ct, context.window().getStart(), context.window().getEnd()));
                    }
                })
//                .reduce(new ReduceFunction<Tuple2<String, Long>>() {
//                    @Override
//                    public Tuple2<String, Long> reduce(Tuple2<String, Long> value1, Tuple2<String, Long> value2) throws Exception {
//                        return new Tuple2<>(value1.f0, value1.f1 + value2.f1);
//                    }
//                })
                .print();

        env.execute("Test Watermark");
    }
}

// 2> (java,1,1630312522000,1630312532000)
// 2> (java,5,1630312532000,1630312542000)

// 2> (java,5,1630312530000,1630312540000)
// 2> (java,4,1630312540000,1630312550000)