package com.example.springbootforwarderrouting.forwarder.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ForwardCommandTest {

    @Test
    public void testCreationNullPayload() {
        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            ForwardCommand command = ForwardCommand.builder().build();
        });
        assertEquals("payload: must not be blank", thrown.getMessage());
    }

    @Test
    public void testCreationBlankPayload() {
        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            ForwardCommand command = ForwardCommand.builder().build();
        });
        assertEquals("payload: must not be blank", thrown.getMessage());
    }

}