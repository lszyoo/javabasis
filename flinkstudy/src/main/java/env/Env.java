package env;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Writer ArtisanLS
 * @Date 2023/1/6
 */
public class Env {
    public static void main(String[] args) {
        // getExecutionEnvironment() 会根据上下文自动识别 本地 或 集群 环境
        StreamExecutionEnvironment env1 = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置从指定的 checkpoint 启动程序
        Configuration conf = new Configuration();
        conf.setString("execution.savepoint.path", "file:///Users/liushuai/Desktop/flink-study/official/checkpoint/d4e9dcc37e114f3878796632d333fa7e/chk-21");
        StreamExecutionEnvironment env2 = StreamExecutionEnvironment.getExecutionEnvironment(conf);

//        ConfigOptions configOptions = ConfigOptions.OptionBuilder("");
//        conf.getString(conf, "");

        ExecutionConfig config = new ExecutionConfig();
        config.setAutoWatermarkInterval(100);
        config.getAutoWatermarkInterval();

        /**
         * 设置执行模式
         * 命令行: bin/flink run -Dexecution.runtime-mode=BATCH examples/streaming/WordCount.jar
         */
        env1.setRuntimeMode(RuntimeExecutionMode.STREAMING); // DataStream 执行模式（默认)
        env1.setRuntimeMode(RuntimeExecutionMode.BATCH); // 在 DataStream API 上进行批量式执行
        env1.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC); // 让系统根据数据源的边界性来决定
    }

    public static StreamExecutionEnvironment builder1() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        return env;
    }
}
