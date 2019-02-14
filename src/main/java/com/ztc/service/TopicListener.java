package com.ztc.service;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.List;

public interface TopicListener extends MessageListener {

    List<ChannelTopic> getTopic();
}
