package com.gdc.paymentsprocessing.entity.requestdto;

import com.gdc.paymentsprocessing.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequestDTO {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "Password must be at least 8 characters long")
    private String password; // Should be encrypted before saving

    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    private String street;
    private String city;
    private String state;

    @Pattern(regexp = "^[0-9]{5,6}$", message = "Zip code must be 5 or 6 digits")
    private String zipCode;

    private String country;

    @NotNull(message = "Verification status is required")
    private Boolean isVerified;

    @NotNull(message = "Roles must be provided")
    private Set<UserRole> roles;
}

