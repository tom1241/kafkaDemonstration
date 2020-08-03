package com.estudo.kafka.demo.bussiness;

import com.estudo.kafka.demo.response.MessagesResponse;
import com.estudo.kafka.demo.service.ConsumerKafka;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class ConsumerKafkaImpl implements ConsumerKafka {

    @Override
    public List<MessagesResponse> consumerKafka(String group, String topic) {
        List<MessagesResponse> messagesResponses = new ArrayList<>();

        Properties properties = getConsumerProperties(group);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
            consumer.subscribe(Arrays.asList(topic));

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

            for (ConsumerRecord<String, String> record: records) {
                MessagesResponse response = new MessagesResponse();
                response.setTopic(record.topic());
                response.setMessage(record.value());

                messagesResponses.add(response);
            }
            consumer.close();
            return messagesResponses;
        }

    /**
     * Set Consumer Configs
     * @param group
     * @return
     */
    private Properties getConsumerProperties(String group) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return properties;
    }

}
