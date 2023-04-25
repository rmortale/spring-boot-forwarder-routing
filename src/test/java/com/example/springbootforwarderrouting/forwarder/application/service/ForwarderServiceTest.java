package com.example.springbootforwarderrouting.forwarder.application.service;

import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardCommand;
import com.example.springbootforwarderrouting.forwarder.application.port.in.ForwardToRoutingUseCase;
import com.example.springbootforwarderrouting.forwarder.application.port.out.FindRoutingPort;
import com.example.springbootforwarderrouting.forwarder.application.port.out.ForwardToRoutingPort;
import com.example.springbootforwarderrouting.forwarder.domain.Routings;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ForwarderServiceTest {

    FindRoutingPort findRoutingPort = mock(FindRoutingPort.class);
    ForwardToRoutingPort forwardToRoutingPort = mock(ForwardToRoutingPort.class);
    ForwardToRoutingUseCase sut = new ForwarderService(findRoutingPort, forwardToRoutingPort);

    static String SRC_UID = "srcUid";
    static String PAYLOAD = "payload";

    @Test
    public void forwardSuccessfully() {
        ForwardCommand fc = ForwardCommand.builder()
                .payload(PAYLOAD)
                .srcUid(SRC_UID)
                .system("anySystem")
                .environment("anyEnvironemnt")
                .serviceId("anyServiceId")
                .serviceVersion("anyVersion")
                .build();

        Routings routings = new Routings(Arrays.asList("queue1"));

        when(findRoutingPort.findRouting(anyString(), anyString(), anyString(), anyString())).thenReturn(routings);
        when(forwardToRoutingPort.forwardMessage(PAYLOAD, routings, SRC_UID)).thenReturn(true);

        boolean success = sut.forward(fc);

        assertThat(success).isTrue();
        verify(findRoutingPort).findRouting(anyString(), anyString(), anyString(), anyString());
        verify(forwardToRoutingPort).forwardMessage(PAYLOAD, routings, SRC_UID);
    }
}