package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt;

import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptRequestDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface TransactionReceiptService {

    List<TransactionReceiptResponseDTO> getAll();

    TransactionReceiptResponseDTO create(TransactionReceiptRequestDTO transactionReceiptRequestDTO) throws JsonProcessingException;
}
