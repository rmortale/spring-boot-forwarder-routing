package com.example.springbootforwarderrouting.forwarder.adapter.out.persistence;

import com.example.springbootforwarderrouting.forwarder.domain.Routings;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RoutingMapperTest {

    RoutingMapper sut = new RoutingMapper();

    @Test
    public void testEmptyMapping() {
        List<RoutingEntity> routingEntities = Collections.emptyList();

        Routings routings = sut.mapToDomain(routingEntities);

        assertThat(routings.targets().isEmpty(), is(true));
    }

    @Test
    public void testNonEmptyMapping() {
        RoutingEntity e = new RoutingEntity();
        e.setTargetQueue("myQueue");
        List<RoutingEntity> routingEntities = List.of(e);

        Routings routings = sut.mapToDomain(routingEntities);

        assertThat(routings.targets().get(0), equalTo("myQueue"));
    }

}