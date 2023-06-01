package sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 *
 * @Writer ArtisanLS
 * @Date 2023/3/29
 */
public class TableEnv {
    public static void main(String[] args) {
        // 方式一
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();
        TableEnvironment tEnv1 = TableEnvironment.create(settings);

        // 方式二
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tEnv2 = StreamTableEnvironment.create(env);
    }

    public static TableEnvironment tEnv(String mode) {
        EnvironmentSettings.Builder builder = EnvironmentSettings.newInstance();
        if (mode == "stream") {
            builder = builder.inStreamingMode();
        } else {
            builder = builder.inBatchMode();
        }
        EnvironmentSettings settings = builder.build();
        TableEnvironment tEnv = TableEnvironment.create(settings);

        return tEnv;
    }

    public static StreamTableEnvironment tEnt() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

        return tEnv;
    }
}
