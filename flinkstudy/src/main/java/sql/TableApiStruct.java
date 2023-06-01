package sql;

import org.apache.flink.connector.datagen.table.DataGenConnectorOptions;
import org.apache.flink.table.api.*;

/**
 * @Writer ArtisanLS
 * @Date 2023/3/29
 */
public class TableApiStruct {
    public static void main(String[] args) {
        // Create a TableEnvironment for batch or streaming execution.
        TableEnvironment tEnv = TableEnv.tEnv("stream");

        // Create a source table
        tEnv.createTemporaryTable(
                "SourceTable",
                TableDescriptor
                        .forConnector("datagen")
                        .schema(Schema
                                .newBuilder()
                                .column("f0", DataTypes.STRING())
                                .build())
                        .option(DataGenConnectorOptions.ROWS_PER_SECOND, 100l)
                        .build());

        // Create a sink table (using SQL DDL)
        tEnv.executeSql("CREATE TEMPORARY TABLE SinkTable WITH ('connector' = 'blackhole') LIKE SourceTable (EXCLUDING OPTIONS)");

        // Create a Table object from a Table API query
        Table table2 = tEnv.from("SourceTable");
//        table2.execute().print();

        // Create a Table object from a SQL query
        Table table3 = tEnv.sqlQuery("SELECT * FROM SourceTable");
//        table3.execute().print();

        // Emit a Table API result Table to a TableSink, same for SQL result
        TableResult tableResult = table2.executeInsert("SinkTable");
        tableResult.print();
    }
}
