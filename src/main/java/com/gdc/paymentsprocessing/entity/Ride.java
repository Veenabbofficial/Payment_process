package com.gdc.paymentsprocessing.entity;

import com.gdc.paymentsprocessing.entity.UserInfo;
import com.gdc.paymentsprocessing.enums.RideStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "rides", schema = "gdc-db")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private UserInfo driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    // Initialize status in constructor instead of field
    public Ride() {
        this.status = RideStatus.PENDING;
    }
}
