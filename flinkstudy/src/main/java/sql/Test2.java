package sql;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.Duration;

/**
 * @Writer ArtisanLS
 * @Date 2023/3/30
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        // create environments of both APIs
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setRuntimeMode(RuntimeExecutionMode.BATCH);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

// create a DataStream
        DataStream<Row> dataStream = env.fromElements(
                Row.of("Alice", 12),
                Row.of("Bob", 10),
                Row.of("Alice", 100));

// interpret the insert-only DataStream as a Table
        Table inputTable = tableEnv.fromDataStream(dataStream).as("name", "score");

// register the Table object as a view and query it
// the query contains an aggregation that produces updates
        tableEnv.createTemporaryView("InputTable", inputTable);
        Table resultTable = tableEnv.sqlQuery(
                "SELECT name, SUM(score) FROM InputTable GROUP BY name");

// interpret the updating Table as a changelog DataStream
        DataStream<Row> resultStream = tableEnv.toChangelogStream(resultTable);

// add a printing sink and execute in DataStream API
        resultStream.print();
        env.execute();
//        Duration
    }
}
//Duration
//
//        CREATE TABLE meta_test (
//             age INT,
//             name STRING,
//             price int,
//             cnt int,
//        proctime AS PROCTIME(),
//              `cost` AS price * cnt,
//             `file.path` STRING NOT NULL METADATA,
//             `file.size` BIGINT NOT NULL metadata
//         ) WITH (
//                 'connector' = 'filesystem',
//                 'path' = 'file:////Users/liushuai/Downloads/test-flink-file',
//                 'format' = 'json',
//                 'source.monitor-interval' = '10s'
//                 );
//        select name, age, image from meta_test cross join unnest(productImages) as t (image);
//        select name, res from meta_test, lateral table(left(age, 4)) as t (res);
//        select * from (
//        select name, age, row_number() over(partition by name order by age desc) as rn from meta_test) where rn = 1;
//
//
//        {"age": 12,"name": "xiaoming","price":15,"cnt":3}
//        {"age": 18,"name": "xiaomhong","price":15,"cnt":3}