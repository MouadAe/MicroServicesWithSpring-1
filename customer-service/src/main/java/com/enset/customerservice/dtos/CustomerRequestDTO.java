package com.enset.customerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerRequestDTO {
    private String id;
    private String name;
    private String email;

    public CustomerRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
