package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String cpf;
    private String name;
    private String password;
    private LocalDateTime descriptionDate;
    private LocalDateTime updateDate;
}
