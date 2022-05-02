package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt;

import com.alanpatrik.bancosantander.transactionreceipt.modules.client.TransactionDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.AccountDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptRequestDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionReceiptMapper {
    TransactionReceiptMapper INSTANCE = Mappers.getMapper(TransactionReceiptMapper.class);
    ObjectMapper mapper = new ObjectMapper();

    default TransactionReceipt toModel(TransactionReceiptResponseDTO transactionReceiptResponseDTO) throws JsonProcessingException {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setId(transactionReceiptResponseDTO.getId());
        transactionReceipt.setValue(transactionReceiptResponseDTO.getValue());
        transactionReceipt.setTransactionType(transactionReceiptResponseDTO.getTransactionType());
        transactionReceipt.setDescriptionDate(transactionReceiptResponseDTO.getDescriptionDate());
        transactionReceipt.setSenderAccount(mapper.registerModule(new JavaTimeModule()).writeValueAsString(transactionReceiptResponseDTO.getSenderAccount()));
        transactionReceipt.setDestinationAccount(mapper.registerModule(new JavaTimeModule()).writeValueAsString(transactionReceiptResponseDTO.getSenderAccount()));

        return transactionReceipt;
    }

    default TransactionReceiptResponseDTO toResponse(TransactionReceipt transactionReceipt) throws JsonProcessingException {
        TransactionReceiptResponseDTO transactionReceiptResponseDTO = new TransactionReceiptResponseDTO();
        transactionReceiptResponseDTO.setId(transactionReceipt.getId());
        transactionReceiptResponseDTO.setValue(transactionReceipt.getValue());
        transactionReceiptResponseDTO.setTransactionType(transactionReceipt.getTransactionType());
        transactionReceiptResponseDTO.setDescriptionDate(transactionReceipt.getDescriptionDate());
        transactionReceiptResponseDTO.setSenderAccount(mapper.registerModule(new JavaTimeModule()).readValue(transactionReceipt.getSenderAccount(), AccountDTO.class));
        transactionReceiptResponseDTO.setDestinationAccount(mapper.registerModule(new JavaTimeModule()).readValue(transactionReceipt.getDestinationAccount(), AccountDTO.class));


        return transactionReceiptResponseDTO;
    }

    default TransactionDTO toDTO(TransactionReceipt transactionReceipt) throws JsonProcessingException {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transactionReceipt.getId());
        transactionDTO.setValue(transactionReceipt.getValue());
        transactionDTO.setTransactionType(transactionReceipt.getTransactionType());
        transactionDTO.setDescriptionDate(transactionReceipt.getDescriptionDate());
        transactionDTO.setSenderAccount(mapper.registerModule(new JavaTimeModule()).readValue(transactionReceipt.getSenderAccount(), AccountDTO.class));
        transactionDTO.setDestinationAccount(mapper.registerModule(new JavaTimeModule()).readValue(transactionReceipt.getDestinationAccount(), AccountDTO.class));

        return transactionDTO;
    }
}
