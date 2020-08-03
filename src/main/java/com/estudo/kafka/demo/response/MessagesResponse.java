package com.estudo.kafka.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagesResponse {

    private String topic;
    private String message;

}
