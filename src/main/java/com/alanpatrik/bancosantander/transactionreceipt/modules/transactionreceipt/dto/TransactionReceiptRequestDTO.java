package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto;

import com.alanpatrik.bancosantander.transactionreceipt.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReceiptRequestDTO {

    private double value;
    private TransactionType transactionType;
    private LocalDateTime descriptionDate;
    private AccountDTO senderAccount;
    private AccountDTO destinationAccount;
}
