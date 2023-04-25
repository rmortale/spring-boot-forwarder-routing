package com.example.springbootforwarderrouting.forwarder.adapter.out.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class QueueSender {

    private static final String COMPONENT_NAME = "artemis-forwarder-routing";
    public static final String COMPONENT_NAME_KEY = "vt_componentName";
    public static final String SOURCE_UID_KEY = "vt_srcUid";
    public static final String TARGET_DESTINATIONS_KEY = "vt_target_destinations";
    private final JmsTemplate template;
    private final String routingQueue;
    private final String trackingQueue;

    public QueueSender(JmsTemplate template,
                       @Value("${routing.queue.name}") String routingQueue,
                       @Value("${tracking.queue.name}") String trackingQueue) {
        this.template = template;
        this.routingQueue = routingQueue;
        this.trackingQueue = trackingQueue;
    }


    public void forwardToRoutingAndTracking(String payload, String srcUid, List<String> targets) {
        forwardMessage(routingQueue, payload, srcUid, targets);
        forwardMessage(trackingQueue, payload, srcUid, targets);
    }

    private void forwardMessage(String queue, String payload, String sourceUid, List<String> targets) {
        template.convertAndSend(queue, payload, m -> {
            setHeaderProperty(m, SOURCE_UID_KEY, sourceUid);
            setHeaderProperty(m, COMPONENT_NAME_KEY, COMPONENT_NAME);
            if (targets != null) {
                setHeaderProperty(m, TARGET_DESTINATIONS_KEY, Strings.join(targets, ','));
            }
            return m;
        });
    }

    private void setHeaderProperty(Message m, String key, String value) throws JMSException {
        m.setStringProperty(key, value);
    }

}
