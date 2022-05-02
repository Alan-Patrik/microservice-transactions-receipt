package com.alanpatrik.bancosantander.transactionreceipt.modules.client;

import com.alanpatrik.bancosantander.transactionreceipt.enums.TransactionType;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private double value;
    private TransactionType transactionType;
    private LocalDateTime descriptionDate;
    private AccountDTO senderAccount;
    private AccountDTO destinationAccount;
}
