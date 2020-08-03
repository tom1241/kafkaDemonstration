package com.estudo.kafka.demo.service;

import com.estudo.kafka.demo.payload.KafkaRequest;

public interface ProducerKafka {

    String getHelloWorld();

    /**
     * Receive a request with the desired topic, the message value and the number of times the message was sent
     * @param request
     * @return
     */
    String getProducer(KafkaRequest request);

}
