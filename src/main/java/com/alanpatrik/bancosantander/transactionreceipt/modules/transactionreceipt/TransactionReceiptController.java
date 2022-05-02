package com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt;

import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptRequestDTO;
import com.alanpatrik.bancosantander.transactionreceipt.modules.transactionreceipt.dto.TransactionReceiptResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comprovante")
public class TransactionReceiptController {

    private final TransactionReceiptServiceImpl transactionReceiptService;

    @GetMapping
    public List<TransactionReceiptResponseDTO> getAll() {
        List<TransactionReceiptResponseDTO> transactionReceiptResponseDTOList = transactionReceiptService.getAll();

        return transactionReceiptResponseDTOList;
    }

    @PostMapping
    public TransactionReceiptResponseDTO create(@RequestBody TransactionReceiptRequestDTO transactionReceiptRequestDTO) throws JsonProcessingException {
        TransactionReceiptResponseDTO transactionReceiptReceivedResponseDTO = transactionReceiptService.create(transactionReceiptRequestDTO);

        return transactionReceiptReceivedResponseDTO;
    }
}
