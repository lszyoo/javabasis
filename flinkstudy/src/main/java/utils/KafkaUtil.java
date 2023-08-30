package utils;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Writer ArtisanLS
 * @Date 2023/8/2
 */
public class KafkaUtil {
    // 获取生产者对象
    private static KafkaProducer<String, String> kafkaProducer = getKafka();

    // 创建生产者
    private static KafkaProducer<String, String> getKafka() {
        // 创建配置文件列表
        Properties properties = new Properties();
        // kafka 地址，多个用逗号分隔
        properties.put("bootstrap.servers", "192.168.31.123:9092");
        // 设置写出数据格式
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 写出的应答方式
        properties.put("acks", "all");
        // 错误重试
        properties.put("retries", 1);
        // 批量写出
        properties.put("batch.size", 16384);
        // 创建生产者对象
        return new KafkaProducer<>(properties);
    }

    public static void sendMsg(String topicName, String msg) {
        ProducerRecord<String, String> banRecordBlue = new ProducerRecord<>(topicName, null, msg);
        kafkaProducer.send(banRecordBlue);
        kafkaProducer.flush();
    }

    // 获取 flink kafka source
    public static KafkaSource<String> getKafkaSource(String topicName, String groupId) {
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers("192.168.31.123:9092")
                .setTopics(topicName)
                .setGroupId(groupId)
//                .setStartingOffsets(OffsetsInitializer.earliest())
                .setStartingOffsets(OffsetsInitializer.latest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();
        return source;
    }
}
