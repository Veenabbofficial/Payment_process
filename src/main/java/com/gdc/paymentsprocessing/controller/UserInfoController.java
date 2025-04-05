package com.gdc.paymentsprocessing.controller;

import com.gdc.paymentsprocessing.entity.UserInfo;
import com.gdc.paymentsprocessing.entity.requestdto.UserInfoRequestDTO;
import com.gdc.paymentsprocessing.servise.UserInfoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    private final UserInfoService userInfoService;


    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/create-users")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserInfoRequestDTO userInfoRequestDTO, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        if (userInfoService.emailExists(userInfoRequestDTO.getEmail())){
            return ResponseEntity.badRequest().body("User Already exists");
        }
        userInfoService.addUser(userInfoRequestDTO);
        return ResponseEntity.ok("User Added Successfully");
    }
}
