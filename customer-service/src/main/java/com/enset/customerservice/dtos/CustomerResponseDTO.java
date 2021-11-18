package com.enset.customerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerResponseDTO {
    private String id;
    private String name;
    private String email;
}
