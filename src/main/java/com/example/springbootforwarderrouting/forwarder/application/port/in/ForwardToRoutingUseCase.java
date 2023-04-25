package com.example.springbootforwarderrouting.forwarder.application.port.in;

import jakarta.validation.Valid;

public interface ForwardToRoutingUseCase {

    boolean forward(@Valid ForwardCommand command);
}
