package com.estudo.kafka.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerRequest {

    private String group;
    private String topic;

}
