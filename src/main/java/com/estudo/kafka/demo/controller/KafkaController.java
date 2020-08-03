package com.estudo.kafka.demo.controller;

import com.estudo.kafka.demo.payload.ConsumerRequest;
import com.estudo.kafka.demo.payload.KafkaRequest;
import com.estudo.kafka.demo.response.MessagesResponse;
import com.estudo.kafka.demo.service.ConsumerKafka;
import com.estudo.kafka.demo.service.ProducerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {

    @Autowired
    private ProducerKafka producerService;

    @Autowired
    private ConsumerKafka consumerService;

    @GetMapping(value="/helloWorld")
    public String getHelloWorld() {
        return producerService.getHelloWorld();
    }

    @PostMapping(value="/producerKafka")
    public String getProducerKafka(KafkaRequest request) {
        return producerService.getProducer(request);
    }

    @PostMapping(value="/consumerKafka")
    public List<MessagesResponse> getConsumerKafka(ConsumerRequest request) {
        return consumerService.consumerKafka(request.getGroup(), request.getTopic());
    }

}
