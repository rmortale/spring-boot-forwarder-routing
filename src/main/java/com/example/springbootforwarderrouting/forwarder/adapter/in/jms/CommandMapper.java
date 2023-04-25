package com.example.springbootforwarderrouting.forwarder.adapter.in.jms;

import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardCommand;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import static org.springframework.jms.support.JmsHeaders.DESTINATION;

@Component
public class CommandMapper {

    public static final String SYSTEM_KEY = "vt_system";
    public static final String ENVIRONEMNT_KEY = "vt_environment";
    public static final String SERVICE_ID_KEY = "vt_service_id";
    public static final String SERVICE_VERSION_KEY = "vt_service_version";

    public ForwardCommand toCommand(Message<String> message) {
        return ForwardCommand.builder()
                .payload(message.getPayload())
                .srcUid(getHeader(message.getHeaders(), JmsHeaders.MESSAGE_ID))
                .serviceId(getHeader(message.getHeaders(), SERVICE_ID_KEY))
                .serviceVersion(getHeader(message.getHeaders(), SERVICE_VERSION_KEY))
                .system(getHeader(message.getHeaders(), SYSTEM_KEY))
                .environment(getHeader(message.getHeaders(), ENVIRONEMNT_KEY))
                .inQueue(getQueueName(message.getHeaders()))
                .build();
    }

    private String getQueueName(MessageHeaders headers) {
        return headers.get(DESTINATION, ActiveMQQueue.class).getQueueName();
    }

    private String getHeader(MessageHeaders headers, String key) {
        return headers.get(key, String.class);
    }
}
