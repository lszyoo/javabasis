package source;

import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.Iterator;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/5
 */
public class TestFlink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> input = env.addSource(new TestSource());
        final OutputTag<String> outputTag = new OutputTag<>("side-output1"){};

        SingleOutputStreamOperator<Tuple4<String, Integer, Long, Long>> mainDataStream = input
                .flatMap(new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String value, Collector<String> out) throws Exception {
                        for (String elem : value.split(" ")) {
                            out.collect(elem);
                        }
                    }
                })
//                .map(elem -> new Tuple2<>(elem, 1))
                .map(new RichMapFunction<String, Tuple2<String, Integer>>() {
//                    IntCounter counter = new IntCounter();
//                    @Override
//                    public void open(Configuration parameters) throws Exception {
//                        getRuntimeContext().addAccumulator("my_counter", this.counter);
//                    }

                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
//                        if (counter.getLocalValue() == 10) {
//                            counter.resetLocal();
//                        }
//                        counter.add(1);
//                        System.out.println(counter.getLocalValue());
                        return new Tuple2<>(value, 1);
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(tuple -> tuple.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .process(new ProcessWindowFunction<Tuple2<String, Integer>, Tuple4<String, Integer, Long, Long>, String, TimeWindow>() {
                    IntCounter counter = new IntCounter();
                    @Override
                    public void open(Configuration parameters) throws Exception {
                        getRuntimeContext().addAccumulator("my_counter", this.counter);
                    }

                    @Override
                    public void process(String s, ProcessWindowFunction<Tuple2<String, Integer>, Tuple4<String, Integer, Long, Long>, String, TimeWindow>.Context context, Iterable<Tuple2<String, Integer>> elements, Collector<Tuple4<String, Integer, Long, Long>> out) throws Exception {
                        Iterator<Tuple2<String, Integer>> iterator = elements.iterator();
                        int ct = 0;
                        while (iterator.hasNext()) {
                            iterator.next(); // 与hasNext方法必须配合使用，否则无法跳出循环
                            ct++;
                            counter.add(1);
                        }
                        if (counter.getLocalValue() > 20) {
                            counter.resetLocal();
                        }
                        System.out.println("========" + counter);
                        out.collect(new Tuple4<>(s, ct, context.window().getStart(), context.window().getEnd()));
                    }
                })

//                .reduce(new ReduceFunction<Tuple2<String, Integer>>() {
//                    @Override
//                    public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
//                        return new Tuple2<>(value1.f0, value1.f1 + value2.f1);
//                    }
//                }, new WindowFunction<>() {
//                    @Override
//                    public void apply(String key, TimeWindow window.window, Iterable<Tuple2<String, Integer>> input, Collector<Tuple4<String, Integer,  Long, Long>> out) throws Exception {
//                        Long start = window.window.getStart() / 1000;
//                        Long end = window.window.getEnd() / 1000;
//                        Tuple2<String, Integer> tuple2 = input.iterator().next();
//                        out.collect(new Tuple4<>(tuple2.f0, tuple2.f1, start, end));
////                        input.iterator().next();
//                    }
//                })

//                .apply(new WindowFunction<Tuple2<String, Integer>, Tuple2<String, Long>, String, TimeWindow>() {
//                    @Override
//                    public void apply(String key, TimeWindow window.window, Iterable<Tuple2<String, Integer>> input, Collector<Tuple2<String, Long>> out) throws Exception {
//                        LongCounter counter = new LongCounter();
//                        for (Tuple2<String, Integer> elem : input) {
//                            counter.add(elem.f1);
//                        }
//                        out.collect(new Tuple2<>(key, counter.getLocalValue()));
//                    }
//                })

//                .sum(1)
//                .apply(new WindowFunction<Tuple2<String, Integer>, Tuple2<String, Long>, String, TimeWindow>() {
//                    @Override
//                    public void apply(String key, TimeWindow window.window, Iterable<Tuple2<String, Integer>> input, Collector<Tuple2<String, Long>> out) throws Exception {
//                        LongCounter counter = new LongCounter();
//                        for (Tuple2<String, Integer> elem : input) {
//                            counter.add(elem.f1);
//                        }
//                        out.collect(new Tuple2<>(key, counter.getLocalValue()));
//                    }
//                })
//                .setParallelism(4)
//                .process(new ProcessFunction<Tuple2<String, Integer>, Tuple2<String, Integer>>() {
//                    @Override
//                    public void processElement(Tuple2<String, Integer> value, ProcessFunction<Tuple2<String, Integer>, Tuple2<String, Integer>>.Context ctx, Collector<Tuple2<String, Integer>> out) throws Exception {
//                        if (value.f1.equals(10)) {
//                            System.out.println(System.currentTimeMillis() + "阈值到了");
//                        }
//                        out.collect(value);
//                        ctx.output(outputTag, "side-" + value);
//                    }
//                })
                ;
        mainDataStream.print();
//        mainDataStream.getSideOutput(outputTag).print();

        env.execute();
    }
}
