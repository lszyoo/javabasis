package state;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Writer ArtisanLS
 * @Date 2023/2/10
 */

public class CountWindowAverage {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.fromElements(Tuple2.of(1L, 3L), Tuple2.of(1L, 5L), Tuple2.of(1L, 7L), Tuple2.of(1L, 4L), Tuple2.of(1L, 2L), Tuple2.of(1L, 11L))
                .keyBy(x -> x.f0)
                .flatMap(new CountFlatMapFunction())
                .print();

        env.execute();
    }

    static class CountFlatMapFunction extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Long>> {
        private transient ValueState<Tuple2<Long, Long>> sum;

        @Override
        public void flatMap(Tuple2<Long, Long> input, Collector<Tuple2<Long, Long>> out) throws Exception {
            Tuple2<Long, Long> currentSum = sum.value();
            if (currentSum == null) {
                currentSum = Tuple2.of(0L, 0L);
            }
            currentSum.f0 += 1;
            currentSum.f1 += input.f1;
            sum.update(currentSum);
            // 当累积个数超过 2 个的时候，进行求和，并将结果发送给下游
            if (currentSum.f0 >= 2) {
                out.collect(new Tuple2<>(input.f0, currentSum.f1 / currentSum.f0));
                sum.clear();
            }
        }

        @Override
        public void open(Configuration config) {
            ValueStateDescriptor<Tuple2<Long, Long>> descriptor = new ValueStateDescriptor<>(
                    "average", // State 名字，在获取 State 的时候使用
                    TypeInformation.of(new TypeHint<>() {})); // State 中数据类型描述，用来做序列化和反序列化
            sum = getRuntimeContext().getState(descriptor);
        }
    }
}

