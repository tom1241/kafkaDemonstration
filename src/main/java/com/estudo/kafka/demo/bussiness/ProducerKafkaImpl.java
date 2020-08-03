package com.estudo.kafka.demo.bussiness;

import com.estudo.kafka.demo.payload.KafkaRequest;
import com.estudo.kafka.demo.service.ProducerKafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ProducerKafkaImpl implements ProducerKafka {

    @Override
    public String getHelloWorld() {
        return "Hello World, take advantage of this project and feel free to improve it with more use cases!";
    }

    @Override
    public String getProducer(KafkaRequest request) {

        Properties kafkaProperties = getProperties();

        try(KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProperties)) {
            int i = 0;
            while (i < request.getTimes()) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(request.getTopic(), (i + 1) + "," +request.getMensage());
                producer.send(record);
                i++;
            }

            return "It works!";
        }
    }

    private Properties getProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return kafkaProperties;
    }
}
