package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto;

import com.alanpatrik.bancosantander.transactionreceipt.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private Integer number;
    private Integer agency;
    private double balance;
    private LocalDateTime descriptionDate;
    private LocalDateTime updateDate;
    private AccountType accountType;
    private UserDTO user;
}
