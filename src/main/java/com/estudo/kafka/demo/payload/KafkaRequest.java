package com.estudo.kafka.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaRequest {

    private String topic;
    private String mensage;
    private int times;
}
