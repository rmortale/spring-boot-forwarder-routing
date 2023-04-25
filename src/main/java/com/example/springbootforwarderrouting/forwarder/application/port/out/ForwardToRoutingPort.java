package com.example.springbootforwarderrouting.forwarder.application.port.out;

import com.example.springbootforwarderrouting.forwarder.domain.Routings;

public interface ForwardToRoutingPort {

    boolean forwardMessage(String payload, Routings routings, String srcUid);
}
