package watermark;

import env.Env;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;

/**
 * @Writer ArtisanLS
 * @Date 2023/7/27
 */
public class WatermarkBuiltIn {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = Env.builder1();
        DataStreamSource<String> inputStream = env.socketTextStream("localhost", 10001);

        inputStream
                .map(str -> Tuple2.of(str.split(" ")[0], 1L))
                .assignTimestampsAndWatermarks(WatermarkStrategy
                        .<Tuple2<String, Long>>forBoundedOutOfOrderness(Duration.ofSeconds(10))
                        .withTimestampAssigner((event, timestamp) -> Long.parseLong(event.f0))).print();
//        System.out.println(Long.MIN_VALUE);
//        System.out.println(Duration.ofSeconds(10).toMillis());
    }
}
