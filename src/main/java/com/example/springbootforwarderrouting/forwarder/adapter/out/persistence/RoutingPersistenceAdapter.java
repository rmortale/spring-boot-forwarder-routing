package com.example.springbootforwarderrouting.forwarder.adapter.out.persistence;

import com.example.springbootforwarderrouting.forwarder.application.port.out.FindRoutingPort;
import com.example.springbootforwarderrouting.forwarder.domain.Routings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class RoutingPersistenceAdapter implements FindRoutingPort {

    private final RoutingRepository repository;
    private final RoutingMapper mapper;

    @Override
    public Routings findRouting(String system, String environment, String serviceId, String serviceVersion) {
        return mapper.mapToDomain(findRoutings(system, environment, serviceId, serviceVersion));
    }

    private List<RoutingEntity> findRoutings(String system, String environment, String serviceId, String serviceVersion) {
        log.debug("querying for routings with system={}, environment={}, serviceId={}, serviceVersion={}",
                system, environment, serviceId, serviceVersion);
        return repository.findBySystemAndEnvironmentAndServiceIdAndServiceVersion(
                system, environment, serviceId, serviceVersion);
    }
}
