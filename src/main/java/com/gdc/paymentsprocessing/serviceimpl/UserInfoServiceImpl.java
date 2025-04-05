package com.gdc.paymentsprocessing.serviceimpl;

import com.gdc.paymentsprocessing.entity.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.gdc.paymentsprocessing.entity.requestdto.UserInfoRequestDTO;
import com.gdc.paymentsprocessing.entity.responsedto.UserInfoResponseDTO;
import com.gdc.paymentsprocessing.repository.UserInfoRepository;
import com.gdc.paymentsprocessing.servise.UserInfoService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Ensure this is injected

    @Autowired
    private Validator validator;

    @Override
    @Transactional
    public String addUser(UserInfoRequestDTO userInfoRequestDTO) {
        // Convert DTO to Entity
        UserInfo userInfo = UserInfo.builder()
                .userName(userInfoRequestDTO.getUserName())
                .email(userInfoRequestDTO.getEmail())
                .password(passwordEncoder.encode(userInfoRequestDTO.getPassword())) // Encrypt password
                .gender(userInfoRequestDTO.getGender())
                .phone(userInfoRequestDTO.getPhone())
                .address(userInfoRequestDTO.getAddress())
                .street(userInfoRequestDTO.getStreet())
                .city(userInfoRequestDTO.getCity())
                .state(userInfoRequestDTO.getState())
                .zipCode(userInfoRequestDTO.getZipCode())
                .country(userInfoRequestDTO.getCountry())
                .isVerified(userInfoRequestDTO.getIsVerified())
                .roles(new HashSet<>(userInfoRequestDTO.getRoles())) //  Convert roles
                .build();

        userInfoRepository.save(userInfo);

        return "User created successfully";
    }

    @Override
    public boolean emailExists(String email) {
        return userInfoRepository.findByEmail(email).isPresent();
    }


    @Override
    public UserInfoResponseDTO getUserById(UUID id) {
        return null;
    }
}

