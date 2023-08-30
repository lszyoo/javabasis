//package state;
//
//import bean.SensorRecord;
//import env.Env;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.common.state.ValueState;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//
//import java.time.LocalDateTime;
//
///**
// * @Writer ArtisanLS
// * @Date 2023/4/12
// */
//public class Value {
//    public static void main(String[] args) {
//        StreamExecutionEnvironment env = Env.builder1();
//        env.setParallelism(1);
//        DataStreamSource<String> source = env.socketTextStream("localhost", 10001);
//        SingleOutputStreamOperator<SensorRecord> dataStream = source
//                .map(new MapFunction<String, SensorRecord>() {
//                    @Override
//                    public SensorRecord map(String s) throws Exception {
//                        if (StringUtils.isNoneBlank(s)) {
//                            String[] split = s.split(",");
//                            if (split != null && split.length == 3) {
//                                return new SensorRecord(split[0], Double.valueOf(split[1]), LocalDateTime.parse(split[2], Formatter));
//                            }
//                        }
//                        return null;
//                    }
//                })
//    }
////    private int tempDiff;
////    private transient ValueState<Double> lastTemp;
////
////    public Value(int tempDiff) {
////        this.tempDiff = tempDiff;
////    }
//}
//
