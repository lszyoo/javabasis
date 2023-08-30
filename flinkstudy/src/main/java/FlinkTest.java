import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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

        Java3y java3y = new Java3y();
        Consumer<String> consumer = java3y::myName;
        consumer.accept("=============");

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

class Java3y {
    // 静态方法
    public static void MyNameStatic(String name) {
        System.out.println(name);
    }

    // 实例方法
    public void myName(String name) {
        System.out.println(name);
    }

    // 无参构造方法
    public Java3y() {
    }
}
