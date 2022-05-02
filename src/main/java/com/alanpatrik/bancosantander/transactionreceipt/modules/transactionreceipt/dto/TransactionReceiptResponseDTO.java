package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto;

import com.alanpatrik.bancosantander.transactionreceipt.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReceiptResponseDTO {

    private Long id;
    private double value;
    private TransactionType transactionType;
    private LocalDateTime descriptionDate;
    private AccountDTO senderAccount;
    private AccountDTO destinationAccount;
}
