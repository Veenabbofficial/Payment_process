package com.gdc.paymentsprocessing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="bankdetails", schema = "gdc-db")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "bank_details_id", updatable = false, nullable = false)
    private UUID bankDetailsId;

    @Pattern(regexp = "\\d{9,18}", message = "Invalid account number")
    @Column(name = "account_number", nullable = false, unique = true, length = 50)
    private String accountNumber;

    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code")
    @Column(name = "ifsc_code", nullable = false, length = 20)
    private String ifscCode;

    @Column(name = "bank_name", nullable = false, length = 255)
    private String bankName;

    @Column(nullable = false)
    private Double balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Automatically set timestamp

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @PrePersist
    protected void onCreate() {
        this.processedAt = LocalDateTime.now();
    }
}