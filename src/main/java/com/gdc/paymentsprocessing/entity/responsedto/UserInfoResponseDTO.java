package com.gdc.paymentsprocessing.entity.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoResponseDTO {

    private UUID userid;
    private String message;


}
