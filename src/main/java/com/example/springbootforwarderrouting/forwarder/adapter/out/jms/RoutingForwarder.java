package com.example.springbootforwarderrouting.forwarder.adapter.out.jms;

import com.example.springbootforwarderrouting.forwarder.application.port.out.ForwardToRoutingPort;
import com.example.springbootforwarderrouting.forwarder.domain.Routings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class RoutingForwarder implements ForwardToRoutingPort {

    private final QueueSender queueSender;

    @Override
    public boolean forwardMessage(String payload, Routings routings, String srcUid) {
        queueSender.forwardToRoutingAndTracking(payload, srcUid, routings.targets());
        return true;
    }
}
