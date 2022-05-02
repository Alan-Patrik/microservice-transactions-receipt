package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt;

import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.AccountDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptRequestDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionReceiptServiceImpl implements TransactionReceiptService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final TransactionReceiptRepository transactionReceiptRepository;
    private final TransactionReceiptMapper transactionReceiptMapper = TransactionReceiptMapper.INSTANCE;

    @Override
    public List<TransactionReceiptResponseDTO> getAll() {
        List<TransactionReceiptResponseDTO> transactionReceiptResponseDTOList = transactionReceiptRepository.findAll().stream().map(transactionReceipt -> {
            TransactionReceiptResponseDTO transactionReceiptResponseDTO = new TransactionReceiptResponseDTO();

            try {
                transactionReceiptResponseDTO.setId(transactionReceipt.getId());
                transactionReceiptResponseDTO.setValue(transactionReceipt.getValue());
                transactionReceiptResponseDTO.setTransactionType(transactionReceipt.getTransactionType());
                transactionReceiptResponseDTO.setDescriptionDate(transactionReceipt.getDescriptionDate());
                transactionReceiptResponseDTO.setSenderAccount(
                        mapper
                                .registerModule(new JavaTimeModule())
                                .readValue(transactionReceipt.getSenderAccount(), AccountDTO.class)
                );
                transactionReceiptResponseDTO.setDestinationAccount(
                        mapper
                                .registerModule(new JavaTimeModule())
                                .readValue(transactionReceipt.getDestinationAccount(), AccountDTO.class)
                );

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return transactionReceiptResponseDTO;
        }).collect(Collectors.toList());

        return transactionReceiptResponseDTOList;
    }

    @Override
    public TransactionReceiptResponseDTO create(TransactionReceiptRequestDTO transactionReceiptRequestDTO) throws JsonProcessingException {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setValue(transactionReceiptRequestDTO.getValue());
        transactionReceipt.setTransactionType(transactionReceiptRequestDTO.getTransactionType());
        transactionReceipt.setDescriptionDate(transactionReceiptRequestDTO.getDescriptionDate());
        transactionReceipt.setSenderAccount(mapper.registerModule(new JavaTimeModule()).writeValueAsString(transactionReceiptRequestDTO.getSenderAccount()));
        transactionReceipt.setDestinationAccount(mapper.registerModule(new JavaTimeModule()).writeValueAsString(transactionReceiptRequestDTO.getDestinationAccount()));

        transactionReceipt = transactionReceiptRepository.save(transactionReceipt);

        return transactionReceiptMapper.toResponse(transactionReceipt);
    }
}
