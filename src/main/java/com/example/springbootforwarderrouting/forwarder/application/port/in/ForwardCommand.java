package com.example.springbootforwarderrouting.forwarder.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForwardCommand {

    @NotBlank
    private final String payload;
    @NotBlank
    private final String srcUid;
    @NotBlank
    private final String serviceId;
    @NotBlank
    private final String serviceVersion;
    @NotBlank
    private final String system;
    @NotBlank
    private final String environment;
    @NotBlank
    private final String inQueue;

}
