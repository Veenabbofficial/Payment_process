package com.gdc.paymentsprocessing.servise;

import com.gdc.paymentsprocessing.entity.requestdto.UserInfoRequestDTO;
import com.gdc.paymentsprocessing.entity.responsedto.UserInfoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserInfoService {
    public String addUser(UserInfoRequestDTO userInfoRequestDTO);
    public boolean emailExists(String email);
    UserInfoResponseDTO getUserById(UUID id);
}
