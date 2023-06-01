import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.Arrays;
import java.util.List;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/5
 */
public class FlinkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        List<Integer> list = Arrays.asList(new Integer[]{1, 3, 6, 9, 8});
        DataStream<Integer> input = env.fromCollection(list);
        final OutputTag<String> outputTag = new OutputTag<>("side-output"){};
        final OutputTag<String> outputTag1 = new OutputTag<>("side-output1"){};

        SingleOutputStreamOperator<Integer> mainDataStream = input
                .process(new ProcessFunction<>() {
                    @Override
                    public void processElement(Integer value, ProcessFunction<Integer, Integer>.Context ctx, Collector<Integer> out) throws Exception {
                        // 发送数据到主要的输出
                        out.collect(value);
                        // 发送数据到旁路输出
                        ctx.output(outputTag, "sideout-" + value);
                        if (value == 9) {
                            ctx.output(outputTag, "sideout--" + value);
                        }
                        ctx.output(outputTag1, "side-output1-" + value);
                    }
                });
        mainDataStream.print();
        mainDataStream.getSideOutput(outputTag).print();
        mainDataStream.getSideOutput(outputTag1).print();

        env.execute();
    }
}
