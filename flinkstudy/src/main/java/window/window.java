package window;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.evictors.CountEvictor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import source.MemorySource;

/**
 * 1. 泛型source：泛型子类继承泛型父类，flink无法判断子类泛型
 * 2. 自定义source必须指定并行度为1，否则每个并行度都会执行source，导致数据增加
 *
 * @Writer ArtisanLS
 * @Date 2023/1/30
 */
public class window {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setStreamTimeCharacteristic();

        env.addSource(new MemorySource<String>())
                .returns(Types.STRING).setParallelism(1)
                .map(new MapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
                        String[] arr = value.split(",");
                        return new Tuple2<>(arr[0], Integer.valueOf(arr[1]));
                    }
                })
//                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .trigger(new Trigger<Tuple2<String, Integer>, TimeWindow>() {
                    private ValueStateDescriptor<Integer> keyState = new ValueStateDescriptor<>("current_key_state", Integer.class);

                    @Override
                    public TriggerResult onElement(Tuple2<String, Integer> element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
                        System.out.println(timestamp);

                        ValueState<Integer> valueState = ctx.getPartitionedState(keyState);
                        if (valueState.value() == null)
                            valueState.update(1);
                        int ct = valueState.value();
                        if (ct == 3) {
                            valueState.update(1);
                            return TriggerResult.FIRE;
                        }
                        ct++;
                        valueState.update(ct);
                        return TriggerResult.CONTINUE;
                    }

                    @Override
                    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
                        return TriggerResult.FIRE;
                    }

                    @Override
                    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
                        return TriggerResult.CONTINUE;
                    }

                    @Override
                    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {
                        ctx.getPartitionedState(keyState).clear();
                    }
                })
                .evictor(CountEvictor.of(3))
                .sum(1)
                .print();

        env.execute();
    }
}
