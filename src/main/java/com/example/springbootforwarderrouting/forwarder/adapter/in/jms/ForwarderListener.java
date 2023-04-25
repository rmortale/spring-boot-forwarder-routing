package com.example.springbootforwarderrouting.forwarder.adapter.in.jms;

import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardCommand;
import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardToRoutingUseCase;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class ForwarderListener {


    private final ForwardToRoutingUseCase forwardToRoutingUseCase;
    private final CommandMapper mapper;

    @JmsListener(destination = "${forwarder.var.queue.name}", concurrency = "${forwarder.var.queue.concurrency}")
    @JmsListener(destination = "${forwarder.avt.queue.name}", concurrency = "${forwarder.avt.queue.concurrency}")
    @JmsListener(destination = "${forwarder.vvz.queue.name}", concurrency = "${forwarder.vvz.queue.concurrency}")
    public void processMessage(Message<String> message) {
        ForwardCommand c = mapper.toCommand(message);
        try {
            forwardToRoutingUseCase.forward(c);
            log.info("Successfully forwarded message from queue {} with id: {}", c.getInQueue(), c.getSrcUid());
        } catch (ConstraintViolationException e) {
            log.error("Got invalid message from {}, errors: {}", c.getInQueue(), e.getMessage());
        }
    }
}
