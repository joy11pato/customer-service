package com.test.ntt.client.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateClientDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String gender;

    @NotBlank(message = "Idcard is mandatory")
    @Size(min = 10, max = 30, message = "idCard must contain from 6 to 20 characters")
    private String idCard;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone is mandatory")
    @Size(min = 6, message = "Phone number must not exceed 20 characters")
    private String phone;


}
