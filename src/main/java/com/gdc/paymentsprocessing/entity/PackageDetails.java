package com.gdc.paymentsprocessing.entity;

import com.gdc.paymentsprocessing.enums.PackageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "packagedetails",schema = "gdc-db")
public class PackageDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "package_id", updatable = false, nullable = false)
    private UUID packageId; // Unique identifier for the package

    private String description; // Description of the package
    private Double weight; // Weight of the package
    private Double volume; // Volume of the package (if applicable)

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // User who owns the package
    private UserInfo owner; // The user who owns the package being transported

    @OneToOne(mappedBy = "packageDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment; // The payment associated with this package

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private PackageType packageType; // Ensure this enum is well-defined

    @Column(name = "is_fragile")
    private Boolean isFragile; // To indicate fragility of the package
}