package com.example.springbootforwarderrouting.forwarder.application.port.out;

import com.example.springbootforwarderrouting.forwarder.domain.Routings;

public interface FindRoutingPort {

    Routings findRouting(String system, String environment, String serviceId, String serviceVersion);
}
