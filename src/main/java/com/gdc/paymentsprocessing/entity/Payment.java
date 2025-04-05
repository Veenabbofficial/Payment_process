package com.gdc.paymentsprocessing.entity;

import com.gdc.paymentsprocessing.enums.PaymentMethod;
import com.gdc.paymentsprocessing.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments", schema = "gdc-db")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id", updatable = false, nullable = false)
    private UUID paymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private PackageDetails packageDetails; // Link to the package being paid for

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private UserInfo driver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)")
    private Double totalAmount;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2) DEFAULT 0.0")
    private Double discountAmount = 0.0;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2) DEFAULT 0.0")
    private Double commissionAmount = 0.0;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)")
    private Double checkoutAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        calculateCheckoutAmount();
    }

    private void calculateCheckoutAmount() {
        double discount = Optional.ofNullable(discountAmount).orElse(0.0);
        double commission = Optional.ofNullable(commissionAmount).orElse(0.0);
        double total = Optional.ofNullable(totalAmount).orElse(0.0);
        this.checkoutAmount = total - discount - commission;
    }

    @PreUpdate
    public void onUpdate() {
        calculateCheckoutAmount(); // Ensure this is reflected on updates
    }
}