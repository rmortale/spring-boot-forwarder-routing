package com.example.springbootforwarderrouting.forwarder.application.service;

import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardCommand;
import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardToRoutingUseCase;
import com.example.springbootforwarderrouting.forwarder.application.port.out.FindRoutingPort;
import com.example.springbootforwarderrouting.forwarder.application.port.out.ForwardToRoutingPort;
import com.example.springbootforwarderrouting.forwarder.domain.Routings;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class ForwarderService implements ForwardToRoutingUseCase {

    private final FindRoutingPort routingPort;
    private final ForwardToRoutingPort forwardToRoutingPort;

    @Override
    public boolean forward(@Valid ForwardCommand fc) {

        Routings routings =
                routingPort.findRouting(fc.getSystem(), fc.getEnvironment(), fc.getServiceId(), fc.getServiceVersion());

        if (routings.targets().isEmpty()) {
            log.error("no valid routing found! srcUid={}", fc.getSrcUid());
            return false;
        }

        return forwardToRoutingPort.forwardMessage(fc.getPayload(), routings, fc.getSrcUid());
    }


}
