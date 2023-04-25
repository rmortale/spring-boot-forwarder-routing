package com.example.springbootforwarderrouting.forwarder.adapter.out.persistence;

import com.example.springbootforwarderrouting.forwarder.domain.Routings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class RoutingMapper {

    Routings mapToDomain(List<RoutingEntity> entitys) {
        return new Routings(entitys.stream()
                .filter(e -> !e.getTargetQueue().isBlank())
                .map(e -> e.getTargetQueue()).collect(Collectors.toList()));
    }

}
