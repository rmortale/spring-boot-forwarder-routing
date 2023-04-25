package com.example.springbootforwarderrouting.forwarder.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "routing")
@Data
@AllArgsConstructor
@NoArgsConstructor
class RoutingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serviceId;
    private String serviceVersion;
    private String system;
    private String environment;
    private String targetQueue;

}
