import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Writer ArtisanLS
 * @Date 2023/8/3
 */
public class KafkaTest {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.31.123:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //设置拉取信息后是否自动提交（kafka记录当前app是否已经获取到此信息），false 手动提交 ；true 自动提交
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        /**
         * earliest 第一条数据开始拉取（当前应该没有获取过此topic信息）
         * latest 获取最新的数据（当前没有获取过此topic信息）
         * none
         * group消费者分组的概念
         */
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "GROUP3");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //创建好kafka消费者对象后，订阅消息，指定消费的topic
        consumer.subscribe(Collections.singleton("flink-watermark"));
        //System.out.println(consumer.listTopics());

        while (true){
            Duration mills = Duration.ofMillis(1000);
            ConsumerRecords<String, String> records = consumer.poll(mills);
            for (ConsumerRecord<String,String> record:records){
                String topic = record.topic();
                int partition = record.partition();
                long offset = record.offset();
                String key = record.key();
                String value = record.value();
                long timestamp = record.timestamp();
                System.out.println("topic:"+topic+"\tpartition"+partition+"\toffset"+offset+"\tkey"+key+"\tvalue"+value+"\ttimestamp"+timestamp);
            }
//            consumer.commitAsync(); //手动提交
        }
    }
}
