package com.estudo.kafka.demo.service;

import com.estudo.kafka.demo.response.MessagesResponse;

import java.util.List;

public interface ConsumerKafka {

    /**
     * Consumes messages from a group and a topic by selecting
     * @param group
     * @param topic
     * @return
     */
    public List<MessagesResponse> consumerKafka(String group, String topic);

}
