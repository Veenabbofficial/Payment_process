package com.gdc.paymentsprocessing.repository;

import com.gdc.paymentsprocessing.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByEmail(String email); // Fetch user by email
}
