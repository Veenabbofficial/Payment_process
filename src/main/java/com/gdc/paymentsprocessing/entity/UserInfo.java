package com.gdc.paymentsprocessing.entity;

import com.gdc.paymentsprocessing.entity.Payment;
import com.gdc.paymentsprocessing.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "gdc-db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // Ensure password is encrypted before saving

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String gender;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @Column(nullable = false)
    private String address;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "phone",unique = true,nullable = false)
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", schema = "gdc-db", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Payment> payments;
}
