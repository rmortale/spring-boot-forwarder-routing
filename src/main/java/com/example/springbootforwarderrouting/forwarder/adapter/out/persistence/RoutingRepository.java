package com.example.springbootforwarderrouting.forwarder.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RoutingRepository extends JpaRepository<RoutingEntity, Long> {

    List<RoutingEntity> findBySystemAndEnvironmentAndServiceIdAndServiceVersion(String system, String environment, String serviceId, String serviceVersion);
}
